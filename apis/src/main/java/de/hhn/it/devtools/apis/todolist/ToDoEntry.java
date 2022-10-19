package de.hhn.it.devtools.apis.todolist;

/**
 * Interface for managing todos in the ToDo-List.
 */
public interface ToDoEntry {

    /**
     * creates a new todo in the todo list.
     * @param title title of the new todo
     * @param description description of the new todo
     * @param checked checked status of the todo
     */
    ToDo createTodo(String title, String description, boolean checked);

    /**
     * deletes a todo of the todo-list.
     */
    void deleteTodo();


}
