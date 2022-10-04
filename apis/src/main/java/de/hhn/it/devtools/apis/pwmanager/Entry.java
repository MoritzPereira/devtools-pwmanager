package de.hhn.it.devtools.apis.pwmanager;

public class Entry {

    private String url;
    private String username;
    private String email;
    private String password;

    public Entry(String url, String username, String email, String password){

        this.url = url;
        this.username = username;
        this.email = email;
        this.password = encrypt(password);
        
    }

    private void changeUrl(String url){

    }
    private void changeUsername(String username){

    }
    private void changeEmail(String email){

    }
    private void changePassword(String password){

    }

    private String encrypt(String password){
        return "HASHEDPW";
    }

    private String decrypt(String password){
        return "NORMALPW";
    }

    public String getPassword() {
        return decrypt(password);
    }
}
