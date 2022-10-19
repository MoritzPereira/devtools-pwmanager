package de.hhn.it.devtools.apis.todolist;

/**
 * Interface for managing todos in the Task-List.
 */
public interface ToDoEntry {

    /**
     * creates a new todo in the todo list.
     * @param title title of the new todo
     * @param description description of the new todo
     * @param checked checked status of the todo
     */
    Task createTodo(String title, String description, boolean checked);

    /**
     * deletes a todo of the todo-list.
     * @param todoId, the ID of the task to delete
     */
    void deleteTodo(int todoId);

    /**
     * edits a todo of the todo-list.
     * @param title, edits the title of the task
     * @param description, edits the description of the task
     */
    void editTodo(String title, String description);


}
