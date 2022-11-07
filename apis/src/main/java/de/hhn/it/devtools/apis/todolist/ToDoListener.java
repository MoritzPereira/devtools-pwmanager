package de.hhn.it.devtools.apis.todolist;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

/** Listener callbacks to notify observers for state changes. */
public interface ToDoListener {

  /**
   * New task has been created.
   *
   * @param newTask new task
   */
  void taskCreated(Task newTask) throws IllegalParameterException;

  /**
   * Current task has been deleted.
   *
   * @param taskDeleted the task that gets deleted
   */
  void taskDeleted(Task taskDeleted);

  /**
   * Current task has been edited.
   *
   * @param taskChanged is the task that had its properties changed
   */
  void taskEdited(Task taskChanged);

  /**
   * All tasks in the current list are saved
   */
  void taskListSaved();

  /**
   * All tasks in the current list are loaded
   */
  void taskListLoaded();

  /**
   * Informs about change of state of a task.
   *
   * @param state the state of the task
   */
  void newState(TaskState state);
}