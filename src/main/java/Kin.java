import java.util.Scanner;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class Kin {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Kin");
        Scanner in = new Scanner(System.in);
        System.out.println("What can I do for you?");
        // String[] list = new String[100];
        Task[] list = new Task[100];
        int count = 0;
        String line = "";
        while (!(line.equals("bye"))) {
            line = in.nextLine(); // .trim
            if (line.equalsIgnoreCase("bye")) {
                System.out.println("Bye, hope to see you again soon!");
                break;
            } else if (line.equalsIgnoreCase("list")) {
                if (count == 0) {
                    System.out.println("No tasks found.");
                } else {
                    // print list
                    for (int i = 0; i < count; i++) {
                        System.out.println((i + 1) + "." + list[i]);
                    }
                }
            } else if (line.length() >= 4 && line.substring(0,4).equalsIgnoreCase("mark")) {
                if (line.length() > 5 && Character.isDigit(line.charAt(5))){
                    int mark = Integer.parseInt(line.substring(5).trim()) - 1;
                    if(mark >= 0 && mark < count){
                        if (list[mark].getStatusIcon().equals(" ")) {
                            //list[mark] = list[mark].replace("[ ]", "[x]");
                            list[mark].markAsDone();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(list[mark]);
                        } else {
                            System.out.println("Task is already marked!");
                        }
                    } else {
                        System.out.println("Invalid task number!");
                    }
                } else {
                    System.out.println("Please enter a valid number to mark");
                }
            } else if (line.length() > 6 && line.substring(0,6).equalsIgnoreCase("unmark")) {
                if (line.length() > 7 && Character.isDigit(line.charAt(7))){
                    int unmark = Integer.parseInt(line.substring(7).trim()) - 1;
                    if(unmark >= 0 && unmark < count){
                        if (list[unmark].getStatusIcon().equals("X")) {
                            //list[unmark] = list[unmark].replace("[x]", "[ ]");
                            list[unmark].unmarkAsDone();
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println(list[unmark]);
                        } else  {
                            System.out.println("Task is already unmarked!");
                        }
                    } else {
                        System.out.println("Invalid task number!");
                    }
                } else {
                    System.out.println("Please enter a valid number to unmark");
                }
            } else {
                // store the line into list arr
                // list[count] = "[ ] " + line;
                list[count] = new Task(line);
                count++;
                System.out.println("added " + line);
            }
        }
    }
}