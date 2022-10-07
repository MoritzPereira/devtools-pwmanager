package de.hhn.it.devtools.apis.pwmanager;

import de.hhn.it.devtools.apis.pwmanager.PwManagerService;
import de.hhn.it.devtools.apis.pwmanager.PwManagerListener;

public class PwManagerUsageDemo {

    public static void main(String[] args){

        // Set up PwManager
        PwManagerService pwManagerService = null;
        PwManagerListener pwManagerListener = null;
        pwManagerService.addEntry("c-shark.de","Swifty","shasse@stud.hs-heilbronn.de","Floistcool");
        pwManagerService.changeMasterPw("Thatsthemasterpw");


    }
}
