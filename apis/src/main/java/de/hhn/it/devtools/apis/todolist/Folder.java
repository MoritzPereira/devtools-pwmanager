package de.hhn.it.devtools.apis.todolist;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Folder Class
 * organizes tasks with the same category.
 */
public class Folder {
  private final UUID id;
  private String title;
  private Colors color;
  private ArrayList<Task> content;

  /**
   * The constructor for the Folder class.
   * It takes in a title and a color and sets the id to a random UUID.
   *
   * @param title The title of the new folder.
   * @param color The color of the new folder.
   */
  public Folder(String title, Colors color) {
    id = UUID.randomUUID();
    this.title = title;
    this.color = color;
  }

  /**
   * This function returns the id of the object.
   *
   * @return The id of the object.
   */
  public UUID getId() {
    return id;
  }

  /**
   * This function returns the title of the book.
   *
   * @return The title of the book.
   */
  public String getTitle() {
    return title;
  }

  /**
   * This function sets the title of the book to the value of the parameter t.
   *
   * @param t The title of the window.
   */
  public void setTitle(String t) {
    this.title = t;
  }

  /**
   * This function returns the color of the folder.
   *
   * @return The color of the folder.
   */
  public Colors getColor() {
    return color;
  }

  /**
   * The function takes in a `Colors` object and sets the `color` variable to that object.
   *
   * @param c The color to set the folder to.
   */
  public void setColor(Colors c) {
    this.color = c;
  }

  /**
   * This function returns the content of the task list.
   *
   * @return The content of the task list.
   */
  public Task[] getContent() {
    Task[] array = new Task[content.size()];
    int i = 0;
    for (Task x : content) {
      array[i++] = x;
    }
    return array;
  }

  /**
   * This function adds a task to the content list.
   *
   * @param x The task to be added to the list.
   */
  public void addContent(Task x) {
    content.add(x);
  }

  //TODO: deleteContent

  /**
   * This function clears the content of the content variable.
   */
  public void clearContent() {
    content.clear();
  }

  /**
   * Returns the size of the content.
   *
   * @return The size of the content.
   */
  public long contentSize() {
    return content.size();
  }

  /**
   * The function takes an array of tasks, and returns an array of the tasks sorted by priority.
   *
   * @return An array of tasks sorted by priority.
   */
  public Task[] sortPriority() {
    Task[] array = this.getContent();
    Task swap;

    for (int i = 1; i < array.length; i++) {
      for (int j = 0; j < array.length - i; j++) {
        if (array[j].getPriority() > array[j + 1].getPriority()) {
          swap = array[j];
          array[j] = array[j + 1];
          array[j + 1] = swap;
        }
      }
    }
    return array;
  }

  /**
   * The function takes an array of tasks, and returns an array of the tasks sorted by EndDate.
   *
   * @return An array of tasks sorted by EndDate.
   */
  public Task[] sortEndDate() {
    Task[] array = this.getContent();
    Task swap;

    for (int i = 1; i < array.length; i++) {
      for (int j = 0; j < array.length - i; j++) {
        if (array[j].getEndDate().compareTo(array[j + 1].getEndDate()) > 0) {
          swap = array[j];
          array[j] = array[j + 1];
          array[j + 1] = swap;
        }
      }
    }
    return array;
  }

  /**
   * The function takes an array of tasks, and returns an array of the tasks sorted by StartDate.
   *
   * @return An array of tasks sorted by StartDate.
   */
  public Task[] sortStartDate() {
    Task[] array = this.getContent();
    Task swap;

    for (int i = 1; i < array.length; i++) {
      for (int j = 0; j < array.length - i; j++) {
        if (array[j].getStartDate().compareTo(array[j + 1].getStartDate()) > 0) {
          swap = array[j];
          array[j] = array[j + 1];
          array[j + 1] = swap;
        }
      }
    }
    return array;
  }

  /**
   * The function takes an array of tasks,
   * and returns an array of the tasks sorted by first highlighted and second priority.
   *
   * @return An array of tasks sorted by highlighted and priority.
   */
  public Task[] sortHighlighted() {
    Task[] array = this.getContent();
    Task swap;

    for (int i = 1; i < array.length; i++) {
      for (int j = 0; j < array.length; j++) {
        if ((array[j].getHighlighted() ? 1 : 0) > (array[j + 1].getHighlighted() ? 1 : 0)
            || (array[j].getHighlighted() && array[j + 1].getHighlighted()
            && array[j].getPriority() > array[j + 1].getPriority())) {
          swap = array[j];
          array[j] = array[j + 1];
          array[j + 1] = swap;
          break;
        }
      }
    }
    return array;
  }

  /**
   * The function takes an array of tasks,
   * and returns an array of the tasks sorted by first TaskState and second priority.
   *
   * @return An array of tasks sorted by TaskState and priority.
   */
  public Task[] sortTaskState() {
    Task[] array = this.getContent();
    Task swap;

    for (int i = 1; i < array.length; i++) {
      for (int j = 0; j < array.length; j++) {

        int j0 = translateStateToInt(array[j].getState());
        int j1 = translateStateToInt(array[j + 1].getState());

        array = sortInt(j, j0, j1, array);
      }
    }
    return array;
  }

  /**
   * If the state is DONE, return 2. If the state is INPROGRESS, return 1. Otherwise, return 0.
   *
   * @param x The TaskState enum value to translate.
   * @return The integer value of the TaskState enum.
   */
  private int translateStateToInt(TaskState x) {
    switch (x) {
      case DONE:
        return 2;

      case INPROGRESS:
        return 1;

      default:  //NOTSTARTED
        return 0;
    }
  }

  /**
   * The function takes an array of tasks,
   * and returns an array of the tasks sorted by first TaskFrequency and second priority.
   *
   * @return An array of tasks sorted by TaskFrequency and priority.
   */
  public Task[] sortTaskFrequency() {
    Task[] array = this.getContent();

    for (int i = 1; i < array.length; i++) {
      for (int j = 0; j < array.length; j++) {
        int j0 = translateFrequencyToInt(array[j].getFrequency());
        int j1 = translateFrequencyToInt(array[j + 1].getFrequency());

        array = sortInt(j, j0, j1, array);
      }
    }
    return array;
  }

  /**
   * If the frequency is daily, return 3. If the frequency is weekly, return 2.
   * If the frequency is monthly, return 1.
   * Otherwise, return 0.
   *
   * @param x The TaskFrequency enum value to translate.
   * @return The integer value of TaskFrequency enum.
   */
  private int translateFrequencyToInt(TaskFrequency x) {
    switch (x) {
      case DAILY:
        return 3;

      case WEEKLY:
        return 2;

      case MONTHLY:
        return 1;

      default:  //YEARLY
        return 0;
    }
  }

  /**
   * If the first element is greater than the second element, or if the first element is equal
   * to the second element and the priority of the first element is greater than
   * the priority of the second element, then swap the two indices.
   *
   * @param j The index of the current element
   * @param j0 The translated value of the j element in the array
   * @param j1 The translated value of the j+1 element in the array
   * @param array The array to be sorted
   * @return The array is being returned.
   */
  private Task[] sortInt(int j, int j0, int j1, Task[] array) {
    Task swap;
    if (j0 > j1 || (j0 == j1 && array[j].getPriority() > array[j + 1].getPriority())) {
      swap = array[j];
      array[j] = array[j + 1];
      array[j + 1] = swap;
    }
    return array;
  }

  //TODO: sort/filter elements by attributes:
}