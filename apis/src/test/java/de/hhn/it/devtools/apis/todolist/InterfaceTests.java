package de.hhn.it.devtools.apis.todolist;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

import java.time.LocalDateTime;
import java.time.Month;

public class InterfaceTests {
    public static void main(String[] args) throws IllegalParameterException {
        ToDoListener tdl = new ToDoListener() {
            @Override
            public void taskCreated(Task newTask) throws IllegalParameterException {
                System.out.println("Task successfully created!");
                System.out.println(newTask.toString());
            }

            @Override
            public void taskDeleted(Task deletedTask) throws IllegalParameterException {

            }

            @Override
            public void taskEdited(Task changedTask) throws IllegalParameterException {

            }

            @Override
            public void taskListSaved() {

            }

            @Override
            public void taskListLoaded() {

            }

            @Override
            public void newState(TaskState newState) throws IllegalParameterException {

            }
        };

        Task task1 = new Task("New Task", "this is a test", LocalDateTime.of(2022, Month.NOVEMBER, 15, 13, 0),
                LocalDateTime.of(2022, Month.NOVEMBER, 16, 15, 0), 5, TaskFrequency.ONETIME, 5, null);
        tdl.taskCreated(task1);
    }
}
