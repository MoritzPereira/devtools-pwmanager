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
   * @param title The title of the new task.
   * @param description The description of the new task.
   */
  Task createTask(String title, String description) throws IllegalParameterException;

  /**
   * Delete the task with the given id.
   *
   * @param id The id of the task to be deleted.
   */
  void deleteTask(UUID id);

  /**
   * This function edits a task.
   *
   * @param taskChanged The task that has been changed.
   */
  void editTask(Task taskChanged)
          throws IllegalParameterException;

  /** This function saves the list of tasks in a file. */
  void saveList();

  /** This function loads a file with all the tasks saved on it. */
  void loadList();

  //TODO: imports/exports
}
