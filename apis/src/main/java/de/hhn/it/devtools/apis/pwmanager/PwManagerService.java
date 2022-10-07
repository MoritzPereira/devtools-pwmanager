package de.hhn.it.devtools.apis.pwmanager;

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
   * changes the visibility of the passwords.
   */
  void changeHidden();


  /**
   * Creates a new Entry.
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
   * Changes an entry.
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




}
