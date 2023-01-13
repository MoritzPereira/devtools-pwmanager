package de.hhn.it.devtools.apis.todolist;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import java.time.LocalDateTime;
import java.time.Month;

import static de.hhn.it.devtools.apis.todolist.TaskFrequency.ONETIME;

public class TodoUsageDemo {

  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(TodoUsageDemo.class);

  public static void main(String[] args) throws IllegalParameterException {
    ToDoListener toDoListener = null;
    ToDoEntry toDoEntry = null;

    //Create two tasks and notify the listener
    Task task1 = new Task("TODO 1", "this is a test", null, null,
            0, ONETIME, 0, null);
    toDoListener.taskCreated(task1);
    Task task2 = new Task("TODO 2", "this is also a test",null, null,
            0, ONETIME, 0, null);
    toDoListener.taskCreated(task2);

    task1.setStartDate(LocalDateTime.of(2022, Month.NOVEMBER, 30, 18, 0));
    toDoListener.taskEdited(task1);
    logger.debug("" + task1);

    task1.setEndDate(LocalDateTime.of(2022, Month.DECEMBER, 15, 15, 0)); //TODO: better way of writing dates
    toDoListener.taskEdited(task1);
    logger.debug("" + task1);

    task1.setState(TaskState.DONE);
    toDoListener.taskEdited(task1);
    logger.debug("" + task1);

    task2.setFrequency(TaskFrequency.WEEKLY);
    toDoListener.taskEdited(task2);
    logger.debug("" + task2);


  }
}
