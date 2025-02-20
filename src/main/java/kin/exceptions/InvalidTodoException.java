package kin.exceptions;
import kin.data.TaskManager;

public class InvalidTodoException extends Exception{
    public InvalidTodoException() { super("Invalid todo format " + TaskManager.randomString() + "! Use: todo <task>"); }
}
