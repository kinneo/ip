import java.util.Scanner;

public class Kin {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Kin");
        Scanner in = new Scanner(System.in);
        System.out.println("What can I do for you?");
        String[] list = new String[100];
        int count = 0;
        String line = "";
        while (!(line.equals("bye"))) {
            line = in.nextLine(); // .trim
            if (line.equalsIgnoreCase("bye")) {
                System.out.println("Bye, hope to see you again soon!");
                break;
            } else if (line.equalsIgnoreCase("list")) {
                if (count == 0){
                    System.out.println("No tasks found.");
                } else {
                    // print list
                    for (int i = 0; i < count; i++) {
                        System.out.println((i + 1) + "." + list[i]);
                    }
                }
            } else {
                // store the line into list arr
                list[count] = line;
                count++;
                System.out.println("added " + line);
            }
        }
    }
}