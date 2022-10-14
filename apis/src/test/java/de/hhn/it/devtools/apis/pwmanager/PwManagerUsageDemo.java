package de.hhn.it.devtools.apis.pwmanager;

import de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerServiceUsageDemo;
import de.hhn.it.devtools.apis.pwmanager.PwManagerService;
import de.hhn.it.devtools.apis.pwmanager.PwManagerListener;

public class PwManagerUsageDemo {

    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(PwManagerUsageDemo.class);

    public static void main(String[] args){

        // Set up PwManager
        PwManagerService pwManagerService = null;
        PwManagerListener pwManagerListener = null;

        //changes the master password
        pwManagerService.changeMasterPw("Thatsthemasterpw");
        logger.info("Master password changed");

        //changes the visibility of the passwords
        pwManagerService.changeHidden();
        logger.info("Visibility of the passwords changed");

        //Adds an Entry
        Entry entry = pwManagerService.addEntry(2,"c-shark.de","Swifty","shasse@stud.hs-heilbronn.de","Floistcool");
        logger.info("Added new entry - id:"+entry.getId());

        // Generate new Pw and change Entry
        String newpw = pwManagerService.generateNewPw(true,true,true,true);
        entry.changePassword(newpw);
        logger.info("Changed the password of the entry - id:"+ entry.getId());
        pwManagerService.changeEntry(entry.getId(), entry.getUrl(), entry.getUsername(), entry.getEmail(), entry.getPassword());
        logger.info("Loads the changed entry in the file");

        //Delete an Entry
        pwManagerService.deleteEntry(entry);
        logger.info("Deletes the entry - id:"+entry.getId());

    }
}
