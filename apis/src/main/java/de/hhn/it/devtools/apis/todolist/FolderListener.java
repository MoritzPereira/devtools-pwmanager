package de.hhn.it.devtools.apis.todolist;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

import java.io.IOException;

/** Listener callbacks to notify observers for state changes. */
public interface FolderListener {

  /**
   * Called when a new folder is created.
   *
   * @param newFolder The folder that was created.
   */
  void folderCreated(Folder newFolder) throws IllegalParameterException;

  /**
   * Called when a folder is deleted.
   *
   * @param removedFolder The folder that was deleted.
   */
  void folderDeleted(Folder removedFolder)throws IllegalParameterException;

  /**
   * Called when a folders properties are edited.
   *
   * @param editedFolder The folder that was edited.
   */
  void folderEdited(Folder editedFolder)throws IllegalParameterException;

  /**
   * Called when a folder has been cleared of its tasks.
   *
   * @param clearedFolder The folder that was cleared.
   */
  void folderCleared(Folder clearedFolder)throws IllegalParameterException;
}
