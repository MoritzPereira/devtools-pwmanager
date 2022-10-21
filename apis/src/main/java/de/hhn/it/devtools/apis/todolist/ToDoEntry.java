package de.hhn.it.devtools.apis.todolist;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Interface for managing todos in the Task-List.
 */
public interface ToDoEntry {

  /**
   * Creates a new task in the ToDo List.
   *
   * @param title title of the new task
   * @param description description of the new task
   */
  Task createTask(String title, String description) throws IllegalParameterException;

  /**
   * Deletes a task of the ToDo List.
   *
   * @param id the ID of the task to delete
   */
  void deleteTask(UUID id);

  /**
   * Edits a task of the ToDo List.
   *
   * @param taskChanged is the task that had its properties changed
   */
  void editTask(Task taskChanged)
          throws IllegalParameterException;

  /** Saves all open and existing tasks in a file.*/
  void saveList();

  /** Loads a file with all the tasks saved on it. */
  void loadList();

  //TODO: imports/exports

}
