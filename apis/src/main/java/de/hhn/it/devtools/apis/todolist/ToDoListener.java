package de.hhn.it.devtools.apis.todolist;

public interface ToDoListener {

    /**
     * new task has been created
     * @param newTask new task
     */
    void todoCreated(Task newTask);

    /**
     * current task has been deleted
     * @param todoId the ID of the task
     */
    void todoDeleted(int todoId);

    /**
     * current task has been edited
     * @param title the title of the task
     * @param description the description of the task
     */
    void todoEdited(String title, String description);

    /**
     * informs about change of state of a task.
     * @param state the state of the task
     */
    void newState(TaskState state);


}
