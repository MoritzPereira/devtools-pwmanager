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
        this.password = password;
        
    }

    public void changeUrl(String url){

    }
    public void changeUsername(String username){

    }
    public void changeEmail(String email){

    }
    public void changePassword(String password){

    }

    public String getPassword() {
        return password;
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

    public void encryptAll(){
        id = Integer.parseInt(encrypt(String.valueOf(id)));
        url = encrypt(url);
        username = encrypt(username);
        email = encrypt(email);
        password = encrypt(password);
    }

    public void decryptAll(){
        id = Integer.parseInt(decrypt(String.valueOf(id)));
        url = decrypt(url);
        username = decrypt(username);
        email = decrypt(email);
        password = decrypt(password);
    }

    private String encrypt(String text){
        StringBuilder hashedString = new StringBuilder();
        char[]        chars        = text.toCharArray();
        for (char c : chars) {
            c += 15;
            hashedString.append(c);
        }
        return hashedString.toString();
    }

    private String decrypt(String text){
        StringBuilder result = new StringBuilder();
        char[]        chars  = text.toCharArray();
        for (char c : chars) {
            c -= 15;
            result.append(c);
        }
        return result.toString();
    }

}
