import java.io.IOException;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
public class NotChatGPT {

    public static void save(ArrayList<Task> tasklist) {
        String filename = "data.txt";
        String header = "Field1,Field2,Field3,Field4";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (int i = 0; i < tasklist.size(); i++) {
                Task task = tasklist.get(i);
                String line = "";
                if (task instanceof ToDo) {
                    line = String.join(";",
                        "T",
                        task.description
                    );
                } else if (task instanceof Deadline d) {
                  line = String.join(";",
                        "D",
                        d.description,
                        d.by
                    );
                } else if (task instanceof Event e) {
                  line = String.join(";",
                        "E",
                        e.description,
                        e.from,
                        e.to
                    );
                }
                writer.write(line);
                writer.newLine();
            }
            writer.flush();
            System.out.println("Saved to disk.");
        } catch (IOException e) {
            System.out.println("Could not save to disk.");
        }
    }

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
                System.out.println("Here are your tasks:");
                for (int i = 0; i < tasklist.toArray().length; i++) {
                    System.out.println(i+1 + "." + tasklist.get(i).toString());
                }
            } else if (echo.substring(0, Math.min(echo.length(), 6)).equals("delete")) {
                if (echo.length() < 8) {
                    System.out.println("Could not delete. Task number is required!\n");
                    continue;
                }
                try {
                    int id = Integer.parseInt(echo.substring(7)) - 1;
                    Task t = tasklist.remove(id);
                    System.out.println("Noted! I've removed:");
                    System.out.println("  " + t.toString());
                    save(tasklist);
                } catch (NumberFormatException e) {
                    System.out.println("Could not delete. Please check you've entered a number!");
                }
            } else if (echo.substring(0, Math.min(echo.length(), 4)).equals("mark")) {
                if (echo.length() < 6) {
                    System.out.println("Could not mark. Task number is required!\n");
                    continue;
                }
                try {
                    int id = Integer.parseInt(echo.substring(5)) - 1;
                    tasklist.get(id).setStatusIcon('X');
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasklist.get(id).toString());
                } catch (NumberFormatException e) {
                    System.out.println("Could not mark. Please check you've entered a number!");
                }

            } else if (echo.substring(0, Math.min(echo.length(), 6)).equals("unmark")) {
                if (echo.length() < 8) {
                    System.out.println("Could not unmark. Please check you've entered a number!\n");
                    continue;
                }
                try {
                    int id = Integer.parseInt(echo.substring(7)) - 1;
                    tasklist.get(id).setStatusIcon(' ');
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasklist.get(id).toString());
                } catch (NumberFormatException e) {
                    System.out.println("Could not unmark. Please enter a task number!");
                }
            } else if (echo.substring(0, Math.min(echo.length(), 4)).equals("todo")) {
                if (echo.length() < 6) {
                    System.out.println("Could not add To-do. Description is required!\n");
                    continue;
                }
                ToDo newToDo = new ToDo(echo.substring(5));
                tasklist.add(newToDo);
                System.out.println("To-do added: " + newToDo);
                save(tasklist);
            } else if (echo.substring(0, Math.min(echo.length(), 8)).equals("deadline")) {
                int byIndex = echo.indexOf("/by");
                if (byIndex == -1) { // no /by
                    System.out.println("Could not add Deadline. /by is required!\n");
                    continue;
                } else if (byIndex - 9 < 1) { // no description
                    System.out.println("Could not add Deadline. Description is required!\n");
                    continue;
                } else if (echo.length() < byIndex + 5) { // no due date
                    System.out.println("Could not add Deadline. Due date is required!\n");
                    continue;
                }
                Deadline newDeadline = new Deadline(echo.substring(9, byIndex - 1),
                    echo.substring(byIndex + 4));
                tasklist.add(newDeadline);
                System.out.println("Deadline added: " + newDeadline);
                save(tasklist);
            } else if (echo.substring(0, Math.min(echo.length(), 5)).equals("event")) {
                int fromIndex = echo.indexOf("/from");
                int toIndex = echo.indexOf("/to");
                if (fromIndex == -1) {
                    System.out.println("Could not add Event. /from is required!\n");
                    continue;
                } else if (toIndex == -1) {
                    System.out.println("Could not add Event. /to is required!\n");
                    continue;
                } else if (fromIndex - 6 < 1) { // no description
                    System.out.println("Could not add Event. Description is required!\n");
                    continue;
                } else if (toIndex - fromIndex - 6 < 1) { // no from date
                    System.out.println("Could not add Event. From date is required!\n");
                    continue;
                } else if (echo.length() < toIndex + 5) { // no to date
                    System.out.println("Could not add Event. To date is required!\n");
                    continue;
                }
                Event newEvent = new Event(echo.substring(6, fromIndex - 1),
                    echo.substring(fromIndex + 6, toIndex - 1),
                    echo.substring(toIndex + 4));
                tasklist.add(newEvent);
                System.out.println("Event added: " + newEvent);
                save(tasklist);
            } else {
                System.out.println("I don't understand");
            }

            System.out.println();

        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
