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
                    System.out.println(i+1 + "." + tasklist.get(i).toString());
                }
            } else if (echo.substring(0, Math.min(echo.length(), 4)).equals("mark")) {
                // change event status to 'X'
                int id = Integer.parseInt(echo.substring(5)) - 1;
                tasklist.get(id).setStatusIcon('X');
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasklist.get(id).toString());
            } else if (echo.substring(0, Math.min(echo.length(), 6)).equals("unmark")) {
                // change event status to ' '
                int id = Integer.parseInt(echo.substring(7)) - 1;
                tasklist.get(id).setStatusIcon(' ');
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasklist.get(id).toString());
            } else if (echo.substring(0, Math.min(echo.length(), 4)).equals("todo")) {
                ToDo newToDo = new ToDo(echo.substring(5));
                tasklist.add(newToDo);
                System.out.println("To-do added: " + newToDo);
            } else if (echo.substring(0, Math.min(echo.length(), 8)).equals("deadline")) {
                int byIndex = echo.indexOf("/by");
                Deadline newDeadline = new Deadline(echo.substring(9, byIndex - 1),
                    echo.substring(byIndex + 4));
                tasklist.add(newDeadline);
                System.out.println("Deadline added: " + newDeadline);
            } else if (echo.substring(0, Math.min(echo.length(), 5)).equals("event")) {
                int fromIndex = echo.indexOf("/from");
                int toIndex = echo.indexOf("/to");
                Event newEvent = new Event(echo.substring(6, fromIndex - 1),
                    echo.substring(fromIndex + 6, toIndex),
                    echo.substring(toIndex + 4));
                tasklist.add(newEvent);
                System.out.println("Event added: " + newEvent);
            }

            System.out.println();

        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
