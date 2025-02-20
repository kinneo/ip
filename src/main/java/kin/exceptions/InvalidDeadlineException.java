package kin.exceptions;
import kin.data.TaskManager;

public class InvalidDeadlineException extends Exception{
    public InvalidDeadlineException() { super("Invalid deadline format " + TaskManager.randomString() + "! Use: deadline <task> /by <date>"); }
}
