package de.hhn.it.devtools.apis.pwmanager;

import java.util.ArrayList;

/**
 * Listener of the Password-Manager tool.
 */
public interface PwManagerListener {

    /**
     * Updates the log-status to "logged in".
     */
    void loggingin(String masterPw);

    /**
     * Status from components.
     */
    void loggedin();

    /**
     * Updates the log-status to "logged out".
     */
    void logout();

    /**
     * Status from components.
     */
    void loggedout();

    /**
     * Add a new Entry to the entry-list.
     *
     * @param newEntry with the given attributes
     */
    void entryAdded(Entry newEntry);

    /**
     * Deletes the current entry.
     *
     * @param currentEntry the entry which should be deleted.
     */
    void entryDeleted(Entry currentEntry);

    /**
     * Updates the attributes of an entry.
     */
    void entryChanged(Entry entry);

    /**
     * Changes the visability of the chosen password.
     */
    void changePasswordVisibility(int id);

    /**
     * Updates the entry-list.
     */
    void updateEntryList();

    /**
     * gives the pw specs.
     */
    void generatePw(boolean useUpper, boolean useLower, boolean useDigits, boolean useSpecialChars);

    /**
     * shows the newly generated pw.
     *
     * @param pw the generated pw.
     */
    void showNewPw(String pw);

    /**
     * gives the sorted List of Entrys.
     *
     * @param entryList sorted list of entrys
     */
    void showsortedEntryList(ArrayList<Entry> entryList);

}
