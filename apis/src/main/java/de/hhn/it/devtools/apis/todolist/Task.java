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
  private Date startDate;
  private Date endDate;
  private int priority;
  private TaskFrequency frequency;
  private boolean highlighted;
  private TaskState state;

  /**
   * Constructor Task for creating new tasks.
   *
   * @param title title of the new task
   * @param description description of the new task
   * @param id identification number of task
   */
  public Task(String title, String description, int id) {
    this.title = title;
    this.description = description;
    this.id = id;
  }

  /**
   * Returns the ID of the task.
   *
   * @return id
   */
  public int getId() {
    return id;
  }

  /**
   * Returns the title of the task.
   *
   * @return title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Returns description of task.
   *
   * @return description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Returns the highlighted state.
   *
   * @return highlighted
   */
  public boolean getHighlighted() {
    return highlighted;
  }

  /**
   * Returns the start date of the task.
   *
   * @return startDate
   */
  public Date getStartDate() {
    return startDate;
  }

  /**
   * Returns the end date of the task.
   *
   * @return endDate
   */
  public Date getEndDate() {
    return endDate;
  }

  /**
   * Returns the priority value of the task.
   *
   * @return priority
   */
  public int getPriority() {
    return priority;
  }

  /**
   * Returns the frequency of the tasks repeating.
   *
   * @return frequency
   */
  public TaskFrequency getFrequency() {
    return frequency;
  }

  /**
   * Changes the ID of the task.
   *
   * @param newId is the new ID to be set
   */
  public void setId(int newId) {
    this.id = newId;
  }

  /**
   * Changes the title of the task.
   *
   * @param newTitle is the new title to be set
   */
  public void setTitle(String newTitle) {
    this.title = newTitle;
  }

  /**
   * Changes the description of the task.
   *
   * @param newDescription is the new description to be set
   */
  public void setDescription(String newDescription) {
    this.description = newDescription;
  }

  /**
   * Highlights or removes the highlight of a task.
   *
   * @param newHighlighted true for highlighting a task, false for removing highlight
   */
  public void setHighlighted(boolean newHighlighted) {
    this.highlighted = newHighlighted;
  }

  /**
   * Sets or changes (pre-/postpones) the start date of the task.
   *
   * @param newStartDate is the new start date of the task
   */
  public void setStartDate(Date newStartDate) {
    this.startDate = newStartDate;
  }

  /**
   * Sets or changes (pre-/postpones) the end date of the task.
   *
   * @param newEndDate is the new end date of the task
   */
  public void setEndDate(Date newEndDate) {
    this.endDate = newEndDate;
  }

  /**
   * Sets or changes the priority of a task.
   *
   * @param newPriority is the new priority of the task
   */
  public void setPriority(int newPriority) {
    this.priority = newPriority;
  }

  /**
   * Sets or changes the frequency of the task interval.
   *
   * @param frequency is the new frequency
   */
  public void setFrequency(TaskFrequency frequency) {
    this.frequency = frequency;
  }

  /**
   * Sets the state of the task.
   *
   * @param state is the processing state of the task
   */
  public void setState(final TaskState state) {
    this.state = state;
  }

  /**
   * Returns the state of the task.
   *
   * @return state
   */
  public TaskState getState() {
    return state;
  }
}