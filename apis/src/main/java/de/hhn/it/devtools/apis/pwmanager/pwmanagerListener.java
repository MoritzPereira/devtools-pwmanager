package de.hhn.it.devtools.apis.pwmanager;

public interface pwmanagerListener {

    void loggedin();

    void loggedout();

    void Entryadded(Entry newEntry);

    void Entrydeleted(Entry currentEntry);

    void Entrychanged();

    void changepasswordvisibility();

    void updateList();
}
