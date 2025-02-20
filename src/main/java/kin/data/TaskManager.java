package kin.data;

import kin.exceptions.InvalidTaskNumberException;
import kin.exceptions.InvalidTodoException;
import kin.exceptions.InvalidDeadlineException;
import kin.exceptions.InvalidEventException;
import kin.exceptions.EmptyTaskListException;
import kin.task.Deadline;
import kin.task.Events;
import kin.task.Task;
import kin.task.Todo;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class TaskManager {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static FileHandler fileHandler;

    public TaskManager(FileHandler fileHandler, ArrayList<Task> tasks) {
        this.fileHandler = fileHandler;
        this.tasks = tasks;  // Use the same task list as Kin
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
                addTask(new Todo(line.substring(5)));
                break;
            case "delete":
                if (tasks.isEmpty()){
                    throw new EmptyTaskListException();
                }
                if (line.length() >= 7 && Character.isDigit(line.charAt(7))) {
                    int index = Integer.parseInt(line.substring(7).trim()) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        removeTask(index);
                    } else {
                        throw new InvalidTaskNumberException();
                    }
                } else {
                    throw new InvalidTaskNumberException();
                }
                break;
            case "deadline":
                if (line.length() == 8) {
                    throw new InvalidDeadlineException();
                }
                String[] dParts = line.substring(9).split(" /by ", 2);
                if (dParts.length != 2) {
                    throw new InvalidDeadlineException();
                }
                addTask(new Deadline(dParts[0], dParts[1]));
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
                addTask(new Events(eParts[0], times[0], times[1]));
                break;
            case "mark":
                if (tasks.isEmpty()){
                    throw new EmptyTaskListException();
                }
                if (line.length() >= 5 && Character.isDigit(line.charAt(5))) {
                    int index = Integer.parseInt(line.substring(5).trim()) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        markTask(index);
                    } else {
                        throw new InvalidTaskNumberException();
                    }
                } else {
                    throw new InvalidTaskNumberException();
                }
                break;
            case "unmark":
                if (tasks.isEmpty()){
                    throw new EmptyTaskListException();
                }
                if (line.length() >= 7 && Character.isDigit(line.charAt(7))) {
                    int index = Integer.parseInt(line.substring(7).trim()) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        unmarkTask(index);
                    } else {
                        throw new InvalidTaskNumberException();
                    }
                } else {
                    throw new InvalidTaskNumberException();
                }
                break;
            default:
                throw new IllegalArgumentException("Sorry g I don't understand that command. Try again " + randomString() + ".");
            }
        } catch (NumberFormatException e){
            System.out.println("Please enter a valid number " + randomString() + ".");
        } catch (EmptyTaskListException | InvalidTodoException | InvalidDeadlineException | InvalidEventException | InvalidTaskNumberException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred. Please try again.");
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
        fileHandler.saveTasks(tasks);
        System.out.println("Roger. I've added this task my g:");
        System.out.println(" " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list, better get to work you " + randomString() + "!");
    }

    public void removeTask(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        fileHandler.saveTasks(tasks);
        System.out.println("Roger. I've removed this task my g:");
        System.out.println(" " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list, better get to work you " + randomString() + "!");
    }

    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found, go take a break or sum.");
        } else{
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
            System.out.println("Get to work you " + randomString() + "!");
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
            System.out.println("Task is already marked " + randomString() + "!");
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
            System.out.println(randomString() + ", task is already unmarked!");
        }
    }

    public static String randomString() {
        List<String> list = List.of(
                "dummy", "stupid", "loser", "dum dum",
                "failure", "bum", "airhead", "lazy bum",
                "useless", "dumbass", "idiot", "dimwit",
                "doofus", "noob", "noobie", "pathetic",
                "wimp", "dork", "dweeb", "BAKAAA",
                "fool", "pea brain", "clown", "meathead",
                "goofball", "dunce"
        );
        return list.get(new Random().nextInt(list.size()));
    }
}
