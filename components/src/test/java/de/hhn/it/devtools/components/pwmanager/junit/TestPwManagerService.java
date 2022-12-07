package de.hhn.it.devtools.components.pwmanager.junit;

import de.hhn.it.devtools.apis.pwmanager.PwManagerListener;
import de.hhn.it.devtools.apis.pwmanager.PwManagerService;
import de.hhn.it.devtools.components.pwmanager.provider.SimplePwManagerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class TestPwManagerService {

  private SimplePwManagerService pwManagerService;


  @BeforeEach
  void setup() {
    pwManagerService = new SimplePwManagerService();
  }



  @Test
  /**
   * Tests if listeners get added correctly.
   */
  void testAddListener() {
    PwManagerListener listener = null;

    //Good Case


    //Bad Case
    Assertions.assertThrows(NullPointerException.class, ()->{
      pwManagerService.addListener(listener);
    });

  }
}
