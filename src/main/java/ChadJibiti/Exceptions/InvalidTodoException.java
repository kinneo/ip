package ChadJibiti.Exceptions;

public class InvalidTodoException extends Exception{
    public InvalidTodoException() { super("Invalid todo format! Use: todo <task>"); }
}
