package de.hhn.it.devtools.apis.todolist;

public interface FolderListener {

    /**
     * A new folder is created.
     *
     * @param newFolder the new folder to be created
     */
    void folderCreated(Folder newFolder);

    /**
     * A folder is deleted.
     *
     * @param removedFolder the folder wich is to be removed
     */
    void folderDeleted(Folder removedFolder);

    /**
     * A folder is edited
     *
     * @param editedFolder the folder wich is to be edited
     */
    void folderEdited(Folder editedFolder);
}
