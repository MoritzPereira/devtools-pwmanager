package de.hhn.it.devtools.components.pwmanager.provider;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.pwmanager.Entry;
import de.hhn.it.devtools.apis.pwmanager.PwManagerListener;
import de.hhn.it.devtools.apis.pwmanager.exceptions.IllegalMasterPasswordException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Service Class in which all the logic happens.
 */
public class SimplePwManagerService implements de.hhn.it.devtools.apis.pwmanager.PwManagerService {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimplePwManagerService.class);
  public String masterPw = "admin";

  int idstatus = 0;
  public boolean loggenIn = false;
  public ArrayList<Entry> listOfEntrys = new ArrayList<>();
  private List<PwManagerListener> listeners = new ArrayList<>();


  /**
   * Adds a Listener to a list of listeners.
   *
   * @param listener which gets added
   * @throws IllegalParameterException if listener is null or already in the list
   */
  public void addListener(final PwManagerListener listener) throws IllegalParameterException {
    /*if (listener != null) {
      listeners.add(listener);
    } else {
      throw new NullPointerException("Listener doesn't exist");
    }*/

    if (listener == null) {
      logger.error("Listener was null reference");
      throw new IllegalParameterException("Listener was null reference");
    }

    if (listeners.contains(listener)) {
      logger.error("Listener already registered");
      throw new IllegalParameterException("Listener already registered");
    }
    logger.info("Listener " + listener.toString() + " added");
    listeners.add(listener);

  }

  public void removeListener(final PwManagerListener listener) throws IllegalParameterException {
    /*if (listener != null) {
      listeners.remove(listener);
    } else {
      throw new NullPointerException("Listener doesn't exist");
    }*/

    if (listener == null) {
      logger.error("Listener was null reference");
      throw new IllegalParameterException("Listener was null reference");
    }

    if (!listeners.contains(listener)) {
      logger.error("Listener is not registered:" + listener);
      throw new IllegalParameterException("Listener is not registered:" + listener);
    }
    logger.info("Listener " + listener.toString() + " removed");
    listeners.remove(listener);
  }

  /**
   * Checks if the given password is in common with our safety rules.
   *
   * @param password which is checked
   * @return true or false if the password is valid or not
   */
  public boolean checkPassword(String password) {
//bissle mehr überprüfungen so weisch
    int length = 4;
    if (password.length() < length) {
      return false;
    } else {
      return true;
    }

  }

  /**
   * Checks if the given email is valid.
   *
   * @param email which is checked
   * @return true or false if the email is valid or not
   */
  public boolean checkEmail(String email) {

    boolean foundA = false; //@
    boolean foundB = false; //.

    for (int i = 0; i < email.length(); i++) {
      char ch = email.charAt(i);
      if (ch == '@') {
        foundA = true;
      } else if (ch == '.') {
        foundB = true;
      }
    }

    if (foundA && foundB) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks if the given url is valid.
   *
   * @param url which is checked
   * @return true or false if the url is valid or not
   */
  public boolean checkUrl(String url) {

    for (int i = 0; i < url.length(); i++) {
      char ch = url.charAt(i);
      if (ch == '.') {
        return true;
      }
    }
    return false;
  }

  /**
   * Changes the masterpassword.
   *
   * @param newPassword the new master password
   * @param oldPassword to check if user is authenticated to change master password
   * @throws IllegalMasterPasswordException if the masterpassword entered is wrong
   * @throws IllegalParameterException      if the given password equals the old one or is too weak
   * @throws NullPointerException           if the password is null
   */
  @Override
  public void changeMasterPw(String newPassword, String oldPassword)
      throws IllegalMasterPasswordException, IllegalParameterException {

    if (!Objects.equals(this.masterPw, oldPassword)) {
      throw new IllegalMasterPasswordException();
    } else if (newPassword == null) {
      logger.error("Password is null");
      throw new NullPointerException("Password is null");
    } else if (Objects.equals(newPassword, oldPassword)) {
      logger.error("Dont use the same password again");
      throw new IllegalParameterException("Dont use the same password again");
    } else if (!checkPassword(newPassword)) {
      logger.error("New password is too weak");
      throw new IllegalParameterException("New password is too weak");
    }
    logger.info("Changed master password");
    this.masterPw = newPassword;
  }

  /**
   * This method is for checking if the user is verifed to do some stuff like edit entry, delete entry.
   *
   * @param masterPw Master password to get access
   * @throws IllegalMasterPasswordException if the user enters the wrong masterpasword
   */
  @Override
  public void login(String masterPw) throws IllegalMasterPasswordException {
    if (Objects.equals(this.masterPw, masterPw)) {
      loggenIn = true;
      logger.info("Logged in");
      listeners.forEach(PwManagerListener::loggedin);
    } else {
      throw new IllegalMasterPasswordException();
    }
  }

  /**
   * This method is only for the loginController to verfiy the login.
   *
   * @param password
   * @param encryptedPassword
   * @throws IllegalMasterPasswordException if the user enters the wrong masterpasword
   */
  public void loginFromLoginController(String password, String encryptedPassword)
      throws IllegalMasterPasswordException {
    if (Objects.equals(password, decrypt(encryptedPassword))) {
      logger.info("Logged in");
      listeners.forEach(PwManagerListener::loggedin);
    } else {
      throw new IllegalMasterPasswordException();
    }
  }

  /**
   * Logs the user out.
   */
  @Override
  public void logout() {
    getState(listOfEntrys);
    listeners.forEach((listener) -> listener.loggedout());
    loggenIn = false;
    logger.info("Logged out");
  }


  /**
   * Adds an entry to the list of entries, which is then displayed.
   *
   * @param url      for the associated password.
   * @param username for the associated password.
   * @param email    for the associated password.
   * @param password for the associated password.
   * @return the entry added
   * @throws IllegalParameterException if email, url, or password are not valid
   */
  @Override
  public Entry addEntry(String url, String username, String email, String password)
      throws IllegalParameterException {

    if (!checkEmail(email)) {
      logger.error("Email is not valid");
      throw new IllegalParameterException("Email is not valid");
    }
    if (!checkUrl(url)) {
      logger.error("Url is not valid");
      throw new IllegalParameterException("Url is not valid");
    }
    if (!checkPassword(password)) {
      logger.error("Password is not valid");
      throw new IllegalParameterException("Password is not valid");
    }

    Entry newEntry = new Entry(idstatus, url, username, email, password);
    logger.info("New entry {id: " + idstatus + " } added");
    idstatus++;
    listOfEntrys.add(newEntry);
    listeners.forEach((listener) -> listener.showsortedEntryList(listOfEntrys));
    return newEntry;
  }

  /**
   * Changes an entry.
   *
   * @param entry    that will be changed
   * @param masterPw to verify it is you changing your data
   * @throws IllegalParameterException      if the new email, url or password aren't valid
   * @throws IllegalMasterPasswordException if the masterpassword is wrong
   */
  @Override
  public void changeEntry(Entry entry, String masterPw)
      throws IllegalParameterException, IllegalMasterPasswordException {

    boolean found = false;
    int id = 0;

    if (!Objects.equals(this.masterPw, masterPw)) {
      throw new IllegalMasterPasswordException();
    }
    if (!checkEmail(entry.getEmail())) {
      logger.error("Email is not valid");
      throw new IllegalParameterException("Email is not valid");
    }
    if (!checkUrl(entry.getUrl())) {
      logger.error("Url is not valid");
      throw new IllegalParameterException("Url is not valid");
    }
    if (!checkPassword(entry.getPassword())) {
      logger.error("Password is not valid");
      throw new IllegalParameterException("Password is not valid");
    }
    for (Entry i : listOfEntrys) {
      if (i.getEntryId() == entry.getEntryId()) {
        id = i.getEntryId();
        found = true;
        i.setUrl(entry.getUrl());
        i.setUsername(entry.getUsername());
        i.setEmail(entry.getEmail());
        i.setPassword(entry.getPassword());
      }
    }
    listeners.forEach((listener) -> listener.showsortedEntryList(listOfEntrys));

    if (!found) {
      throw new IllegalParameterException("Entry not found");
    } else {
      logger.info("Entry {id: " + id + " } changed");
    }
  }

  /**
   * Deletes an entry.
   *
   * @param id       of the entry that will be deleted
   * @param masterPw to check it is you deleting data
   * @throws IllegalParameterException      if the entry was not found
   * @throws IllegalMasterPasswordException if the masterpassword is wrong
   */
  @Override
  public void deleteEntry(int id, String masterPw)
      throws IllegalParameterException, IllegalMasterPasswordException {

    boolean foundId = false;

    if (!Objects.equals(this.masterPw, masterPw)) {
      throw new IllegalMasterPasswordException();
    }

    Iterator it = listOfEntrys.iterator();
    while (it.hasNext()) {
      Entry entry = (Entry) it.next();
      if (id == entry.getEntryId()) {
        foundId = true;
        it.remove();
        logger.info("Entry {id: " + id + " } deleted");
        listeners.forEach((listener) -> listener.showsortedEntryList(listOfEntrys));
        break;
      }
    }

    if (!foundId) {
      throw new IllegalParameterException("Entry not found");
    }

  }

  /**
   * Changes the visibility of an entry.
   *
   * @param id of the Entry whos visibility gets changed
   * @throws IllegalParameterException if the entry was not found
   */
  @Override
  public void changeHidden(int id) throws IllegalParameterException {

    boolean foundId = false;

    for (Entry i : listOfEntrys) {
      if (i.getEntryId() == id) {
        if (i.isChangeHidden()) {
          i.setChangeHidden(false);
          logger.info("Hidden changed from id - " + id + " to false");
        } else {
          i.setChangeHidden(true);
          logger.info("Hidden changed from id - " + id + " to true");
        }
        foundId = true;
        break;
      }
    }
    //Check if the id exist
    if (!foundId) {
      logger.error("Given id not found");
      throw new IllegalParameterException("Given id not found");
    } else {
      listeners.forEach((listener) -> listener.showsortedEntryList(listOfEntrys));
    }
  }

  /**
   * Generates a new Password with the given options.
   *
   * @param useUpper             signals if uppercase letters should be used
   * @param useLower             signals if lowercase letters should be used
   * @param useDigits            signals if digits should be used
   * @param useSpecialCharacters signals if special characters should be used
   * @param useCyrillic          signals if cyrillic letters should be used
   * @param length               sets the length of the generated password
   * @return the generated password
   * @throws IllegalParameterException if length is to short
   */
  @Override
  public String generateNewPw(boolean useUpper, boolean useLower, boolean useDigits,
                              boolean useSpecialCharacters, boolean useCyrillic, int length)
      throws IllegalParameterException {

    final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final String DIGITS = "0123456789";
    final String SpecialCharacters = "!@#$%&*()_+-=[]|,./?><";
    final String Cyrillicletters = "БГЗИЙЛПФД";

    if (length <= 3) {
      logger.error("Length must be greater than four");
      throw new IllegalParameterException("Length must be greater than four");
    }

    String password = "";
    Random random = new Random(System.nanoTime());

    // Use categories
    List<String> charCategories = new ArrayList<>(4);
    if (useLower) {
      charCategories.add(LOWER);
    }
    if (useUpper) {
      charCategories.add(UPPER);
    }
    if (useDigits) {
      charCategories.add(DIGITS);
    }
    if (useSpecialCharacters) {
      charCategories.add(SpecialCharacters);
    }
    if (useCyrillic) {
      charCategories.add(Cyrillicletters);
    }
    // Build the password.
    for (int x = 0; x < length; x++) {
      //random of the given charcategories
      int y = random.nextInt(charCategories.size());
      //random char of the on y related string
      char z = charCategories.get(y).charAt(random.nextInt(charCategories.get(y).length()));
      //add the random char to the password
      password += z;
    }
    String finalPassword = password;
    listeners.forEach((listener) -> listener.generatePw(finalPassword));
    logger.info("New password generated: " + password);
    return password;
  }

  /**
   * Puts the State of entries to an extern file.
   *
   * @param listOfEntries the current list of entries
   * @throws RuntimeException
   */
  @Override
  public void getState(List<Entry> listOfEntries) {

    //Deleting the entries bevor saving them
    listeners.forEach(PwManagerListener::deleteContentOfFile);

    //Save masterpw
    listeners.forEach(listener -> listener.updateEntryListFile(encrypt(this.masterPw)));

    //Saving the entries
    for (Entry entry : listOfEntries) {
      String encId = encrypt(Integer.toString(entry.getEntryId()));
      String encUrl = encrypt(entry.getUrl());
      String encUname = encrypt(entry.getUsername());
      String encEmail = encrypt(entry.getEmail());
      String encPw = encrypt(entry.getPassword());
      String outss = encId + "," + encUrl + "," + encUname + "," + encEmail + "," + encPw;
      listeners.forEach(listener -> listener.updateEntryListFile(outss));
    }
    logger.info("Entrylist saved");
  }

  /**
   * Encrypts the password given.
   *
   * @param text which should be encrypted
   * @return the encrypted string
   */
  private String encrypt(String text) {
    StringBuilder hashedString = new StringBuilder();
    char[] chars = text.toCharArray();
    for (char c : chars) {
      c += 5;
      hashedString.append(c);
    }
    return hashedString.toString();
  }

  @Override
  public void loadState() throws NullPointerException {

  }

  /**
   * Decrypts an entry given.
   *
   * @param url of the entry
   * @param username of the entry
   * @param email of the entry
   * @param password of the entry
   */
  public void decryptAndLoadEntries(String url, String username, String email, String password) {
    String decUrl = this.decrypt(url);
    String decUname = this.decrypt(username);
    String decEmail = this.decrypt(email);
    String decPw = this.decrypt(password);
    try {
      this.addEntry(decUrl, decUname, decEmail, decPw);
    } catch (IllegalParameterException e) {
      e.printStackTrace();
    }
    listeners.forEach(listener -> listener.showsortedEntryList(listOfEntrys));
  }

  /**
   * Decrypts the given string.
   *
   * @param text that should be decrypted
   * @return the decrypted string
   */
  private String decrypt(String text) {
    StringBuilder result = new StringBuilder();
    char[] chars = text.toCharArray();
    for (char c : chars) {
      c -= 5;
      result.append(c);
    }
    return result.toString();
  }

  /**
   * Sets the masterpassword.
   *
   * @param masterPw that should be set.
   */
  public void setMasterPw(String masterPw) {
    this.masterPw = decrypt(masterPw);
    logger.info("masterpassword was set");
  }
}
