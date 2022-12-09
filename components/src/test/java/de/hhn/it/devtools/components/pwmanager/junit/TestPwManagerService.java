package de.hhn.it.devtools.components.pwmanager.junit;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.pwmanager.PwManagerListener;
import de.hhn.it.devtools.apis.pwmanager.PwManagerService;
import de.hhn.it.devtools.apis.pwmanager.exceptions.IllegalMasterPasswordException;
import de.hhn.it.devtools.components.pwmanager.provider.SimplePwManagerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class TestPwManagerService {

  private SimplePwManagerService pwManagerService;
  private DummyListener pwManagerListener;
  private String masterPw;


  @BeforeEach
  void setup() {

    pwManagerService = new SimplePwManagerService();
    masterPw = "admin";
    pwManagerListener = new DummyListener();

  }



  @Test
  /**
   * Tests if listeners get added correctly.
   */
  void testAddListener() {

    //Good Case


    //Bad Case
    Assertions.assertThrows(NullPointerException.class, ()->{
      pwManagerService.addListener(pwManagerListener);
    });

  }

  @Test
  void testChangeMasterPw(){

    Assertions.assertThrows(IllegalMasterPasswordException.class, ()->{
      pwManagerService.changeMasterPw("secret", "admin");
    });

    Assertions.assertThrows(IllegalParameterException.class, ()->{
      pwManagerService.changeMasterPw("secret", masterPw);
      masterPw = "secret";
    });

  }



}
