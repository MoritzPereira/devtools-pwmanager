package de.hhn.it.devtools.apis.todolist;

/**
 * ToDo Class
 * entails all information of a todo instance.
 */
public class ToDo {

    private String title;
    private String description;
    private boolean checked;

    /**
     * Constructor ToDo for creating new todos.
     * @param title title of the new todo
     * @param description description of the new todo
     * @param checked sets the status of the todo, usually "false"
     */
    public ToDo(String title, String description, boolean checked) {
        this.title = title;
        this.description = description;
        this.checked = checked;
    }

    /**
     * returns the title of the todo.
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * returns description of todo.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * changes the title of the todo.
     *
     * @param newTitle is the new title to be set
     */
    public void changeTitle(String newTitle) {
         this.title = newTitle;
    }

    /**
     * changes the description of the todo.
     *
     * @param newDescription is the new description to be set
     */
    public void changeDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * checks the todo.
     */
    public void checkToDo() {
        this.checked = true;
    }

    /**
     * unchecks the todo.
     */
    public void uncheckToDo() {
        this.checked = false;
    }
}