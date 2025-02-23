package ChadJibiti.Exceptions;

/**
 * Exception thrown when an invalid task number is provided.
 *
 * This exception is used when a user inputs an invalid task number
 * that does not correspond to any existing task in the task list.
 */
 public class InvalidTaskNumberException extends Exception {
    public InvalidTaskNumberException() { super("Invalid task number! Please enter a valid number."); }
}
