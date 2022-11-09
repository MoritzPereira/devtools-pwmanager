package de.hhn.it.devtools.apis.todolist;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

/** Listener callbacks to notify observers for state changes. */
public interface ToDoListener {

  /**
   * Called when a new task is created.
   *
   * @param newTask The task that was created.
   */
  void taskCreated(Task newTask) throws IllegalParameterException;

  /**
   * Called when the task is deleted.
   *
   * @param deletedTask The task that was deleted.
   */
  void taskDeleted(Task deletedTask);

  /**
   * Called when a tasks properties are edited.
   *
   * @param changedTask The task that was edited.
   */
  void taskEdited(Task changedTask);

  /**
   * All tasks in the current list are saved.
   */
  void taskListSaved();

  /**
   * All tasks in the current list are loaded.
   */
  void taskListLoaded();

  /**
   * Called when the state of the task changes.
   *
   * @param newState The new state of the task.
   */
  void newState(TaskState newState);
}