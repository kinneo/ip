package ChadJibiti.Exceptions;

/**
 * Exception thrown when an invalid deadline format is encountered.
 * <p>
 * This exception is used when a user provides an incorrectly formatted deadline input,
 * ensuring that the correct format is followed for task deadlines.
 * The correct format is: "deadline <task> /by <date>".
 */
public class InvalidDeadlineException extends Exception {
    public InvalidDeadlineException() {
        super("Invalid deadline format! Use: deadline <task> /by <date>");
    }
}
