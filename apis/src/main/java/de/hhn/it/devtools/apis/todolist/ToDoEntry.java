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
   * @param title the new title of the task
   * @param description the new description of the task
   * @param startDate the new start date of the task
   * @param endDate the new end date of the task
   * @param priority the new priority value of the task
   * @param frequency the new frequency of the task
   * @param highlighted true value for highlighted, false value for un-highlighted
   * @param newState the new state of the task
   */
  void editTask(String title, String description, LocalDate startDate, LocalDate endDate,
                int priority, TaskFrequency frequency, boolean highlighted, TaskState newState)
          throws IllegalParameterException;
  //TODO: alternative zur übernahme aller werte: Task überschreiben // neue werte vergleichen

  /** Saves all open and existing tasks in a file.*/
  void saveList();

  /** Loads a file with all the tasks saved on it. */
  void loadList();

  //TODO: imports/exports

}
