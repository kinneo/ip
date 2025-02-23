package ChadJibiti.TaskList;

import ChadJibiti.Storage.FileHandler;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;
    private FileHandler fileHandler;

    public TaskManager(FileHandler fileHandler, ArrayList<Task> tasks) {
        this.fileHandler = fileHandler;
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        fileHandler.saveTasks(tasks);
        System.out.println("Roger. I've added this task my g:");
        System.out.println(" " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list, better get to work!");
    }

    public void removeTask(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        fileHandler.saveTasks(tasks);
        System.out.println("Roger. I've removed this task my g:");
        System.out.println(" " + task);
        if (tasks.size() == 0){
            System.out.println("Now you have " + tasks.size() + " tasks in the list, go take a break!");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in the list, better get to work!");
        }
    }

    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found, go take a break or sum.");
        } else{
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
            System.out.println("Get to work!");
        }
    }

    public void markTask(int index) {
        Task task = tasks.get(index);
        if (!task.isDone){
            task.markAsDone();
            fileHandler.saveTasks(tasks);
            System.out.println("Solid bruv! I've marked this task as done:");
            System.out.println(" " + task);
        } else {
            System.out.println("Task is already marked!");
        }
    }

    public void unmarkTask(int index) {
        Task task = tasks.get(index);
        if (task.isDone){
            task.unmarkAsDone();
            fileHandler.saveTasks(tasks);
            System.out.println("Bro has no discipline, fine... I've unmarked this task:");
            System.out.println(" " + task);
        } else  {
            System.out.println("Task is already unmarked!");
        }
    }
}
