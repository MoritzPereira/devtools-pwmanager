package de.hhn.it.devtools.apis.todolist;

public interface ToDoEntry {

    /**
     * adds a new todo to the todo-list
     */
    void addTodo(String title, String description);

    /**
     * deletes a todo of the todo-list
     */
    void deleteTodo();

    /**
     * edits a title of a todo
     */
    void editTitle(String newTitle);

    /**
     * edits a description of a todo
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
