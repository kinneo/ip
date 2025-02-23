package ChadJibiti.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end time.
 * <p>
 * The event times can be provided as date-time strings in the format "dd/MM/yyyy HHmm".
 * If the input follows this format, it will be reformatted for better readability.
 */
public class Events extends Task {
    protected String from;
    protected String to;

    /**
     * Creates a new Event task with a description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from        The start time in "dd/MM/yyyy HHmm" format or as a regular string.
     * @param to          The end time in "dd/MM/yyyy HHmm" format or as a regular string.
     */
    public Events(String description, String from, String to) {
        super(description);
        if (isValidDateTime(from.trim())) {
            this.from = formatDateTime(from.trim());
        } else {
            this.from = from;
        }
        if (isValidDateTime(to)) {
            this.to = formatDateTime(to).trim();
        } else {
            this.to = to;
        }
    }

    /**
     * Checks if the provided date-time string follows the expected format.
     *
     * @param line The date-time string to validate.
     * @return {@code true} if the format is valid, {@code false} otherwise.
     */
    private boolean isValidDateTime(String line) {
        try {
            DateTimeFormatter output = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime.parse(line, output);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Formats a valid date-time string into a more readable format.
     *
     * @param by The date-time string in "dd/MM/yyyy HHmm" format.
     * @return The formatted date-time as "dd MMM yyyy h:mm a".
     */
    private String formatDateTime(String by) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(by, inputFormatter);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
        return dateTime.format(dateFormatter) + " " + dateTime.format(timeFormatter) + " ";
    }

    /**
     * Returns the string representation of the Event task.
     *
     * @return A formatted string representing the event, its start, and end time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + "to: " + to + ")";
    }
}
