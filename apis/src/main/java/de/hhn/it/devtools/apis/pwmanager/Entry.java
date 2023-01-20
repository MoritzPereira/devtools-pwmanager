package de.hhn.it.devtools.apis.pwmanager;

/**
 * Class of an Entry
 *
 */
public class Entry {

  private int entryId;
  private String url;
  private String username;
  private String email;
  private String password;

  private boolean changeHidden = false;

  /**
   * Constructor of an entry.
   *
   * @param id of the entry
   * @param url of the entry
   * @param username of the entry
   * @param email of the entry
   * @param password of the entry
   */
  public Entry(int id, String url, String username, String email, String password) {
    this.entryId = id;
    this.url = url;
    this.username = username;
    this.email = email;
    this.password = password;
  }

  /**
   * Sets the url for the entry.
   *
   * @param url of the entry
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * Sets the username for the entry.
   *
   * @param username of the entry
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Sets the email for the entry.
   *
   * @param email of the entry
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Sets the password for the entry.
   *
   * @param password of the entry
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Returns the password for the entry.
   *
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Returns the id for the entry.
   *
   * @return the id
   */
  public int getEntryId() {
    return entryId;
  }

  /**
   * Returns the url for the entry.
   *
   * @return the url
   */
  public String getUrl() {
    return url;
  }

  /**
   * Returns the username for the entry.
   *
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Returns the email for the entry.
   *
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the visibility of the entry.
   *
   * @param changeHidden if visibility of the entry is true
   */
  public void setChangeHidden(boolean changeHidden) {
    this.changeHidden = changeHidden;
  }

  /**
   * Returns the visibility of the entry.
   *
   * @return the visibility
   */
  public boolean isChangeHidden() {
    return changeHidden;
  }
}
