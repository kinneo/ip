package kin.data;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import kin.task.Task;
import kin.task.Deadline;
import kin.task.Events;
import kin.task.Todo;

public class FileHandler {
    private String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    // Load tasks from file
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks; // Return empty list if file does not exist
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                char taskType = line.charAt(1);
                boolean isDone = line.charAt(4) == 'X';

                switch (taskType) {
                case 'T': // Todo
                    //System.out.println("Recognized todo task: " + line);
                    String todoDescription = line.substring(7);
                    Todo todo = new Todo(todoDescription);
                    if(isDone){
                        todo.markAsDone();
                    }
                    tasks.add(todo);
                    break;
                case 'D': // Deadline
                    //System.out.println("Recognized deadline task: " + line);
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
                    //System.out.println("Recognized event task: " + line);
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

    // Save tasks to file
    public void saveTasks(ArrayList<Task> tasks) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toString() + System.lineSeparator()); // Assuming Task has a meaningful toString() method
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}