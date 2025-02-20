package kin.ui;

import kin.task.Task;
import java.util.Scanner;
import java.util.ArrayList;
import kin.data.FileHandler;
import kin.data.TaskManager;

public class Kin {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static FileHandler fileHandler;

    public Kin() {
        String filePath = "C:\\Users\\Asus\\ip\\kintasks.txt";
        fileHandler = new FileHandler(filePath);
        tasks = fileHandler.loadTasks();
    }

    public static void main(String[] args) {
        System.out.println("Wagwan my g! The name's Kin");
        System.out.println("What can I do for you today?");
        Scanner in = new Scanner(System.in);
        Kin kin = new Kin();
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
