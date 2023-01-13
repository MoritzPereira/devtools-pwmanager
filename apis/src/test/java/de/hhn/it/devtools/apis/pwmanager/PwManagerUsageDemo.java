package de.hhn.it.devtools.apis.pwmanager;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.pwmanager.exceptions.IllegalMasterPasswordException;

public class PwManagerUsageDemo {

    private static final org.slf4j.Logger logger =
        org.slf4j.LoggerFactory.getLogger(PwManagerUsageDemo.class);

    public static void main(String[] args) throws IllegalMasterPasswordException, IllegalParameterException {

        // Set up PwManager
        PwManagerService pwManagerService = null;
        PwManagerListener pwManagerListener = null;

        //login to PwManager
        pwManagerService.login("admin");
        logger.info("Logged in");

        //changes the master password
        pwManagerService.changeMasterPw("Thatsthemasterpw", "admin");
        logger.info("Master password changed");

        //Adds an Entry
        Entry entry = pwManagerService.addEntry("c-shark.de", "Swifty", "shasse@stud.hs-heilbronn.de", "Floistcool");
        logger.info("Added new entry - id:"+entry.getEntryId());

        //changes the visibility of the passwords
        pwManagerService.changeHidden(2);
        logger.info("Visibility of the password of entry:" + entry.getEntryId() + "changed");

        // Generate new Pw and change Entry
        String newpw = pwManagerService.generateNewPw(true, true, true, true, 8);
        entry.setPassword(newpw);
        logger.info("Changed the password of the entry - id:"+ entry.getEntryId());
        pwManagerService.changeEntry(entry, "Thatsthemasterpw");
        logger.info("Loads the changed entry in the file");

        //Delete an Entry
        pwManagerService.deleteEntry(2, "Thatsthemasterpw");
        logger.info("Deletes the entry - id:"+entry.getEntryId());

        //Logout
        pwManagerService.logout();
        logger.info("Sucessfully logged out");

    }
}
