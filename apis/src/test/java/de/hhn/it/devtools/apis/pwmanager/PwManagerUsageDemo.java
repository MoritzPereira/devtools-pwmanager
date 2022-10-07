package de.hhn.it.devtools.apis.pwmanager;

import de.hhn.it.devtools.apis.pwmanager.PwManagerService;
import de.hhn.it.devtools.apis.pwmanager.PwManagerListener;

public class PwManagerUsageDemo {

    public static void main(String[] args){

        // Set up PwManager
        PwManagerService pwManagerService = null;
        PwManagerListener pwManagerListener = null;

        //changes the master password
        pwManagerService.changeMasterPw("Thatsthemasterpw");

        //changes the visibility of the passwords
        pwManagerService.changeHidden();

        //Adds an Entry
        Entry entry = pwManagerService.addEntry(2,"c-shark.de","Swifty","shasse@stud.hs-heilbronn.de","Floistcool");

        // Generate new Pw and change Entry
        String newpw = pwManagerService.generateNewPw(true,true,true,true);
        entry.changePassword(newpw);
        pwManagerService.changeEntry(entry.getId(), entry.getUrl(), entry.getUsername(), entry.getEmail(), entry.getPassword());

        //Delete an Entry
        pwManagerService.deleteEntry(entry);





    }
}
