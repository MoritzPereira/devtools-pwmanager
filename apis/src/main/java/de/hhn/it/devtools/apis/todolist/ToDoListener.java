package de.hhn.it.devtools.apis.todolist;

/** Listener callbacks to notify observers for state changes. */
public interface ToDoListener {

    /**
     * New task has been created.
     *
     * @param newTask new task
     */
    void taskCreated(Task newTask);

    /**
     * Current task has been deleted.
     *
     * @param id the ID of the task
     */
    void taskDeleted(int id);

    /**
     * Current task has been edited.
     *
     * @param title the title of the task
     * @param description the description of the task
     */
    void taskEdited(String title, String description);

    /**
     * Informs about change of state of a task.
     *
     * @param state the state of the task
     */
    void newState(TaskState state);
}
