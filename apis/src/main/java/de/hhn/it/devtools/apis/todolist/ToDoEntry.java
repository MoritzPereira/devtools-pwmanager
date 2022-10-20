package de.hhn.it.devtools.apis.todolist;

/**
 * Interface for managing todos in the Task-List.
 */
public interface ToDoEntry {

  /**
   * Creates a new task in the ToDo List.
   *
   * @param title title of the new task
   * @param description description of the new task
   * @param checked checked status of the task
   */
  Task createTask(String title, String description, boolean checked);

  /**
   * Deletes a task of the ToDo List.
   *
   * @param todoId the ID of the task to delete
   */
  void deleteTask(int todoId);

  /**
   * Edits a task of the ToDo List.
   *
   * @param title edits the title of the task
   * @param description edits the description of the task
   */
  void editTask(String title, String description);
}
