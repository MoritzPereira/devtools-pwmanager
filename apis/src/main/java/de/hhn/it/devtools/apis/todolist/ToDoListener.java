package de.hhn.it.devtools.apis.todolist;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import java.util.UUID;

/** Listener callbacks to notify observers for state changes. */
public interface ToDoListener {

  /**
   * This function is called when a new task is created.
   *
   * @param newTask The task that was created.
   */
  void taskCreated(Task newTask) throws IllegalParameterException;

  /**
   * When a task is deleted, remove it from the list of tasks.
   *
   * @param id The UUID of the task that was deleted.
   */
  void taskDeleted(UUID id);

  /**
   * When a task is edited, update the task in the database.
   *
   * @param taskChanged The task that was edited.
   */
  void taskEdited(Task taskChanged);

  /**
   * This function is called when the state of the task changes.
   *
   * @param state The new state of the task.
   */
  void newState(TaskState state);


}