package de.hhn.it.devtools.apis.todolist;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

import java.time.LocalDate;

public class TodoUsageDemo {

  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(TodoUsageDemo.class);

  public static void main(String[] args) throws IllegalParameterException {
    ToDoListener toDoListener = null; //TODO: issue here null throws a exception, alternatives?
    ToDoEntry toDoEntry;

    //Create two tasks and notify the listener
    Task task1 = new Task("TODO 1", "this is a test");
    toDoListener.taskCreated(task1);
    Task task2 = new Task("TODO 2", "this is also a test");
    toDoListener.taskCreated(task2);

    task1.setStartDate(LocalDate.ofEpochDay(30-10-2022));   //TODO: better way of writing dates?
    toDoListener.taskEdited(task1);
    logger.debug("" + task1);
  }
}
