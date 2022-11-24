package de.hhn.it.devtools.components.pwmanager;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.pwmanager.Entry;
import de.hhn.it.devtools.apis.pwmanager.exceptions.IllegalMasterPasswordException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class PwManagerService implements de.hhn.it.devtools.apis.pwmanager.PwManagerService {

  private String masterPw = "admin";
  private boolean loggenIn = false;
  private boolean hidePws = true;
  private ArrayList<Entry> listOfEntrys = new ArrayList<>();


  @Override
  public void changeMasterPw(String newPassword, String oldPassword)
      throws IllegalMasterPasswordException, IllegalParameterException {

        if (Objects.equals(this.masterPw, oldPassword)) {
            this.masterPw = newPassword;
        } else {
            throw new IllegalMasterPasswordException();
        }

        //Weitere Überprüfung auf Sonderzeichen
        //Überprüfung obs net des gleiche ist
        //Überprüfung auf bestimmte Länge
        //all in all: Überprüfung auf Mindestanforderung

  }

  @Override
  public void login(String masterPw) throws IllegalMasterPasswordException {
    if (Objects.equals(this.masterPw, masterPw)) {
      loggenIn = true;
    } else {
      throw new IllegalMasterPasswordException();
    }
  }

  @Override
  public void logout() {
    loggenIn = false;
  }

  @Override
  public String changeHidden(int id) throws IllegalParameterException {

        String output = " ";

    for (Entry i : listOfEntrys) {
      if (i.getEntryId() == id) {
        output =
            i.getEntryId() + "," + i.getUrl() + "," + i.getUsername() + "," + i.getEmail() + "," +
                i.getPassword();
        foundId = true;
        break;
      }
    }

    //Check if the id exist
    if (!foundId) {
      throw new IllegalParameterException("Given id not found");
    }

    return output;
  }

  @Override
  public Entry addEntry(int id, String url, String username, String email, String password)
      throws IllegalParameterException {

        Entry newEntry = new Entry(id, url, username, email, password);
        listOfEntrys.add(newEntry);
        return newEntry;

  }

  @Override
  public void changeEntry(Entry entry, String masterPw)
      throws IllegalParameterException, IllegalMasterPasswordException {

    }

  @Override
  public void deleteEntry(int id, String masterPw)
      throws IllegalParameterException, IllegalMasterPasswordException {

    boolean foundId = false;

    if(!Objects.equals(this.masterPw, masterPw)){
      throw new IllegalMasterPasswordException();
    }

    Iterator it = listOfEntrys.iterator();
    while (it.hasNext()) {
      Entry entry = (Entry) it.next();
      if (id == entry.getEntryId()) {
        foundId = true;
        it.remove();
        break;
      }
    }

    if(!foundId){
      throw new IllegalParameterException("Entry not found");
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

    if (length <= 0) {
      throw new IllegalParameterException("Length must be greater than zero");
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
    for (int i = 0; i < length; i++) {
      password += charCategories.get(random.nextInt(charCategories.size()));
    }

    return password;
  }

  @Override
  public List<Entry> getState() throws RuntimeException {
    if (this.listOfEntrys != null) {
      return this.listOfEntrys;
    } else {
      throw new NullPointerException();
    }
  }

  @Override
  public void loadState(List<Entry> state) throws NullPointerException {
    if (state != null) {
      this.listOfEntrys = (ArrayList<Entry>) state;
    } else {
      throw new NullPointerException();
    }
  }


}
