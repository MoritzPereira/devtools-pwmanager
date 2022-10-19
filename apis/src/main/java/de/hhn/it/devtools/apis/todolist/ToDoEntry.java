package de.hhn.it.devtools.apis.todolist;

public interface ToDoEntry {

    /**
     * creates a new todo in the todo list
     * @param title title of the new todo
     * @param description description of the new todo
     */
    ToDo createTodo(String title, String description);

    /**
     * deletes a todo of the todo-list
     */
    void deleteTodo();

    /**
     * edits a title of a todo
     * @param newTitle the new title of the todo
     */
    void editTitle(String newTitle);

    /**
     * edits a description of a todo
     * @param newDescription the new description of the todo
     */
    void editDescription(String newDescription);

    /**
     * marks a todo as "done"
     */
    void checkTodo();

    /**
     * marks a done todo as "undone"
     */
    void uncheckTodo();
}
