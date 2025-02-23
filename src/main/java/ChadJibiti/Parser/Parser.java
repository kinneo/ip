package ChadJibiti.Parser;

import ChadJibiti.Exceptions.InvalidTaskNumberException;
import ChadJibiti.Exceptions.InvalidTodoException;
import ChadJibiti.Exceptions.InvalidDeadlineException;
import ChadJibiti.Exceptions.InvalidEventException;
import ChadJibiti.Exceptions.EmptyTaskListException;
import ChadJibiti.Exceptions.InvalidFindException;
import ChadJibiti.TaskList.Deadline;
import ChadJibiti.TaskList.Events;
import ChadJibiti.TaskList.Task;
import ChadJibiti.TaskList.Todo;
import ChadJibiti.TaskList.TaskManager;

import java.util.ArrayList;

/**
 * The Parser class processes user commands and converts them into corresponding actions
 * on the TaskManager. It validates the input and handles different task-related commands
 * such as adding, deleting, marking, unmarking and finding tasks.
 */
public class Parser {
    private TaskManager taskManager;
    private ArrayList<Task> tasks;

    /**
     * Constructor for the Parser class.
     *
     * @param taskManager The TaskManager instance used to manage tasks.
     */
    public Parser(TaskManager taskManager) {
        this.taskManager = taskManager;
        this.tasks = taskManager.getTasks();
    }

    /**
     * Decodes the user's command and performs the corresponding task operation.
     * Validates the input and handles errors such as invalid task numbers or missing details.
     *
     * @param line The command entered by the user.
     * @throws InvalidTodoException       If the "todo" command is missing a task description.
     * @throws EmptyTaskListException     If the task list is empty when trying to delete, find, mark, or unmark tasks.
     * @throws InvalidDeadlineException   If the "deadline" command is missing the required information or is formatted incorrectly.
     * @throws InvalidEventException      If the "event" command is missing the required information or is formatted incorrectly.
     * @throws InvalidTaskNumberException If the task number is invalid or out of bounds.
     * @throws IllegalArgumentException   If the command is not recognized or is invalid.
     */
    public void decodeCommand(String line) {
        String[] parts = line.split(" ", 2);
        String command = parts[0].toLowerCase();
        try {
            switch (command) {
            case "find":
                if (line.length() == 4) {
                    throw new InvalidFindException();
                }
                if (tasks.isEmpty()) {
                    throw new EmptyTaskListException();
                }
                taskManager.findTask(line.substring(5).trim());
                break;
            case "todo":
                if (line.length() == 4) {
                    throw new InvalidTodoException();
                }
                taskManager.addTask(new Todo(line.substring(5)));
                break;
            case "delete":
                if (tasks.isEmpty()) {
                    throw new EmptyTaskListException();
                }
                int deleteIndex = parseTaskIndex(line, 7);
                taskManager.removeTask(deleteIndex);
                break;
            case "deadline":
                if (line.length() == 8) {
                    throw new InvalidDeadlineException();
                }
                String[] dParts = line.substring(9).split(" /by ", 2);
                if (dParts.length != 2) {
                    throw new InvalidDeadlineException();
                }
                taskManager.addTask(new Deadline(dParts[0], dParts[1]));
                break;
            case "event":
                if (line.length() == 5) {
                    throw new InvalidEventException();
                }
                String[] eParts = line.substring(6).split(" /from ", 2);
                if (eParts.length != 2 || !eParts[1].contains(" /to ")) {
                    throw new InvalidEventException();
                }
                String[] times = eParts[1].split("/to ", 2);
                taskManager.addTask(new Events(eParts[0], times[0], times[1]));
                break;
            case "mark":
                if (tasks.isEmpty()) {
                    throw new EmptyTaskListException();
                }
                int markIndex = parseTaskIndex(line, 5); // Use parseTaskIndex for mark
                taskManager.markTask(markIndex);
                break;
            case "unmark":
                if (tasks.isEmpty()) {
                    throw new EmptyTaskListException();
                }
                int unmarkIndex = parseTaskIndex(line, 7); // Use parseTaskIndex for unmark
                taskManager.unmarkTask(unmarkIndex);
                break;
            default:
                throw new IllegalArgumentException("Sorry g I don't understand that command. Try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        } catch (InvalidFindException | EmptyTaskListException | InvalidTodoException | InvalidDeadlineException |
                 InvalidEventException | InvalidTaskNumberException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred. Please try again.");
        }
    }

    /**
     * Parses the task index from the user input.
     * The index is adjusted to be zero-based and validated to ensure it is within the valid task list range.
     *
     * @param line       The command entered by the user.
     * @param startIndex The index where the task number starts in the user input.
     * @return The zero-based task index.
     * @throws InvalidTaskNumberException If the task number is invalid or out of bounds.
     */
    private int parseTaskIndex(String line, int startIndex) throws InvalidTaskNumberException {
        if (line.length() >= startIndex && Character.isDigit(line.charAt(startIndex))) {
            int index = Integer.parseInt(line.substring(startIndex).trim()) - 1;
            if (index >= 0 && index < tasks.size()) {
                return index;
            } else {
                throw new InvalidTaskNumberException();
            }
        } else {
            throw new InvalidTaskNumberException();
        }
    }
}
