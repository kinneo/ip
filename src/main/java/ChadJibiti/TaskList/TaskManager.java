package ChadJibiti.TaskList;

import ChadJibiti.Storage.FileHandler;

import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;
    private FileHandler fileHandler;

    /**
     * Constructor for TaskManager.
     *
     * @param fileHandler The file handler used for saving tasks.
     * @param tasks       The list of tasks to be managed.
     */
    public TaskManager(FileHandler fileHandler, ArrayList<Task> tasks) {
        this.fileHandler = fileHandler;
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Searches for tasks in the list that contain the specified keyword and displays the matching tasks.
     *
     * @param keyword The keyword used to search for matching tasks in the task list.
     */
    public void findTask(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        if (foundTasks.isEmpty()) {
            System.out.println("Sorry g, there are no matching tasks in your list.");
        } else {
            System.out.println("Roger. Here are the matching tasks in your list:");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println((i + 1) + "." + foundTasks.get(i));
            }
        }
    }

    /**
     * Adds a task to the list and saves it to the file.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        fileHandler.saveTasks(tasks);
        System.out.println("Roger. I've added this task my g:");
        System.out.println(" " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list, better get to work!");
    }

    /**
     * Removes a task from the list and saves the updated list to the file.
     *
     * @param index The index of the task to be removed.
     */
    public void removeTask(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        fileHandler.saveTasks(tasks);
        System.out.println("Roger. I've removed this task my g:");
        System.out.println(" " + task);
        if (tasks.size() == 0) {
            System.out.println("Now you have " + tasks.size() + " tasks in the list, go take a break!");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in the list, better get to work!");
        }
    }

    /**
     * Prints the tasks in the list.
     */
    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found, go take a break or sum.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
            System.out.println("Get to work!");
        }
    }

    /**
     * Marks a task as done and saves the updated list to the file.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markTask(int index) {
        Task task = tasks.get(index);
        if (!task.isDone) {
            task.markAsDone();
            fileHandler.saveTasks(tasks);
            System.out.println("Solid bruv! I've marked this task as done:");
            System.out.println(" " + task);
        } else {
            System.out.println("Task is already marked!");
        }
    }

    /**
     * Unmarks a task as done and saves the updated list to the file.
     *
     * @param index The index of the task to be unmarked as done.
     */
    public void unmarkTask(int index) {
        Task task = tasks.get(index);
        if (task.isDone) {
            task.unmarkAsDone();
            fileHandler.saveTasks(tasks);
            System.out.println("Bro has no discipline, fine... I've unmarked this task:");
            System.out.println(" " + task);
        } else {
            System.out.println("Task is already unmarked!");
        }
    }
}
