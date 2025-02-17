package kin.ui;


import kin.exceptions.InvalidTaskNumber;
import kin.exceptions.InvalidTodo;
import kin.exceptions.InvalidDeadline;
import kin.exceptions.InvalidEvent;
import kin.exceptions.EmptyTaskList;
import kin.task.Deadline;
import kin.task.Events;
import kin.task.Task;
import kin.task.Todo;

import java.util.Scanner;
import java.util.List;
import java.util.Random;

import java.util.ArrayList;

import kin.FileHandler;

public class Kin {
    private static ArrayList<Task> tasks = new ArrayList<>();

    private static FileHandler fileHandler;

    public Kin() {
        String filePath = "C:\\Users\\Asus\\ip\\kintasks.txt";
        fileHandler = new FileHandler(filePath);
        tasks = fileHandler.loadTasks();
    }

    public static void addTask(Task task) {
        tasks.add(task);
        fileHandler.saveTasks(tasks);
        System.out.println("Roger. I've added this task my g:");
        System.out.println(" " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list, better get to work you " + randomString() + "!");
    }

    public static void removeTask(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        fileHandler.saveTasks(tasks);
        System.out.println("Roger. I've removed this task my g:");
        System.out.println(" " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list, better get to work you " + randomString() + "!");
    }

    public static void printTasks() {
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

    public static void markTask(int index) {
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

    public static void unmarkTask(int index) {
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
        List<String> list = List.of("dummy", "stupid", "loser", "dum dum",
                "failure", "bum", "airhead", "lazy bum", "useless", "dumbass", "idiot",
                "dimwit", "doofus", "noob", "noobie", "pathetic", "wimp", "dork", "dweeb",
                "BAKAAA", "fool", "pea brain", "clown", "meathead", "goofball", "dunce");
        return list.get(new Random().nextInt(list.size()));
    }

    public static void main(String[] args) {
        System.out.println("Wagwan my g! The name's Kin");
        System.out.println("What can I do for you today?");
        Scanner in = new Scanner(System.in);
        Kin kin = new Kin();


        while (true) {
            try {
                String line = in.nextLine().trim();
                if (line.equalsIgnoreCase("bye")) {
                    fileHandler.saveTasks(tasks);
                    System.out.println("Aite time to bounce, sayonara!");
                    break;
                } else if (line.equalsIgnoreCase("list")) {
                    printTasks();
                } else if (line.toLowerCase().startsWith("todo")) {
                    if (line.length() == 4) {
                        throw new InvalidTodo("Todo field cannot be empty " + randomString() + "!");
                    }
                    addTask(new Todo(line.substring(5)));
                } else if (line.toLowerCase().startsWith("delete")){
                    if (tasks.isEmpty()){
                        throw new EmptyTaskList("Your list is currently empty, go take a break you deserve it.");
                    }
                    if (line.length() >= 7 && Character.isDigit(line.charAt(7))) {
                        int index = Integer.parseInt(line.substring(7).trim()) - 1;
                        if (index >= 0 && index < tasks.size()) {
                            removeTask(index);
                        } else {
                            throw new InvalidTaskNumber("Invalid task number " + randomString() + "! Please enter a valid number to delete.");
                        }
                    } else {
                        throw new InvalidTaskNumber("Delete field cannot be empty " + randomString() + "! Please enter a valid number to delete.");
                    }
                } else if (line.toLowerCase().startsWith("deadline")) {
                    if (line.length() == 8) {
                        throw new InvalidDeadline("Deadline field cannot be empty " + randomString() + "! Use: deadline <task> /by <date>");
                    }
                    String[] parts = line.substring(9).split(" /by ", 2);
                    if (parts.length != 2) {
                        throw new InvalidDeadline("Invalid deadline format " + randomString() + "! Use: deadline <task> /by <date>");
                    }
                    addTask(new Deadline(parts[0], parts[1]));
                } else if (line.toLowerCase().startsWith("event")) {
                    if (line.length() == 5) {
                        throw new InvalidEvent("Event field cannot be empty " + randomString() + "! Use: event <task> /from <start> /to <end>");
                    }
                    String[] parts = line.substring(6).split(" /from ", 2);
                    if (parts.length != 2 || !parts[1].contains(" /to ")) {
                        throw new InvalidEvent("Invalid event format " + randomString() + "! Use: event <task> /from <start> /to <end>");
                    }
                    String[] times = parts[1].split("/to ", 2);
                    addTask(new Events(parts[0], times[0], times[1]));
                } else if (line.toLowerCase().startsWith("mark")) {
                    if (tasks.isEmpty()){
                        throw new EmptyTaskList("Your list is currently empty, go take a break you deserve it.");
                    }
                    if (line.length() >= 5 && Character.isDigit(line.charAt(5))) {
                        int index = Integer.parseInt(line.substring(5).trim()) - 1;
                        if (index >= 0 && index < tasks.size()) {
                            markTask(index);
                        } else {
                            throw new InvalidTaskNumber("Invalid task number " + randomString() + "! Please enter a valid number to mark.");
                        }
                    } else {
                        throw new InvalidTaskNumber("Mark field cannot be empty " + randomString() + "! Please enter a valid number to mark.");
                    }
                } else if (line.toLowerCase().startsWith("unmark")) {
                    if (tasks.isEmpty()){
                        throw new EmptyTaskList("Your list is currently empty, go take a break you deserve it.");
                    }

                    if (line.length() >= 7 && Character.isDigit(line.charAt(7))) {
                        int index = Integer.parseInt(line.substring(7).trim()) - 1;
                        if (index >= 0 && index < tasks.size()) {
                            unmarkTask(index);
                        } else {
                            throw new InvalidTaskNumber("Invalid task number " + randomString() + "! Please enter a valid number to unmark.");
                        }
                    } else {
                        throw new InvalidTaskNumber("Unmark field cannot be empty " + randomString() + "! Please enter a valid number to unmark.");
                    }
                } else {
                    throw new IllegalArgumentException("Sorry g I don't understand that command. Try again " + randomString() + ".");
                }
            } catch (NumberFormatException e){
                System.out.println("Please enter a valid number " + randomString() + ".");
            } catch (EmptyTaskList | InvalidTodo | InvalidDeadline | InvalidEvent | InvalidTaskNumber | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred. Please try again.");
            }
        }
    }
}
