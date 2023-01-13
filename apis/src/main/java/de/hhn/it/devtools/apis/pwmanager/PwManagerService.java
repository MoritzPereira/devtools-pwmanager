package de.hhn.it.devtools.apis.pwmanager;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.pwmanager.exceptions.IllegalMasterPasswordException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Pw-Manager Service.
 *
 */
public interface PwManagerService {

  public String masterPw = "admin";
  boolean hidepws = true;

  /**
   * changes the Master Password.
   *
   * @param newPassword the new master password
   * @param oldPassword to check if user is authenticated to change master password
   * @throws IllegalMasterPasswordException if user is not authenticated (oldPassword != masterPw)
   * @throws IllegalParameterException      if the oldPassword and newPassword are equal
   */
  void changeMasterPw(String newPassword, String oldPassword)
      throws IllegalMasterPasswordException, IllegalParameterException;

  /**
   * Logs in the user and gives access to the passwords.
   *
   * @param masterPw Master password to get access
   * @throws IllegalMasterPasswordException if user is not authenticated
   */
  void login(String masterPw) throws IllegalMasterPasswordException;

  /**
   * Logs out the user.
   */
  void logout();

  /**
   * changes the visibility of a single password.
   *
   * @param id of the Entry
   * @return the decrypted password
   * @throws IllegalParameterException if the id does not exist
   */
  String changeHidden(int id) throws IllegalParameterException;


  /**
   * Creates a new Entry and loads the entry in the file.
   *
   * @param id       for the associated password.
   * @param url      for the associated password.
   * @param username for the associated password.
   * @param email    for the associated password.
   * @param password for the associated password.
   * @return the created entry.
   * @throws IllegalParameterException if any attributs are invalid
   */
  public Entry addEntry(int id, String url, String username, String email, String password)
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
                              boolean useSpecialChars, int length) throws IllegalParameterException;

  /**
   * Gets the state from the component
   *
   * @return the state.
   * @throws NullPointerException if list doesn't exist
   */
  public void getState(List<Entry> state) throws NullPointerException, IOException;

  /**
   * Loads the state in the component.
   *
   * @throws NullPointerException if list doesn't exist
   */
  public List<Entry> loadState() throws NullPointerException;

}
