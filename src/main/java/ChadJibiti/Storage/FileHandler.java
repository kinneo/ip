package ChadJibiti.Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import ChadJibiti.TaskList.Task;
import ChadJibiti.TaskList.Deadline;
import ChadJibiti.TaskList.Events;
import ChadJibiti.TaskList.Todo;

public class FileHandler {
    private String filePath;

    /**
     * Constructor for FileHandler.
     *
     * @param filePath The file path where tasks are saved/loaded.
     */
    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from a file and returns them as an ArrayList of Task objects.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws FileNotFoundException If the file does not exist or cannot be found.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                char taskType = line.charAt(1);
                boolean isDone = line.charAt(4) == 'X';

                switch (taskType) {
                case 'T': // Todo
                    String todoDescription = line.substring(7);
                    Todo todo = new Todo(todoDescription);
                    if(isDone){
                        todo.markAsDone();
                    }
                    tasks.add(todo);
                    break;
                case 'D': // Deadline
                    int byIndex = line.indexOf("(by: ");
                    String deadlineDescription = line.substring(7, byIndex - 1);
                    String by = line.substring(byIndex + 5, line.length() - 1);
                    Deadline deadline = new Deadline(deadlineDescription, by);
                    if(isDone){
                        deadline.markAsDone();
                    }
                    tasks.add(deadline);
                    break;
                case 'E': // Event
                    int fromIndex = line.indexOf("(from: ");
                    int toIndex = line.indexOf("to: ");
                    String eventDescription = line.substring(7, fromIndex - 1);
                    String from = line.substring(fromIndex + 7, toIndex);
                    String to = line.substring(toIndex + 4, line.length() - 1);
                    Events event = new Events(eventDescription, from, to);
                    if(isDone){
                        event.markAsDone();
                    }
                    tasks.add(event);
                    break;
                default:
                    System.out.println("Unknown task format: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves tasks to a file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If there is an error writing to the file.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}