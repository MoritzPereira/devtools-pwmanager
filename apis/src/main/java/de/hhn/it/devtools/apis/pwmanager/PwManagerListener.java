package de.hhn.it.devtools.apis.pwmanager;

import java.util.ArrayList;

/**
 * Listener of the Password-Manager tool.
 */
public interface PwManagerListener {


  /**
   * Status from components.
   */
  void loggedin();

  /**
   * Status from components.
   */
  void loggedout();

  /**
   * Updates the entry-list.
   */
  void updateEntryListFile(String input);

  /**
   * Deletes the entry-list in the file.
   */
  void deleteContentOfFile();

  /**
   * gives the pw to the ui.
   */
  void generatePw(String password);

  /**
   * gives the sorted List of Entrys.
   *
   * @param entryList sorted list of entrys
   */
  void showSortedEntryList(ArrayList<Entry> entryList);

}
