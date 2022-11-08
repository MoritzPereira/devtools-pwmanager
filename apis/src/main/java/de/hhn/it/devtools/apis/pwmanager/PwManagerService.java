package de.hhn.it.devtools.apis.pwmanager;

import java.util.ArrayList;

/**
 * Pw-Manager Service.
 */
public interface PwManagerService {

  String masterPw = "test";
  boolean hidepws = true;

  /**
   * changes the Master Password.
   *
   * @param password the new master password.
   * @throws RuntimeException if method takes too much time.
   */
  void changeMasterPw(String password) throws RuntimeException;

  /**
   * Logs in the user and gives access to the passwords.
   *
   * @param masterPw Master password to get access.
   * @throws RuntimeException if method takes too much time.
   */
  void login(String masterPw) throws RuntimeException;

  /**
   * Logs out the user.
   *
   * @throws RuntimeException if method takes too much time.
   */
  void logout() throws RuntimeException;

  /**
   * changes the visibility of a single password.
   *
   * @param id of the Entry.
   * @return the decrypted password.
   * @throws RuntimeException if method takes too much time.
   */
  String changeHidden(int id) throws RuntimeException;


  /**
   * Creates a new Entry and loads the entry in the file.
   *
   * @param id       for the associated password.
   * @param url      for the associated password.
   * @param username for the associated password.
   * @param email    for the associated password.
   * @param password for the associated password.
   * @return the created entry.
   * @throws RuntimeException if method takes too much time
   */
  public Entry addEntry(int id, String url, String username, String email, String password)
      throws RuntimeException;

  /**
   * Changes an entry and loads the entry in the file.
   *
   * @param entry that will be changed
   * @throws RuntimeException if method takes too much time
   */
  public void changeEntry(Entry entry) throws RuntimeException;

  /**
   * Deletes an entry.
   *
   * @param id of the entry that will be deleted.
   * @throws RuntimeException if method takes too much time
   */
  public void deleteEntry(int id) throws RuntimeException;

  /**
   * Generates a new password with the given specs.
   *
   * @param useUpper signals if uppercase letters
   * @throws RuntimeException if method takes too much time
   */
  public String generateNewPw(boolean useUpper, boolean useLower, boolean useDigits,
                              boolean useSpecialChars) throws RuntimeException;

  /**
   * Gets the state from the component
   *
   * @return the state
   * @throws RuntimeException if method takes too much time
   */
  public ArrayList<Entry> getState() throws RuntimeException;

  /**
   * Loads the state in the component.
   *
   * @throws RuntimeException if method takes too much time
   */
  public void loadState(ArrayList<Entry> state) throws RuntimeException;

}
