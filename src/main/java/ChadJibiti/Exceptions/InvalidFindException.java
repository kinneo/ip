package ChadJibiti.Exceptions;

/**
 * Exception thrown when an invalid find format is encountered.
 * <p>
 * This exception is used when a user provides an incorrectly formatted find input,
 * ensuring that the correct format is followed for searching tasks.
 * The correct format is: "find <keyword>".
 */
public class InvalidFindException extends Exception {
    public InvalidFindException() {
        super("Invalid find format! Use: find <keyword>");
    }
}
