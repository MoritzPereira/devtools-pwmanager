package de.hhn.it.devtools.apis.pwmanager;

/**
 * Listener of the Password-Manager tool.
 *
 */
public interface PwManagerListener {

    /**
     * Updates the log-status to "logged in".
     */
    void loggedin();

    /**
     * Updates the log-status to "logged out".
     */
    void loggedout();

    /**
     * Add a new Entry to the entry-list.
     * @param newEntry with the given attributes
     */
    void entryAdded(Entry newEntry);

    /**
     * Deletes the current entry.
     * @param currentEntry the entry which should be deleted.
     */
    void entryDeleted(Entry currentEntry);

    /**
     * Updates the attributes of an entry.
     * @param password password of current Entry
     * @param url url of current Entry
     * @param username username of current Entry
     * @param email email of current Entry
     */
    void entryChanged(Entry password, Entry url, Entry username, Entry email);

    /**
     * Changes the visability of the chosen password.
     */
    void changepasswordVisibility();

    /**
     * Updates the entry-list.
     */
    void updateEntryList();

}
