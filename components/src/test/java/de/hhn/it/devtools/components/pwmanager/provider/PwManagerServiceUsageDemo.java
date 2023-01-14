package de.hhn.it.devtools.components.pwmanager.provider;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.pwmanager.Entry;
import de.hhn.it.devtools.apis.pwmanager.exceptions.IllegalMasterPasswordException;

public class PwManagerServiceUsageDemo {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(PwManagerServiceUsageDemo.class);


  public static void main(String[] args)
      throws IllegalStateException, IllegalMasterPasswordException, IllegalParameterException {

    SimplePwManagerService pwManagerService = new SimplePwManagerService();

    //Login with MasterPw
    pwManagerService.login("admin");
    logger.info("-----Welcome to our Password Manager");

    //Add new Entries
    pwManagerService.addEntry("test.de","TestUser1","test@gmail.com","testPw");
    pwManagerService.addEntry("testtest.net","TestUser2","test@gmx.de","securePw");
    logger.info("-----New Entries added");

    //Delete an Entry
    pwManagerService.deleteEntry(1,"admin");
    logger.info("-----Entry deleted");

    //Change an Entry and generate a new Password
    pwManagerService.changeEntry(new Entry(0,"changedTest.de","changedTestUser1","changedtest@gmail.com",pwManagerService.generateNewPw(true,true,true,true,12)
    ),"admin");
    logger.info("-----Entry changed with new generated Password");

    //Change Hidden from an Entry
    pwManagerService.changeHidden(0);
    logger.info("-----Hidden changed");

    //Change Masterpw
    pwManagerService.changeMasterPw("newadmin","admin");
    logger.info("-----Master Password changed");

    //Logout
    pwManagerService.logout();
    logger.info("-----Logged out");

    //Login with new Master Password
    pwManagerService.login("newadmin");
    logger.info("-----Logged in with new Master Password");
  }


}
