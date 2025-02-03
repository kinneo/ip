import java.util.Scanner;

public class Kin {
    private static Task[] tasks = new Task[100];
    private static int count = 0;

    public static void addTask(Task task) {
        tasks[count] = task;
        count++;
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + count + " tasks in the list");
    }

    public static void printTasks() {
        if (count == 0) {
            System.out.println("No tasks found.");
        } else{
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + "." + tasks[i]);
            }
        }
    }

    public static void markTask(int index) {
        if (index >= 0 && index < count){
            if (!tasks[index].isDone){
                tasks[index].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" " + tasks[index]);
            } else {
                System.out.println("Task is already marked!");
            }
        }
    }

    public static void unmarkTask(int index) {
        if (index >= 0 && index < count){
            if (tasks[index].isDone){
                tasks[index].unmarkAsDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(" " + tasks[index]);
            } else  {
                System.out.println("Task is already unmarked!");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Kin");
        Scanner in = new Scanner(System.in);
        System.out.println("What can I do for you?");

        while (true) {
            String line = in.nextLine().trim();
            if (line.equalsIgnoreCase("bye")) {
                System.out.println("Bye, hope to see you again soon!");
                break;
            } else if (line.equalsIgnoreCase("list")) {
                printTasks();
            } else if (line.toLowerCase().startsWith("todo ")) {
                addTask(new Todo(line.substring(5)));
            } else if (line.toLowerCase().startsWith("deadline ")) {
                String[] parts = line.substring(9).split(" /by ", 2);
                if (parts.length == 2) {
                    addTask(new Deadline(parts[0], parts[1]));
                } else {
                    System.out.println("Invalid deadline format!");
                }
            } else if (line.toLowerCase().startsWith("event ")) {
                String[] parts = line.substring(6).split(" /from ", 2);
                if (parts.length == 2 && parts[1].contains(" /to ")) {
                    String[] times = parts[1].split("/to ", 2);
                    addTask(new Events(parts[0], times[0], times[1]));
                } else {
                    System.out.println("Invalid event format!");
                }
            } else if (line.toLowerCase().startsWith("mark ")) {
                if (line.length() > 5 && Character.isDigit(line.charAt(5))) {
                    int index = Integer.parseInt(line.substring(5).trim()) - 1;
                    if (index >= 0 && index < count) {
                        markTask(index);
                    } else {
                        System.out.println("Please enter a valid number to mark!");
                    }
                } else {
                    System.out.println("Please enter a valid number to mark!");
                }
            } else if (line.toLowerCase().startsWith("unmark ")) {
                if (line.length() > 7 && Character.isDigit(line.charAt(7))) {
                    int index = Integer.parseInt(line.substring(7).trim()) - 1;
                    if (index >= 0 && index < count) {
                        unmarkTask(index);
                    } else {
                        System.out.println("Please enter a valid number to unmark!");
                    }
                } else {
                    System.out.println("Please enter a valid number to unmark!");
                }
            } else {
                System.out.println("Invalid command!");
            }
        }
    }
}