package de.hhn.it.devtools.apis.todolist;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

/**
 * Interface for managing todos in the Task-List.
 */
public interface ToDoEntry {

  /**
   * Creates a new task in the ToDo List.
   *
   * @param title The title of the new task.
   * @param description The description of the new task.
   */
  Task createTask(String title, String description) throws IllegalParameterException;

  /**
   * Deletes the task with the given id.
   *
   * @param editedTask The task to be deleted.
   */
  void deleteTask(Task editedTask);

  /**
   * Edits the tasks properties.
   *
   * @param changedTask The task that has been changed.
   */
  void editTask(Task changedTask)
          throws IllegalParameterException;

  /**
   * Changes the state of the task.
   *
   * @param newState new TaskState
   */
  void changeState(TaskState newState);

  /** This function saves the list of tasks in a file. */
  void saveList();

  /** This function loads a file with all the tasks saved on it. */
  void loadList();
}
