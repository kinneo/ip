package kin.exceptions;
import kin.data.TaskManager;

public class InvalidTaskNumberException extends Exception {
    public InvalidTaskNumberException() { super("Invalid task number " + TaskManager.randomString() + "! Please enter a valid number."); }
}
