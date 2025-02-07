package notchatgpt;

import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);

    public String readCommand(String s) {
        return s.trim();
    }

    public String showWelcome(String name) {
        return "Hello! I'm " + name + "\n" + "What can I do for you?\n";
    }

    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Iterates across the task list and generates a list to display all to the user
     *
     * @param title Title of the displayed list
     * @param tasks List of tasks to show
     */
    public String showTaskList(String title, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(title + "\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            sb.append((i + 1) + "." + tasks.get(i));
        }
        return sb.toString();
    }

    public String showError(String message) {
        return message;
    }

    public String showMessage(String message) {
        return message;
    }
}
