package kin.exceptions;
import kin.data.TaskManager;

public class InvalidEventException extends Exception{
    public InvalidEventException() { super("Invalid event format " + TaskManager.randomString() + "! Use: event <task> /from <start> /to <end>"); }
}
