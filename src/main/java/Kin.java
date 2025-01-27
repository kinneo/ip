import java.util.Scanner;

public class Kin {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Kin");
        Scanner in = new Scanner(System.in);
        System.out.println("What can I do for you?");
        String line = "";
        while (!(line.equals("bye"))) {
            line = in.nextLine();
            if (line.equalsIgnoreCase("bye")) {
                System.out.println("Bye, hope to see you again soon!");
                break;
            } else {
                System.out.println(line);
            }
        }
    }
}