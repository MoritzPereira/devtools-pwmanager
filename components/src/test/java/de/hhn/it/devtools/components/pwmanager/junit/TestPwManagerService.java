package de.hhn.it.devtools.components.pwmanager.junit;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.pwmanager.Entry;
import de.hhn.it.devtools.apis.pwmanager.PwManagerListener;
import de.hhn.it.devtools.apis.pwmanager.PwManagerService;
import de.hhn.it.devtools.apis.pwmanager.exceptions.IllegalMasterPasswordException;
import de.hhn.it.devtools.components.pwmanager.provider.SimplePwManagerService;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestPwManagerService {

  private SimplePwManagerService pwManagerService;
  private DummyListener pwManagerListener;


  @BeforeEach
  void setup() {
    pwManagerService = new SimplePwManagerService();
    pwManagerListener = new DummyListener();
    pwManagerService.addListener(pwManagerListener);
  }

  @Test
  /**
   * Tests if listeners get added correctly.
   */
  void testAddListener() {
    //Good Case
    pwManagerService.addListener(pwManagerListener);

    //Bad Case
    pwManagerListener = null;
    Assertions.assertThrows(NullPointerException.class, () -> {
      pwManagerService.addListener(pwManagerListener);
    });
  }

  @Test
  void testCheckPassword() {
    //Good Case
    assertEquals(true, pwManagerService.checkPassword("testpw"));

    //Bad Case
    assertEquals(false, pwManagerService.checkPassword("uhu"));
  }

  @Test
  void testCheckEmail() {
    //Good Case
    assertEquals(true, pwManagerService.checkEmail("test@gmail.com"));

    //Bad Case
    assertEquals(false, pwManagerService.checkEmail("testgmailcom"));
  }

  @Test
  void testCheckUrl() {
    //Good Case
    assertEquals(true, pwManagerService.checkUrl("testurl.com"));

    //Bad Case
    assertEquals(false, pwManagerService.checkUrl("testurlcom"));
  }

  @Test
  void testChangeMasterPwGoodCase()
      throws IllegalMasterPasswordException, IllegalParameterException, NullPointerException {
    String pw = "password";

    pwManagerService.changeMasterPw(pw, pwManagerService.masterPw);
    assertEquals("password", pwManagerService.masterPw);
  }

  @Test
  void testChangeMasterPwBadCase()
      throws IllegalMasterPasswordException, IllegalParameterException, NullPointerException {
    //old pw is wrong
    Assertions.assertThrows(IllegalMasterPasswordException.class, () -> {
      pwManagerService.changeMasterPw("secret", "test");
    });
    //paswort is the same
    Assertions.assertThrows(IllegalParameterException.class, () -> {
      pwManagerService.changeMasterPw("admin", pwManagerService.masterPw);
    });
    //Passwort is too weak
    Assertions.assertThrows(IllegalParameterException.class, () -> {
      pwManagerService.changeMasterPw("adm", pwManagerService.masterPw);
    });
    //paswort is null
    Assertions.assertThrows(NullPointerException.class, () -> {
      pwManagerService.changeMasterPw(null, pwManagerService.masterPw);
    });
  }

  @Test
  void testLoginLogout()
      throws IllegalMasterPasswordException, IllegalParameterException, NullPointerException {

    //Good Case Login
    pwManagerService.login(PwManagerService.masterPw);
    assertEquals(true, pwManagerService.loggenIn);

    //Logout
    pwManagerService.logout();
    assertEquals(false, pwManagerService.loggenIn);

    //Bad Case Login
    Assertions.assertThrows(IllegalMasterPasswordException.class, () -> {
      pwManagerService.login("wrongmasterpw");
    });
  }

  @Test
  void testAddEntry() throws IllegalParameterException {

    //Good Case
    int oldsize = pwManagerService.listOfEntrys.size();
    assertTrue(pwManagerService.addEntry(0, "test.com", "test", "test@gmail.com",
        "testpw") instanceof Entry);
    assertEquals(oldsize + 1, pwManagerService.listOfEntrys.size());

    //Bad Case Email
    Assertions.assertThrows(IllegalParameterException.class, () -> {
      pwManagerService.addEntry(0, "test.com", "test", "testGmailCom", "testpw");
    });
    //Bad Case Url
    Assertions.assertThrows(IllegalParameterException.class, () -> {
      pwManagerService.addEntry(0, "testCom", "test", "test@gmail.com", "testpw");
    });
  }

  @Test
  void testChangeEntry() throws IllegalParameterException, IllegalMasterPasswordException {
    pwManagerService.addEntry(0, "test.com", "test", "test@gmail.com", "testpw");

    //Good Case
    String changedpw = "";
    Entry changedEntrysuccessful = new Entry(0, "test.com", "test", "test@gmail.com", "changedpw");
    pwManagerService.changeEntry(changedEntrysuccessful, pwManagerService.masterPw);
    for (Entry i : pwManagerService.listOfEntrys) {
      if (i.getEntryId() == 0) {
        changedpw = i.getPassword();
      }
    }
    assertEquals("changedpw", changedpw);

    //Bad Case Wrong MasterPw
    Entry changedEntry = new Entry(0, "test.com", "test", "test@gmail.com", "changedpw");
    Assertions.assertThrows(IllegalMasterPasswordException.class, () -> {
      pwManagerService.changeEntry(changedEntry, "wrongmasterpw");
    });

    //Bad Case Entry not found
    Entry changedEntry2 = new Entry(5, "test.com", "test", "test@gmail.com", "changedpw");
    Assertions.assertThrows(IllegalParameterException.class, () -> {
      pwManagerService.changeEntry(changedEntry2, pwManagerService.masterPw);
    });
  }

  @Test
  void testDeleteEntry() throws IllegalParameterException, IllegalMasterPasswordException {
    pwManagerService.addEntry(0, "test.com", "test", "test@gmail.com", "testpw");
    pwManagerService.addEntry(1, "test2.com", "test2", "test2@gmail.com", "test2pw");

    //Good Case
    int listsize = pwManagerService.listOfEntrys.size();
    pwManagerService.deleteEntry(0, pwManagerService.masterPw);
    assertEquals(listsize - 1, pwManagerService.listOfEntrys.size());

    //Bad Case Wrong MasterPw
    Assertions.assertThrows(IllegalMasterPasswordException.class, () -> {
      pwManagerService.deleteEntry(1, "wrongmasterpw");
    });

    //Bad Case Entry not found
    Assertions.assertThrows(IllegalParameterException.class, () -> {
      pwManagerService.deleteEntry(5, pwManagerService.masterPw);
    });
  }

  @Test
  void testChangeHidden() throws IllegalParameterException {
    pwManagerService.addEntry(0, "test.com", "test", "test@gmail.com", "testPw");

    //Good Case
    assertEquals(0 + ",test.com,test,test@gmail.com,testPw", pwManagerService.changeHidden(0));

    //Bad Case
    Assertions.assertThrows(IllegalParameterException.class, () -> {
      pwManagerService.changeHidden(5);
    });
  }

  @Test
  void testGenerateNewPw() throws IllegalParameterException{
    //Good Case
    

    //Bad Case
    Assertions.assertThrows(IllegalParameterException.class, () -> {
      pwManagerService.generateNewPw(true,true,true,true, 0);
    });

     }

}

