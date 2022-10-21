package de.hhn.it.devtools.apis.todolist;

import java.time.LocalDate;
import java.util.UUID;

/** Listener callbacks to notify observers for state changes. */
public interface ToDoListener {

  /**
   * New task has been created.
   *
   * @param newTask new task
   */
  void taskCreated(Task newTask); //TODO: throws illegal date exception

  /**
   * Current task has been deleted.
   *
   * @param id the ID of the task
   */
  void taskDeleted(UUID id);

  /**
   * Current task has been edited.
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
  void taskEdited(String title, String description, LocalDate startDate, LocalDate endDate,
                  int priority, TaskFrequency frequency, boolean highlighted, TaskState newState);
  //TODO: alternative möglicherweise implementieren

  /**
   * Informs about change of state of a task.
   *
   * @param state the state of the task
   */
  void newState(TaskState state);
}