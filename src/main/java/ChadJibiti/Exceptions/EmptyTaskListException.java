package ChadJibiti.Exceptions;

/**
 * Exception thrown when an operation is attempted on an empty task list.
 * <p>
 * This exception is used to inform the user that their task list is currently empty,
 * and suggests they take a break as no tasks are available to act upon.
 */
public class EmptyTaskListException extends Exception {
    public EmptyTaskListException() {
        super("Your list is currently empty, go take a break you deserve it.");
    }
}
