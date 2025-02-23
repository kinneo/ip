package ChadJibiti.TaskList;

/**
 * Represents a generic task in the task management system.
 * <p>
 * The Task class serves as a base class for various types of tasks such as Todo, Deadline, and Event.
 * It holds the description of the task and tracks whether the task is completed or not.
 */
public class Task {
    protected String description;
    public boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representing the task's completion status.
     *
     * @return "X" if the task is marked as done, " " if not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return a string representing the task in the format: "[status] description".
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}