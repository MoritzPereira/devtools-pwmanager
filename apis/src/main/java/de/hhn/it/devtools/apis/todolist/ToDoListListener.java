package de.hhn.it.devtools.apis.todolist;

/**
 * Listener notifying about change of state on tasks.
 */
public interface ToDoListListener {
    /**
     * informs about change of state of a task.
     *
     * @param state the state of the task
     */
    void newState(TaskState state);
}
