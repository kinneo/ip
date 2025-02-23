package ChadJibiti.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * <p>
 * The deadline can be provided as a date-time string in the format "dd/MM/yyyy HHmm".
 * If the input follows this format, it will be reformatted for better readability.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Creates a new Deadline task with a description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline in "dd/MM/yyyy HHmm" format or as a regular string.
     */
    public Deadline(String description, String by) {
        super(description);
        if (isValidDateTime(by)) {
            this.by = formatDateTime(by);
        } else {
            this.by = by;
        }
    }

    /**
     * Checks if the provided deadline string follows the expected date-time format.
     *
     * @param by The deadline string to validate.
     * @return {@code true} if the format is valid, {@code false} otherwise.
     */
    private boolean isValidDateTime(String by) {
        try {
            DateTimeFormatter output = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime.parse(by, output);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Formats a valid date-time string into a more readable format.
     *
     * @param by The deadline string in "dd/MM/yyyy HHmm" format.
     * @return The formatted deadline as "dd MMM yyyy h:mm a".
     */
    private String formatDateTime(String by) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(by, inputFormatter);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
        return dateTime.format(dateFormatter) + " " + dateTime.format(timeFormatter);
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return A formatted string representing the task and its deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
