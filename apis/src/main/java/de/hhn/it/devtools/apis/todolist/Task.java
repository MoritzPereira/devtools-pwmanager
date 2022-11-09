package de.hhn.it.devtools.apis.todolist;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Task Class
 * entails all information of a task.
 */
public class Task {

  private final UUID id;
  private String title;
  private String description;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private int priority;
  private TaskFrequency frequency;
  private boolean highlighted;
  private TaskState state;
  private int estimatedSize;
  private Folder category;

  /**
   * Constructor Task for creating new tasks,
   * with given minimum requirements.
   * Constructor sets the highlight status to false, generates a random UUID
   *
   * @param title The title of the task.
   * @param description The description of the task.
   * @param startDate The start date of the task.
   * @param endDate The end date of the task.
   * @param priority the priority of the task.
   * @param frequency the frequency the task repeats at.
   * @param estimatedSize the size of the task.
   * @param category the category the task will be set under.
   */
  // The constructor of the class.
  public Task(String title, String description, LocalDateTime startDate, LocalDateTime endDate,
              int priority, TaskFrequency frequency, int estimatedSize, Folder category) {
    this.title = title;
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
    this.priority = priority;
    this.frequency = frequency;
    this.estimatedSize = estimatedSize;
    this.category = category;

    highlighted = false;
    id = UUID.randomUUID();
    state = TaskState.NOTSTARTED;
  }

  /**
   * This function returns the id of the task.
   *
   * @return The id of the object.
   */
  public UUID getId() {
    return id;
  }

  /**
   * This function returns the title of the task.
   *
   * @return The title of the task.
   */
  public String getTitle() {
    return title;
  }

  /**
   * This function sets the title of the task to the value of the newTitle parameter.
   *
   * @param newTitle The new title of the task.
   */
  public void setTitle(String newTitle) {
    this.title = newTitle;
  }

  /**
   * This function returns the description of the task.
   *
   * @return The description of the item.
   */
  public String getDescription() {
    return description;
  }

  /**
   * This function sets the description of the task to the value of newDescription.
   *
   * @param newDescription The new description of the task.
   */
  public void setDescription(String newDescription) {
    this.description = newDescription;
  }

  /**
   * This function returns the start date of the task.
   *
   * @return The startDate variable.
   */
  public LocalDateTime getStartDate() {
    return startDate;
  }

  /**
   * This function sets the start date of the task to the value of newStartDate.
   * Not only initial value, also to pre-/postpone a task.
   *
   * @param newStartDate The new start date for the task.
   */
  public void setStartDate(LocalDateTime newStartDate) {
    this.startDate = newStartDate;
  }

  /**
   * This function returns the end date of the task.
   *
   * @return The endDate variable.
   */
  public LocalDateTime getEndDate() {
    return endDate;
  }

  /**
   * This function sets the end date of the task to the value of newEndDate.
   * Not only initial value, also to pre-/postpone a task.
   *
   * @param newEndDate The new end date for the project.
   */
  public void setEndDate(LocalDateTime newEndDate) {
    this.endDate = newEndDate;
  }

  /**
   * This function returns the highlighted state of the task.
   *
   * @return The highlighted status is being returned.
   */
  public boolean getHighlighted() {
    return highlighted;
  }

  /**
   * This function sets the highlighted state to the value of the newHighlighted state.
   *
   * @param newHighlighted The new highlighted state of the button.
   */
  public void setHighlighted(boolean newHighlighted) {
    this.highlighted = newHighlighted;
  }

  /**
   * Returns the priority value of the task.
   *
   * @return The priority of the task.
   */
  public int getPriority() {
    return priority;
  }

  /**
   * This function sets the priority of the task to the value of newPriority.
   *
   * @param newPriority The new priority of the task.
   */
  public void setPriority(int newPriority) {
    this.priority = newPriority;
  }

  /**
   * This function returns the repeating frequency of the task.
   *
   * @return The frequency of the task.
   */
  public TaskFrequency getFrequency() {
    return frequency;
  }

  /**
   * This function sets the repeating frequency of the task.
   *
   * @param frequency The frequency of the task.
   */
  public void setFrequency(TaskFrequency frequency) {
    this.frequency = frequency;
  }

  /**
   * This function returns the state of the task.
   *
   * @return The state of the task.
   */
  public TaskState getState() {
    return state;
  }

  /**
   * This function sets the state of the task.
   *
   * @param state The state of the task.
   */
  public void setState(final TaskState state) {
    this.state = state;
  }

  /**
   * Returns the estimated effort of the task.
   *
   * @return The estimated size of the task.
   */
  public int getEstimatedSize() {
    return estimatedSize;
  }

  /**
   * This function sets the estimated size of the task.
   *
   * @param size The estimated size of the task.
   */
  public void setEstimatedSize(int size) {
    estimatedSize = size;
  }

  /**
   * Returns the category of the task.
   *
   * @return category
   */
  public Folder getCategory() {
    return category;
  }

  /**
   * Sets the category of the task to a new one.
   *
   * @param newCategory the new category.
   **/
  public void setCategory(Folder newCategory) {
    this.category = newCategory;
  }

  /**
   * The toString() function returns a string representation of the object Task.
   *
   * @return A string representation of the task object.
   */
  public String toString() {
    return "{ TASK ID: " + id + "\n TASK TITLE: " + title + "\n TASK DESCRIPTION: " + description
            + "\n TASK START DATE: " + startDate + "\n TASK END DATE: " + endDate
            + "\n TASK PRIORITY: " + priority + "\n TASK FREQUENCY: " + frequency
            + "\n TASK HIGHLIGHTED: " + highlighted + "\n TASK STATE: " + state + "\n TASK SIZE: "
            + estimatedSize + "\n TASK CATEGORY: " + category + " }";
  }
}