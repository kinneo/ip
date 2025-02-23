package ChadJibiti.exceptions;

public class InvalidTodoException extends Exception{
    public InvalidTodoException() { super("Invalid todo format! Use: todo <task>"); }
}
