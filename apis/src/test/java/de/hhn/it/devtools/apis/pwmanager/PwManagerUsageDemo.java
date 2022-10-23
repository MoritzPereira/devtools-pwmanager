package de.hhn.it.devtools.apis.pwmanager;

public class PwManagerUsageDemo {

    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(PwManagerUsageDemo.class);

    public static void main(String[] args) {

        // Set up PwManager
        PwManagerService pwManagerService = null;
        PwManagerListener pwManagerListener = null;

        //login to PwManager
        pwManagerService.login("test");
        logger.info("Logged in");

        //changes the master password
        pwManagerService.changeMasterPw("Thatsthemasterpw");
        logger.info("Master password changed");

        //Adds an Entry
        Entry entry = pwManagerService.addEntry(2, "c-shark.de", "Swifty", "shasse@stud.hs-heilbronn.de", "Floistcool");
        logger.info("Added new entry - id:"+entry.getEntryId());

        //changes the visibility of the passwords
        pwManagerService.changeHidden(2);
        logger.info("Visibility of the password of entry:" + entry.getEntryId() + "changed");

        // Generate new Pw and change Entry
        String newpw = pwManagerService.generateNewPw(true, true, true, true);
        entry.changePassword(newpw);
        logger.info("Changed the password of the entry - id:"+ entry.getEntryId());
        pwManagerService.changeEntry(entry);
        logger.info("Loads the changed entry in the file");

        //Delete an Entry
        pwManagerService.deleteEntry(2);
        logger.info("Deletes the entry - id:"+entry.getEntryId());

        //Logout
        pwManagerService.logout();
        logger.info("Sucessfully logged out");

    }
}
