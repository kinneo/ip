package kin.exceptions;

public class EmptyTaskListException extends Exception {
    public EmptyTaskListException() { super("Your list is currently empty, go take a break you deserve it."); }
}
