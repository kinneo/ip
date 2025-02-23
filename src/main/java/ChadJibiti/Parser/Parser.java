package ChadJibiti.Parser;

import ChadJibiti.Exceptions.InvalidTaskNumberException;
import ChadJibiti.Exceptions.InvalidTodoException;
import ChadJibiti.Exceptions.InvalidDeadlineException;
import ChadJibiti.Exceptions.InvalidEventException;
import ChadJibiti.Exceptions.EmptyTaskListException;
import ChadJibiti.TaskList.Deadline;
import ChadJibiti.TaskList.Events;
import ChadJibiti.TaskList.Task;
import ChadJibiti.TaskList.Todo;
import ChadJibiti.TaskList.TaskManager;
import java.util.ArrayList;

public class Parser {
    private TaskManager taskManager;
    private ArrayList<Task> tasks;

    public Parser(TaskManager taskManager) {
        this.taskManager = taskManager;
        this.tasks = taskManager.getTasks();
    }

    public void decodeCommand(String line){
        String[] parts = line.split(" ", 2);
        String command = parts[0].toLowerCase();
        try {
            switch(command){
            case "todo":
                if (line.length() == 4) {
                    throw new InvalidTodoException();
                }
                taskManager.addTask(new Todo(line.substring(5)));
                break;
            case "delete":
                if (tasks.isEmpty()){
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
                if (tasks.isEmpty()){
                    throw new EmptyTaskListException();
                }
                int markIndex = parseTaskIndex(line, 5); // Use parseTaskIndex for mark
                taskManager.markTask(markIndex);
                break;
            case "unmark":
                if (tasks.isEmpty()){
                    throw new EmptyTaskListException();
                }
                int unmarkIndex = parseTaskIndex(line, 7); // Use parseTaskIndex for unmark
                taskManager.unmarkTask(unmarkIndex);
                break;
            default:
                throw new IllegalArgumentException("Sorry g I don't understand that command. Try again.");
            }
        } catch (NumberFormatException e){
            System.out.println("Please enter a valid number.");
        } catch (EmptyTaskListException | InvalidTodoException | InvalidDeadlineException | InvalidEventException | InvalidTaskNumberException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred. Please try again.");
        }
    }

    private int parseTaskIndex(String line, int startIndex) throws InvalidTaskNumberException {
        if (line.length() >= startIndex && Character.isDigit(line.charAt(startIndex))) {
            int index = Integer.parseInt(line.substring(startIndex).trim()) - 1;
            if (index >= 0 && index < tasks.size()){
                return index;
            } else {
                throw new InvalidTaskNumberException();
            }
        } else {
            throw new InvalidTaskNumberException();
        }
    }
}
