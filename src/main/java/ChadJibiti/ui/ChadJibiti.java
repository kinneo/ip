package ChadJibiti.ui;

import ChadJibiti.task.Task;
import java.util.Scanner;
import java.util.ArrayList;
import ChadJibiti.storage.FileHandler;
import ChadJibiti.storage.TaskManager;

public class ChadJibiti {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static FileHandler fileHandler;

    public ChadJibiti() {
        String filePath = "C:\\Users\\Asus\\ip\\kintasks.txt";
        fileHandler = new FileHandler(filePath);
        tasks = fileHandler.loadTasks();
    }

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
        ChadJibiti kin = new ChadJibiti();
        TaskManager taskManager = new TaskManager(fileHandler, tasks);

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
                    taskManager.decodeCommand(line);
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
