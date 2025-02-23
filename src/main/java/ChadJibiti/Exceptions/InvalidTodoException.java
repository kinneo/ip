package ChadJibiti.Exceptions;

/**
 * Exception thrown when an invalid Todo format is encountered.
 *
 * This exception is used when the user inputs a malformed or incomplete Todo task.
 * The correct format is "todo <task>", where <task> is the description of the Todo item.
 */
 public class InvalidTodoException extends Exception{
    public InvalidTodoException() { super("Invalid todo format! Use: todo <task>"); }
}
