package de.hhn.it.devtools.apis.todolist;

/** Listener callbacks to notify observers for state changes. */
public interface FolderListener {

  /**
   * This function is called when a new folder is created.
   *
   * @param newFolder The folder that was created.
   */
  void folderCreated(Folder newFolder);

  /**
   * This function is called when a folder is deleted.
   *
   * @param removedFolder The folder that was deleted.
   */
  void folderDeleted(Folder removedFolder);

  /**
   * When the user edits a folder, update the folder in the database.
   *
   * @param editedFolder The folder that was edited.
   */
  void folderEdited(Folder editedFolder);
  //TODO: void addedToFolder(Folder newContentFolder);

  //TODO: add task to folder Listener, in welchen Listener?
}
