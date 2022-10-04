package de.hhn.it.devtools.apis.pwmanager;

/**
 * Listener of the Password-Manager tool
 *
 */
public interface pwmanagerListener {

    /**
     * Changes the log-status to "logged in"
     */
    void loggedin();

    /**
     * Changes the log-status to "logged out"
     */
    void loggedout();

    /**
     * Add a new Entry to the entry-list
     * @param newEntry with the given attributes
     */
    void Entryadded(Entry newEntry);

    /**
     * Deletes the current entry
     * @param currentEntry the entry which should be deleted
     */
    void Entrydeleted(Entry currentEntry);

    /**
     * Updates the attributes of an entry
     */
    void Entrychanged(Entry password, Entry url, Entry username, Entry email);

    /**
     * Changes the visability of the chosen password
     */
    void changepasswordvisibility();

    /**
     * Updates the entry-list
     */
    void updateEntrylist();

}
