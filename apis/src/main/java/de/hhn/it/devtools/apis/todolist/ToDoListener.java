package de.hhn.it.devtools.apis.todolist;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

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
   * @param taskDeleted The task that was deleted.
   */
  void taskDeleted(Task taskDeleted);

  /**
   * When a task is edited, update the task in the database.
   *
   * @param taskChanged The task that was edited.
   */
  void taskEdited(Task taskChanged);

  /**
   * All tasks in the current list are saved.
   */
  void taskListSaved();

  /**
   * All tasks in the current list are loaded.
   */
  void taskListLoaded();

  /**
   * This function is called when the state of the task changes.
   *
   * @param state The new state of the task.
   */
  void newState(TaskState state);
}