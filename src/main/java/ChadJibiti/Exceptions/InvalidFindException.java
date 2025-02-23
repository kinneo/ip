package ChadJibiti.Exceptions;

public class InvalidFindException extends Exception {
    public InvalidFindException() {
        super("Invalid find format! Use: find <keyword>");
    }
}
