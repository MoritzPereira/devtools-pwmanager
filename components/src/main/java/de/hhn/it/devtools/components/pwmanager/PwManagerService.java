package de.hhn.it.devtools.components.pwmanager;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.pwmanager.Entry;
import de.hhn.it.devtools.apis.pwmanager.exceptions.IllegalMasterPasswordException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PwManagerService implements de.hhn.it.devtools.apis.pwmanager.PwManagerService {

    private String masterPw = "admin";
    private boolean loggenIn = false;
    private boolean hidePws = true;
    private ArrayList<Entry> listOfEntrys = new ArrayList<>();


    @Override
    public void changeMasterPw(String newPassword, String oldPassword) throws IllegalMasterPasswordException, IllegalParameterException {

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
                output = i.getEntryId() + "," + i.getUrl() + "," + i.getUsername() + "," + i.getEmail() + "," + i.getPassword();
                break;
            }
        }
        return output;

    }

    @Override
    public Entry addEntry(int id, String url, String username, String email, String password) throws IllegalParameterException {

        Entry newEntry = new Entry(id, url, username, email, password);
        listOfEntrys.add(newEntry);
        return newEntry;

    }

    @Override
    public void changeEntry(Entry entry, String masterPw) throws IllegalParameterException, IllegalMasterPasswordException {

    }

    @Override
    public void deleteEntry(int id, String masterPw) throws IllegalParameterException, IllegalMasterPasswordException {

    }

    @Override
    public String generateNewPw(boolean useUpper, boolean useLower, boolean useDigits, boolean useSpecialChars) {
        return null;
    }

    @Override
    public List<Entry> getState() throws RuntimeException {
        if (this.listOfEntrys!=null){
            return this.listOfEntrys;
        }else {
            throw new NullPointerException();
        }
    }

    @Override
    public void loadState(List<Entry> state) throws NullPointerException {
        if (state != null){
            this.listOfEntrys= (ArrayList<Entry>) state;
        }else {
            throw new NullPointerException();
        }
    }



}
