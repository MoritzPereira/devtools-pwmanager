package de.hhn.it.devtools.apis.todolist;

/**
 * Enum stating all possible statuses of a task.
 */
public enum TaskState {
    /**
     * Task was not yet started
     */
    NOTSTARTED,
    /**
     * Task has been marked as "in progress"
     */
    INPROGRESS,
    /**
     * Task has been marked as "done"
     */
    DONE
}
