package de.hhn.it.devtools.apis.pwmanager;

public class Entry {

  private int entryId;
  private String url;
  private String username;
  private String email;
  private String password;

  public Entry(int id, String url, String username, String email, String password) {
    this.entryId = id;
    this.url = url;
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword() {
    return password;
  }

  public int getEntryId() {
    return entryId;
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
