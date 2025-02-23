package ChadJibiti.Exceptions;

public class InvalidEventException extends Exception{
    public InvalidEventException() { super("Invalid event format! Use: event <task> /from <start> /to <end>"); }
}
