package de.hhn.it.devtools.components.pwmanager.provider.junit;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.pwmanager.Entry;
import de.hhn.it.devtools.apis.pwmanager.PwManagerListener;
import de.hhn.it.devtools.apis.pwmanager.PwManagerService;
import de.hhn.it.devtools.apis.pwmanager.exceptions.IllegalMasterPasswordException;
import de.hhn.it.devtools.components.pwmanager.provider.SimplePwManagerService;
import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestPwManagerService {

  private SimplePwManagerService pwManagerService;
  private DummyListener pwManagerListener;


  @BeforeEach
  void setup() throws IllegalParameterException {
    pwManagerService = new SimplePwManagerService();
    pwManagerListener = new DummyListener();
    pwManagerService.addListener(pwManagerListener);
  }

  @Test
  /**
   * Tests if listeners get added correctly.
   */
  void testAddListener() throws IllegalParameterException {
    //Bad Cases

    Assertions.assertThrows(IllegalParameterException.class, () -> {
      pwManagerService.addListener(pwManagerListener);
    });

    pwManagerListener = null;
    Assertions.assertThrows(IllegalParameterException.class, () -> {
      pwManagerService.addListener(pwManagerListener);
    });
  }

  @Test
  /**
   * Tests if listeners get added correctly.
   */
  void removeListener() throws IllegalParameterException {
    int size = pwManagerService.listeners.size();
    PwManagerListener listener2 = new DummyListener();

    //Bad Cases
    Assertions.assertThrows(IllegalParameterException.class, () -> {
      pwManagerService.removeListener(null);
    });

    Assertions.assertThrows(IllegalParameterException.class, () -> {
      pwManagerService.removeListener(listener2);
    });

    //Good Case
    pwManagerService.removeListener(pwManagerListener);
    Assertions.assertEquals(size-1,pwManagerService.listeners.size());
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
    assertEquals(true, pwManagerService.loggedIn);

    //Logout
    pwManagerService.logout();
    assertEquals(false, pwManagerService.loggedIn);

    //Bad Case Login
    Assertions.assertThrows(IllegalMasterPasswordException.class, () -> {
      pwManagerService.login("wrongmasterpw");
    });
  }

  @Test
  void testAddEntry() throws IllegalParameterException {

    //Good Case
    int oldsize = pwManagerService.listOfEntrys.size();
    assertTrue(pwManagerService.addEntry("test.com", "test", "test@gmail.com",
        "testpw") instanceof Entry);
    assertEquals(oldsize + 1, pwManagerService.listOfEntrys.size());

    //Bad Case No valid Password
    Assertions.assertThrows(IllegalParameterException.class, () -> {
      pwManagerService.addEntry("test.com", "test", "test@gmail.com", "te");
    });

    //Bad Case Email
    Assertions.assertThrows(IllegalParameterException.class, () -> {
      pwManagerService.addEntry("test.com", "test", "testGmailCom", "testpw");
    });
    //Bad Case Url
    Assertions.assertThrows(IllegalParameterException.class, () -> {
      pwManagerService.addEntry("testCom", "test", "test@gmail.com", "testpw");
    });
  }

  @Test
  void testChangeEntry() throws IllegalParameterException, IllegalMasterPasswordException {
    pwManagerService.addEntry("test.com", "test", "test@gmail.com", "testpw");

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

    //Bad Case No valid Password
    Entry changedEntry3 = new Entry(6, "test6.com", "test6", "test6@gmail.com", "6");
    Assertions.assertThrows(IllegalParameterException.class, () -> {
      pwManagerService.changeEntry(changedEntry3,pwManagerService.masterPw);
    });

    //Bad Case Email
    Entry changedEntry4 = new Entry(7, "test7.com", "test7", "test7gmailcom", "changedpw7");
    Assertions.assertThrows(IllegalParameterException.class, () -> {
      pwManagerService.changeEntry(changedEntry4,pwManagerService.masterPw);
    });

    //Bad Case Url
    Entry changedEntry5 = new Entry(8, "test7Com", "test8", "test8@gmail.com", "changedpw8");
    Assertions.assertThrows(IllegalParameterException.class, () -> {
      pwManagerService.changeEntry(changedEntry5,pwManagerService.masterPw);
    });
  }

  @Test
  void testDeleteEntry() throws IllegalParameterException, IllegalMasterPasswordException {
    pwManagerService.addEntry( "test.com", "test", "test@gmail.com", "testpw");
    pwManagerService.addEntry( "test2.com", "test2", "test2@gmail.com", "test2pw");

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
    pwManagerService.addEntry( "test.com", "test", "test@gmail.com", "testPw");
    boolean x = pwManagerService.listOfEntrys.get(0).isChangeHidden();
    pwManagerService.changeHidden(0);

    //Good Case
    assertNotEquals(x,pwManagerService.listOfEntrys.get(0).isChangeHidden());

    //Bad Case
    Assertions.assertThrows(IllegalParameterException.class, () -> {
      pwManagerService.changeHidden(5);
    });
  }

  @Test
  void testGenerateNewPw() throws IllegalParameterException{
    //Good Case
    String password = pwManagerService.generateNewPw(true,true,true,true,true,12);
    assertEquals(12,password.length());

    //Bad Case
    Assertions.assertThrows(IllegalParameterException.class, () -> {
      pwManagerService.generateNewPw(true,true,true,true,true, 0);
    });

     }

  @Test
  void testGetLoadStateGoodCase()
      throws NullPointerException, IllegalParameterException, IllegalMasterPasswordException {
    pwManagerService.addEntry("test.com", "test", "test@gmail.com", "testPw");
    pwManagerService.addEntry("test2.com", "test2", "test2@gmail.com", "test2Pw");

    /*
    List<Entry> testlist = pwManagerService.getState();
    assertEquals(2,testlist.size());

    pwManagerService.deleteEntry(1,pwManagerService.masterPw);
    pwManagerService.loadState(testlist);
    assertEquals(1,pwManagerService.listOfEntrys.size());
*/
  }

  @Test
  void testGetLoadStateBadCase()
      throws NullPointerException{
    pwManagerService.listOfEntrys = null;
/*
    Assertions.assertThrows(NullPointerException.class, () -> {
      pwManagerService.getState();
    });

    Assertions.assertThrows(NullPointerException.class, () -> {
      pwManagerService.loadState(pwManagerService.listOfEntrys);
    });
*/
  }

  @Test
  void testGetLoadState() throws NullPointerException, IllegalParameterException, IOException {

    //Good Case
    pwManagerService.addEntry("example.com", "example", "test@gmail.com", "testPw");
    pwManagerService.addEntry("whynot.com", "test2", "test2@gmail.com", "test2Pw");
    pwManagerService.addEntry("www.google.de", "Perry", "momoperkru@web.de", "sad234/(&%!ASD");
    pwManagerService.getState(pwManagerService.listOfEntrys);

    SimplePwManagerService anotherPwManager = new SimplePwManagerService();
    anotherPwManager.loadState();
    assertEquals(0,anotherPwManager.listOfEntrys.size());

    //Bad Case
    pwManagerService.listOfEntrys = null;
    Assertions.assertThrows(NullPointerException.class, () -> {
      pwManagerService.getState(pwManagerService.listOfEntrys);
    });
  }

  @Test
  void testSetMasterPassword(){
    String mp = pwManagerService.masterPw;
    pwManagerService.setMasterPw("firns7"); //firns7 ist admin2 verschlüsselt

    assertEquals("admin2",pwManagerService.masterPw);
  }

}

