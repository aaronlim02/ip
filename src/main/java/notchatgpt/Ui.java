package notchatgpt;

import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);

    public String readCommand() {
        return sc.nextLine().trim();
    }

    public void showWelcome(String name) {
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println();
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Iterates across the task list and generates a list to display all to the user
     *
     * @param title Title of the displayed list
     * @param tasks List of tasks to show
     */
    public void showTaskList(String title, TaskList tasks) {
        System.out.println(title);
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}