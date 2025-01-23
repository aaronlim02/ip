import java.util.*;
public class NotChatGPT {

    public static void main(String[] args) {
        ArrayList<Task> tasklist = new ArrayList<>();
        String name = "Not ChatGPT";
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println();
        while (true) {
            String echo = sc.nextLine();
            if (echo.equals("bye")) {
                break;
            } else if (echo.equals("list")) {
                for (int i = 0; i < tasklist.toArray().length; i++) {
                    System.out.println(i+1 + ".[" + tasklist.get(i).getStatusIcon()
                        + "] " + tasklist.get(i).getDescription());
                }
            } else if (echo.substring(0, Math.min(echo.length(), 4)).equals("mark")) {
                // change event status to 'X'
                int id = Integer.parseInt(echo.substring(5)) - 1;
                tasklist.get(id).setStatusIcon('X');
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [" + tasklist.get(id).getStatusIcon()
                    + "] " + tasklist.get(id).getDescription());
            } else if (echo.substring(0, Math.min(echo.length(), 6)).equals("unmark")) {
                // change event status to ' '
                int id = Integer.parseInt(echo.substring(7)) - 1;
                tasklist.get(id).setStatusIcon(' ');
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  [" + tasklist.get(id).getStatusIcon()
                    + "] " + tasklist.get(id).getDescription());
            } else {
                System.out.println("added:" + echo + '\n');
                tasklist.add(new Task(echo));
            }

            System.out.println();

        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
