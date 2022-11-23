package de.hhn.it.devtools.components.pwmanager;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.pwmanager.Entry;
import de.hhn.it.devtools.apis.pwmanager.exceptions.IllegalMasterPasswordException;

import java.util.List;

public class PwManagerService implements de.hhn.it.devtools.apis.pwmanager.PwManagerService {

    @Override
    public void changeMasterPw(String newPassword, String oldPassword) throws IllegalMasterPasswordException, IllegalParameterException {

    }

    @Override
    public void login(String masterPw) throws IllegalMasterPasswordException {

    }

    @Override
    public void logout() {

    }

    @Override
    public String changeHidden(int id) throws IllegalParameterException {
        return null;
    }

    @Override
    public Entry addEntry(int id, String url, String username, String email, String password) throws IllegalParameterException {
        return null;
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
        return null;
    }

    @Override
    public void loadState(List<Entry> state) throws RuntimeException {

    }

}
