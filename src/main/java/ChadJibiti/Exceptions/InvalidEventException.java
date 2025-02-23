package ChadJibiti.Exceptions;

/**
 * Exception thrown when an invalid event format is encountered.
 * <p>
 * This exception is used when a user provides an incorrectly formatted event input,
 * ensuring that the correct format is followed for event tasks.
 * The correct format is: "event <task> /from <start> /to <end>".
 */
public class InvalidEventException extends Exception {
    public InvalidEventException() {
        super("Invalid event format! Use: event <task> /from <start> /to <end>");
    }
}
