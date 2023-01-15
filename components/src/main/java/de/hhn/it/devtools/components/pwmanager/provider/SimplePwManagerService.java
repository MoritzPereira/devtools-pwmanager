package de.hhn.it.devtools.components.pwmanager.provider;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.pwmanager.Entry;
import de.hhn.it.devtools.apis.pwmanager.PwManagerListener;
import de.hhn.it.devtools.apis.pwmanager.exceptions.IllegalMasterPasswordException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class SimplePwManagerService implements de.hhn.it.devtools.apis.pwmanager.PwManagerService {

  public String masterPw = "admin";

  int idstatus = 0;
  public boolean loggenIn = false;
  private boolean hidePws = true;
  public ArrayList<Entry> listOfEntrys = new ArrayList<>();
  private List<PwManagerListener> listeners = new ArrayList<>();


  //was ist mit konstruktor?

  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SimplePwManagerService.class);

  public void addListener(final PwManagerListener listener) throws IllegalParameterException {
    /*if (listener != null) {
      listeners.add(listener);
    } else {
      throw new NullPointerException("Listener doesn't exist");
    }*/

    if (listener == null) {
      throw new IllegalParameterException("Listener was null reference.");
    }

    if (listeners.contains(listener)) {
      throw new IllegalParameterException("Listener already registered.");
    }

    listeners.add(listener);

  }

  public void removeListener(final PwManagerListener listener) throws IllegalParameterException {
    /*if (listener != null) {
      listeners.remove(listener);
    } else {
      throw new NullPointerException("Listener doesn't exist");
    }*/

    if (listener == null) {
      throw new IllegalParameterException("Listener was null reference.");
    }

    if (!listeners.contains(listener)) {
      throw new IllegalParameterException("Listener is not registered:" + listener);
    }

    listeners.remove(listener);
  }

  public boolean checkPassword(String password) {
//bissle mehr überprüfungen so weisch
    int length = 4;
    if (password.length() < length) {
      return false;
    } else {
      return true;
    }

  }

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

  public boolean checkUrl(String url) {

    for (int i = 0; i < url.length(); i++) {
      char ch = url.charAt(i);
      if (ch == '.') {
        return true;
      }
    }
    return false;
  }

  @Override
  public void changeMasterPw(String newPassword, String oldPassword)
      throws IllegalMasterPasswordException, IllegalParameterException {

    if (!Objects.equals(this.masterPw, oldPassword)) {
      throw new IllegalMasterPasswordException();
    } else if(newPassword == null){
      throw new NullPointerException("Password is null");
    } else if (Objects.equals(newPassword, oldPassword)) {
      throw new IllegalParameterException("Dont use the same password again");
    } else if (!checkPassword(newPassword)) {
      throw new IllegalParameterException("New password is too weak");
    }
    this.masterPw = newPassword;
  }

  @Override
  public void login(String masterPw) throws IllegalMasterPasswordException {
    //loadMasterPW() to get the masterPw from the file
    if (Objects.equals(this.masterPw, masterPw)) {
      loggenIn = true;
      logger.info("Logged in");
      for (PwManagerListener listener : listeners) {
        listener.loggedin();
      }
      //listener.loggedin();
    } else {
      throw new IllegalMasterPasswordException();
    }
  }

  @Override
  public void logout() {
    System.out.println("Logout anfang");
    //listener.loggedout();
    try {
      getState(listOfEntrys);
      listeners.forEach((listener) -> listener.loggedout());
    } catch (IOException e) {
      e.getMessage();
    }
    loggenIn = false;
    logger.info("Logged out");
  }

  //Methode welche checkt welche id drankommt bei addEntry

  @Override
  public Entry addEntry(String url, String username, String email, String password)
          throws IllegalParameterException {

    if (!checkEmail(email)) {
      throw new IllegalParameterException("Email is not valid");
    }
    if (!checkUrl(url)) {
      throw new IllegalParameterException("Url is not valid");
    }
    if (!checkPassword(password)) {
      throw new IllegalParameterException("Password is not valid");
    }

    Entry newEntry = new Entry(idstatus, url, username, email, password);
    logger.info("New entry {id: " + idstatus + " } added");
    idstatus++;
    listOfEntrys.add(newEntry);

    //listener.entryAdded(newEntry);
    listeners.forEach((listener) -> listener.entryAdded(newEntry));
    return newEntry;

  }

  @Override
  public void changeEntry(Entry entry, String masterPw)
          throws IllegalParameterException, IllegalMasterPasswordException {

    boolean found = false;
    int id = 0;

    if (!Objects.equals(this.masterPw, masterPw)) {
      throw new IllegalMasterPasswordException();
    }

    for (Entry i : listOfEntrys) {
      if (i.getEntryId() == entry.getEntryId()) {
        id = i.getEntryId();
        found = true;
        i.setUrl(entry.getUrl());
        i.setUsername(entry.getUsername());
        i.setEmail(entry.getPassword());
        i.setPassword(entry.getPassword());
      }
      listeners.forEach((listener) -> listener.showsortedEntryList(listOfEntrys));
      //listeners.forEach((listener) -> listener.entryChanged(entry));
    }

    if (!found) {
      throw new IllegalParameterException("Entry not found");
    } else {
      logger.info("Entry {id: " + id + " } changed");
      //listener.entryChanged(entry);
    }
  }

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
        //listeners.forEach((listener) -> listener.entryDeleted(id));
        break;
      }
    }

    if (!foundId) {
      throw new IllegalParameterException("Entry not found");
    }

  }

  @Override
  public void changeHidden(int id) throws IllegalParameterException {

    boolean foundId = false;

    for (Entry i : listOfEntrys) {
      if (i.getEntryId() == id) {
        /*output =
            i.getEntryId() + "," + i.getUrl() + "," + i.getUsername() + "," + i.getEmail() + "," +
                i.getPassword();*/
        if(i.isChangeHidden()){
          i.setChangeHidden(false);
          logger.info("Hidden changed from id - " + id + " to false");
        }
        else{
          i.setChangeHidden(true);
          logger.info("Hidden changed from id - " + id + " to true");
        }
        foundId = true;
        break;
      }
    }

    //Check if the id exist
    if (!foundId) {
      throw new IllegalParameterException("Given id not found");
    }
    else{
      listeners.forEach((listener) -> listener.showsortedEntryList(listOfEntrys));
    }

  }

  @Override
  public String generateNewPw(boolean useUpper, boolean useLower, boolean useDigits,
                              boolean useSpecialCharacters, int length)
      throws IllegalParameterException {

    final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final String DIGITS = "0123456789";
    final String SpecialCharacters = "!@#$%&*()_+-=[]|,./?><";

    if (length <= 3) {
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
    // Build the password.
    for (int x = 0; x < length; x++) {
      //random of the given charcategories
      int y = random.nextInt(charCategories.size());
      //random char of the on y related string
      char z = charCategories.get(y).charAt(random.nextInt(charCategories.get(y).length()));
      //add the random char to the password
      password += z;
    }
    logger.info("New password generated: " + password);
    return password;
  }

  /**
   * Puts the State of entries to an extern file
   * @param listOfEntries the current list of entries
   * @throws RuntimeException
   */
  @Override
  public void getState(List<Entry> listOfEntries) throws RuntimeException, IOException {

    if (listOfEntries != null) {

      String osPath = System.getProperty("user.dir");
      osPath = osPath.replace("components","");
      osPath += "/components/src/main/entries.txt";
      File file = new File(osPath);
      Path filePath = Paths.get(osPath);

      BufferedWriter writer =
              new BufferedWriter(new OutputStreamWriter(new FileOutputStream(osPath, true)));

      //Writer to delete all from file
      PrintWriter deleteWriter = new PrintWriter(file);
      deleteWriter.print("");
      deleteWriter.close();

      for (Entry entry: listOfEntries) {
        String encId = encrypt(Integer.toString(entry.getEntryId()));
        String encUrl = encrypt(entry.getUrl());
        String encUname = encrypt(entry.getUsername());
        String encEmail = encrypt(entry.getEmail());
        String encPw = encrypt(entry.getPassword());
        String outss = encId + "," + encUrl + "," + encUname + "," + encEmail + "," + encPw;

        try {
          //Files.newBufferedWriter(filePath, StandardOpenOption.TRUNCATE_EXISTING);
          writer.write(outss);
          writer.newLine();
          //writer.close();
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
      }
      writer.close();
    } else {
      throw new NullPointerException();
    }
    System.out.println("liste gespeichert");
  }

  private String encrypt(String text) {
    StringBuilder hashedString = new StringBuilder();
    char[] chars = text.toCharArray();
    for (char c : chars) {
      c+= 5;
      hashedString.append(c);
    }
    return hashedString.toString();
  }

  @Override
  public void loadState() throws NullPointerException {

    int length = 0;
    String osPath = System.getProperty("user.dir");
    osPath = osPath.replace("components","");
    osPath += "/components/src/main/entries.txt";
    File file = new File(osPath);
    if (file == null){
      throw new NullPointerException();
    }
    Path filePath = Paths.get(osPath);
    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader(file));
      length = (int) Files.lines(filePath).count();
      Files.lines(filePath).close();

      for (int i = 0; i < length; i++) {
        String line = br.readLine();
        String[] splitline = line.split(",");
        String decId = this.decrypt(splitline[0]);
        String decUrl = this.decrypt(splitline[1]);
        String decUname = this.decrypt(splitline[2]);
        String decEmail = this.decrypt(splitline[3]);
        String decPw = this.decrypt(splitline[4]);
        this.addEntry(decUrl,decUname,decEmail,decPw);
      }
    } catch (IOException | IllegalParameterException e) {
      e.printStackTrace();
    }
    listeners.forEach((listener) -> listener.showsortedEntryList(listOfEntrys));
  }

  private String decrypt(String text) {
    StringBuilder result = new StringBuilder();
    char[] chars = text.toCharArray();
    for (char c : chars) {
      c -= 5;
      result.append(c);
    }
    return result.toString();
  }


}
