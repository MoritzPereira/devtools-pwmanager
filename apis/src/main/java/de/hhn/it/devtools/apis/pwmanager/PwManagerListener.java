package de.hhn.it.devtools.apis.pwmanager;

/**
 * Listener of the Password-Manager tool.
 *
 */
public interface PwManagerListener {

    /**
     * Updates the log-status to "logged in".
     */
    void loggingin(String masterPw);

    /**
     * Updates the log-status to "logged out".
     */
    void logout();

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
     */
    void entryChanged(Entry changedEntry);

    /**
     * Changes the visability of the chosen password.
     */
    void changepasswordVisibility();

    /**
     * Updates the entry-list.
     */
    void updateEntryList();

}
