package ChadJibiti.TaskList;

/**
 * Represents a Todo task in the task management system.
 * <p>
 * A Todo task is a simple task that has a description and tracks its completion status.
 * This class extends the Task class and adds a specific identifier for the Todo task type.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description the description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task, including its task type
     * and completion status.
     *
     * @return a string representing the Todo task in the format: "[T][status] description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
