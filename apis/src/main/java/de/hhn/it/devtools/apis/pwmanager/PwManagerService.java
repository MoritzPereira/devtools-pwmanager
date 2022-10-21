package de.hhn.it.devtools.apis.pwmanager;

import java.util.ArrayList;

public interface PwManagerService {

  String masterPw = "test";
  boolean hidepws = true;

  /**
   * changes the Master Password.
   *
   * @param password the new master password.
   */
  void changeMasterPw(String password);

  /**
   * Logs in the user and gives access to the passwords
   * @param masterPw Master password to get access
   */
  void login(String masterPw);

  /**
   * Logs out the user
   */
  void logout();

  /**
   * changes the visibility of the passwords.
   */
  void changeHidden();


  /**
   * Creates a new Entry and loads the entry in the file.
   *
   * @param id       for the associated password.
   * @param url      for the associated password.
   * @param username for the associated password.
   * @param email    for the associated password.
   * @param password
   * @return the created entry.
   */
  public Entry addEntry(int id, String url, String username, String email, String password);

  /**
   * Changes an entry and loads the entry in the file.
   *
   * @param id
   * @param url      of the changed entry.
   * @param username of the changed entry.
   * @param email    of the changed entry.
   * @param password of the changed entry
   */
  public void changeEntry(int id, String url, String username, String email, String password);

  /**
   * Deletes an entry.
   *
   * @param entry that will be deleted.
   */
  public void deleteEntry(Entry entry);

  /**
   * Generates a new password with the given specs.
   *
   * @param useUpper signals if uppercase letters
   */
  public String generateNewPw(boolean useUpper, boolean useLower, boolean useDigits, boolean useSpecialChars);

  /**
   * Encrypts a single entry and loads the entry in the file.
   */
  public void exportToFile();

  /**
   * Reads the encrypted entries from the file and puts the decrypted entries in the list.
   * @return a list with decrypted entries
   */
  public ArrayList<Entry> importFromFile();

}
