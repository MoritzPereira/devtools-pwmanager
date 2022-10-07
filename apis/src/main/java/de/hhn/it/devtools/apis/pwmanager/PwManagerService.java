package de.hhn.it.devtools.apis.pwmanager;

public interface PwManagerService {

    String masterPw = "test";
    boolean hidepws = true;

    /**
     * changes the Master Password.
     *
     * @param password the new master password.
     */
     void changeMasterPw(String password);

    /**
     * changes the visibility of the passwords.
     */
     void changeHidden();



    /**
     * Creates a new Entry.
     *
     * @param url      for the associated password.
     * @param username for the associated password.
     * @param email    for the associated password.
     * @param password
     * @return the created entry.
     */
    public Entry addEntry(String url, String username, String email, String password);

    /**
     * Deletes an entry.
     * @param entry that will be deleted.
     */
    public void deleteEntry(Entry entry);


}
