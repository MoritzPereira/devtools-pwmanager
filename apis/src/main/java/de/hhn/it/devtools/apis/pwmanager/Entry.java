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
        this.password = hash(password);
        
    }

    private void changeUrl(String url){

    }
    private void changeUsername(String username){

    }
    private void changeEmail(String email){

    }
    private void changePassword(String password){

    }

    private String hash(String password){
        return "HASHEDPW";
    }

}
