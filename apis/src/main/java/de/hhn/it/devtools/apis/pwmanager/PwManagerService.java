package de.hhn.it.devtools.apis.pwmanager;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.pwmanager.exceptions.IllegalMasterPasswordException;
import java.io.IOException;
import java.util.List;

/**
 * Pw-Manager Service.
 */
public interface PwManagerService {

  public String masterPw = "admin";
  boolean hidepws = true;

  /**
   * Adds a Listener to a list of listeners.
   *
   * @param listener which gets added
   * @throws IllegalParameterException if listener is null or already in the list
   */
  void addListener(PwManagerListener listener) throws IllegalParameterException;

  /**
   * changes the Master Password.
   *
   * @param newPassword the new master password
   * @param oldPassword to check if user is authenticated to change master password
   * @throws IllegalMasterPasswordException if user is not authenticated (oldPassword != masterPw)
   * @throws IllegalParameterException      if the oldPassword and newPassword are equal
   */
  void changeMasterPw(String newPassword, String repeatedNewPassword, String oldPassword)
      throws IllegalMasterPasswordException, IllegalParameterException;

  /**
   * Logs in the user and gives access to the passwords.
   *
   * @param masterPw Master password to get access
   * @throws IllegalMasterPasswordException if user is not authenticated
   */
  void login(String masterPw) throws IllegalMasterPasswordException;

  /**
   * Sets the variable loggedIn.
   *
   * @param loggedIn variable
   */
  void setLoggedIn(boolean loggedIn);

  /**
   * Logs out the user.
   */
  void logout();

  /**
   * changes the visibility of a single password.
   *
   * @param id of the Entry
   * @throws IllegalParameterException if the id does not exist
   */
  public void changeHidden(int id) throws IllegalParameterException;


  /**
   * Creates a new Entry and loads the entry in the file.
   *
   * @param url      for the associated password.
   * @param username for the associated password.
   * @param email    for the associated password.
   * @param password for the associated password.
   * @return the created entry.
   * @throws IllegalParameterException if any attributs are invalid
   */
  public Entry addEntry(String url, String username, String email, String password,
                        String repeatedPassword)
      throws IllegalParameterException;

  /**
   * Changes an entry and loads the entry in the file.
   *
   * @param entry that will be changed
   * @throws IllegalParameterException      if the chosen entry does not exist
   * @throws IllegalMasterPasswordException if user is not authenticated
   */
  public void changeEntry(Entry entry, String masterPw)
      throws IllegalParameterException, IllegalMasterPasswordException;

  /**
   * Deletes an entry.
   *
   * @param id of the entry that will be deleted
   * @throws IllegalParameterException      if the chosen id does not exist
   * @throws IllegalMasterPasswordException if user is not authenticated
   */
  public void deleteEntry(int id, String masterPw)
      throws IllegalParameterException, IllegalMasterPasswordException;

  /**
   * Generates a new password with the given specs.
   *
   * @param useUpper signals if uppercase letters
   */
  public String generateNewPw(boolean useUpper, boolean useLower, boolean useDigits,
                              boolean useSpecialChars, boolean useCyrillic, int length)
      throws IllegalParameterException;

  /**
   * Gets the state from the component.
   *
   * @throws NullPointerException if list doesn't exist
   */
  public void getState(List<Entry> state) throws NullPointerException, IOException;

  /**
   * Decrypts an entry given.
   *
   * @param url      of the entry
   * @param username of the entry
   * @param email    of the entry
   * @param password of the entry
   */
  void decryptAndLoadEntries(String url, String username, String email, String password);

  /**
   * Sets the masterpassword.
   *
   * @param masterPw that should be set.
   */
  void setMasterPw(String masterPw);
}
