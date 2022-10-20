package de.hhn.it.devtools.apis.todolist;

public interface ToDoListener {

    /**
     * New task has been created.
     *
     * @param newTask new task
     */
    void todoCreated(Task newTask);

    /**
     * Current task has been deleted.
     *
     * @param todoId the ID of the task
     */
    void todoDeleted(int todoId);

    /**
     * Current task has been edited.
     *
     * @param title the title of the task
     * @param description the description of the task
     */
    void todoEdited(String title, String description);

    /**
     * Informs about change of state of a task.
     *
     * @param state the state of the task
     */
    void newState(TaskState state);


}
