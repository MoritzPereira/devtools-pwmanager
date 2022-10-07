package de.hhn.it.devtools.apis.pwmanager;

public class Entry {

    private int id;
    private String url;
    private String username;
    private String email;
    private String password;

    public Entry(int id, String url, String username, String email, String password){

        this.id = id;
        this.url = url;
        this.username = username;
        this.email = email;
        this.password = encrypt(password);
        
    }

    public void changeUrl(String url){

    }
    public void changeUsername(String username){

    }
    public void changeEmail(String email){

    }
    public void changePassword(String password){

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

    public int getId(){
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
