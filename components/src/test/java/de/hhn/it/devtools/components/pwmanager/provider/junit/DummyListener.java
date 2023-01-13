package de.hhn.it.devtools.components.pwmanager.provider.junit;

import de.hhn.it.devtools.apis.pwmanager.Entry;
import de.hhn.it.devtools.apis.pwmanager.PwManagerListener;
import de.hhn.it.devtools.components.example.coffeemakerservice.junit.DummyCallback;

import java.util.ArrayList;

public class DummyListener implements PwManagerListener {

    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(DummyCallback.class);

    @Override
    public void loggingin(String masterPw) {
        logger.info("Logging in...");
    }

    @Override
    public void loggedin() {
        logger.info("Logged in");
    }

    @Override
    public void logout() {
        logger.info("Logging out...");
    }

    @Override
    public void loggedout() {
        logger.info("Loggt out...");
    }

    @Override
    public void entryAdded(Entry newEntry) {
        logger.info("Entry added");
    }

    @Override
    public void entryDeleted(Entry currentEntry) {
        logger.info("Entry deleted");
    }

    @Override
    public void entryChanged(Entry entry) {
        logger.info("Entry changed");
    }

    @Override
    public void changePasswordVisibility(int id) {
        logger.info("Passsword-Visibility changed from id: " + id);
    }

    @Override
    public void updateEntryList() {
        logger.info("Entrylist updated");
    }

    @Override
    public void generatePw(boolean useUpper, boolean useLower, boolean useDigits, boolean useSpecialChars) {
        logger.info("New password generated");
    }

    @Override
    public void showNewPw(String pw) {
        logger.info("Showing new password");
    }

    @Override
    public void showsortedEntryList(ArrayList<Entry> entryList) {
        logger.info("Showing sorted entry list");
    }
}
