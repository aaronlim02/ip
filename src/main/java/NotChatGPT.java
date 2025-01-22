import java.util.*;
public class NotChatGPT {

    public static void main(String[] args) {
        ArrayList<String> tasklist = new ArrayList<>();
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
                    System.out.println(i+1 + ". " + tasklist.get(i));
                }
                System.out.println();
            } else {
                System.out.println("added:" + echo + '\n');
                tasklist.add(echo);
            }


        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
