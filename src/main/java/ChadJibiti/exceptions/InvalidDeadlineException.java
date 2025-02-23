package ChadJibiti.exceptions;

public class InvalidDeadlineException extends Exception{
    public InvalidDeadlineException() { super("Invalid deadline format! Use: deadline <task> /by <date>"); }
}
