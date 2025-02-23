package ChadJibiti.Ui;

import ChadJibiti.TaskList.Task;

import java.util.Scanner;
import java.util.ArrayList;

import ChadJibiti.Storage.FileHandler;
import ChadJibiti.TaskList.TaskManager;
import ChadJibiti.Parser.Parser;

/**
 * The main class that starts the ChadJibiti application.
 * Handles user input, manages tasks, and interacts with the TaskManager and Parser to process commands.
 */
public class ChadJibiti {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static FileHandler fileHandler;

    /**
     * Constructor for ChadJibiti.
     * Initializes the file handler and loads tasks from txt file.
     */
    public ChadJibiti() {
        String filePath = "C:\\Users\\Asus\\ip\\kintasks.txt";
        fileHandler = new FileHandler(filePath);
        tasks = fileHandler.loadTasks();
    }

    /**
     * Main method to start the application.
     * It displays a welcome message, listens for user input, and processes commands.
     * Allows users to interact with the task manager to view, add, or modify tasks.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

        String logo = """
                  ________  ___  ___  ________  ________             ___  ___  ________  ___  _________  ___     
                 |\\   ____\\|\\  \\|\\  \\|\\   __  \\|\\   ___ \\           |\\  \\|\\  \\|\\   __  \\|\\  \\|___   ___\\\\  \\    
                 \\ \\  \\___|\\ \\  \\\\\\  \\ \\  \\|\\  \\ \\  \\_|\\ \\          \\ \\  \\ \\  \\ \\  \\|\\ /\\ \\  \\|___ \\  \\_\\ \\  \\   
                  \\ \\  \\    \\ \\   __  \\ \\   __  \\ \\  \\ \\\\ \\       __ \\ \\  \\ \\  \\ \\   __  \\ \\  \\   \\ \\  \\ \\ \\  \\  
                   \\ \\  \\____\\ \\  \\ \\  \\ \\  \\ \\  \\ \\  \\_\\\\ \\     |\\  \\\\_\\  \\ \\  \\ \\  \\|\\  \\ \\  \\   \\ \\  \\ \\ \\  \\ 
                    \\ \\_______\\ \\__\\ \\__\\ \\__\\ \\__\\ \\_______\\    \\ \\________\\ \\__\\ \\_______\\ \\__\\   \\ \\__\\ \\ \\__\\
                     \\|_______|\\|__|\\|__|\\|__|\\|__|\\|_______|     \\|________|\\|__|\\|_______|\\|__|    \\|__|  \\|__|
                """;

        System.out.println(logo);

        System.out.println("Wagwan my g! My name is Chad Jibiti");
        System.out.println("What can I do for you today?");
        Scanner in = new Scanner(System.in);

        // Initialize ChadJibiti, TaskManager, and Parser
        ChadJibiti chatJibiti = new ChadJibiti();
        TaskManager taskManager = new TaskManager(fileHandler, tasks);
        Parser parser = new Parser(taskManager);

        // Main loop
        while (true) {
            try {
                String line = in.nextLine().trim();
                if (line.equalsIgnoreCase("bye")) {
                    fileHandler.saveTasks(tasks);
                    System.out.println("Aite time to bounce, sayonara!");
                    break;
                } else if (line.equalsIgnoreCase("list")) {
                    taskManager.printTasks();
                } else {
                    parser.decodeCommand(line);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
