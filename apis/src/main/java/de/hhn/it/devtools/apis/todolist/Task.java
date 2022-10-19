package de.hhn.it.devtools.apis.todolist;

import java.util.Date;

/**
 * Task Class
 * entails all information of a task.
 */
public class Task {

    private int id;
    private String title;
    private String description;
    private Date startDate;     //TODO: should these be required when creating a task
    private Date endDate;       //.
    private int priority;       //.
    //TODO: missing: repeating weekly, monthly ..., est. time to end
    private boolean highlighted;
    private TaskState state;

    /**
     * Constructor Task for creating new tasks.
     * @param title title of the new task
     * @param description description of the new task
     */
    public Task(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    /**
     * returns the ID of the task.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * returns the title of the task.
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * returns description of task.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * returns the highlighted state
     *
     * @return highlighted
     */
    public boolean getHighlighted() {
        return highlighted;
    }

    /**
     * returns the start date of the task.
     *
     * @return startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * returns the end date of the task.
     *
     * @return endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * returns the priority value of the task
     *
     * @return priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * changes the ID of the task.
     *
     * @param newId is the new ID to be set
     */
    public void setId(int newId) {
        this.id = newId;
    }

    /**
     * changes the title of the task.
     *
     * @param newTitle is the new title to be set
     */
    public void setTitle(String newTitle) {
         this.title = newTitle;
    }

    /**
     * changes the description of the task.
     *
     * @param newDescription is the new description to be set
     */
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * highlights or removes the highlight of a task
     *
     * @param newHighlighted true for highlighting a task, false for removing highlight
     */
    public void setHighlighted(boolean newHighlighted) {
        this.highlighted = newHighlighted;
    }

    /**
     * sets or changes (pre-/postpones) the start date of the task.
     *
     * @param newStartDate is the new start date of the task
     */
    public void setStartDate(Date newStartDate) {
        this.startDate = newStartDate;
    }

    /**
     * sets or changes (pre-/postpones) the end date of the task.
     *
     * @param newEndDate is the new end date of the task
     */
    public void setEndDate(Date newEndDate) {
        this.endDate = newEndDate;
    }

    /**
     * sets or changes the priority of a task.
     *
     * @param newPriority is the new priority of the task
     */
    public void setPriority(int newPriority) {
        this.priority = newPriority;
    }

    /**
     * sets the state of the task.
     */
    public void setState(final TaskState state) {
        this.state = state;
    }

    /**
     * Returns the state of the task.
     * @return state
     */
    public TaskState getState() {
        return state;
    }

}