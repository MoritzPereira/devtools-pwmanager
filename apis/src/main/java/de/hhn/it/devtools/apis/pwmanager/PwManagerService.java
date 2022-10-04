package de.hhn.it.devtools.apis.pwmanager;

public interface PwManagerService {

    String masterPw = "test";
    boolean hidepws = true;

    /**
     * changes the Master Password.
     * @param password the new master password.
     */
    private void changeMasterPw(String password){

    }

    /**
     * changes the visibility of the passwords.
     */
    private void changeHidden(){

    }
}
