package notchatgpt;

import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);

    /**
     * Reads a command from the user input.
     * This method ensures that the input is not null or empty.
     *
     * @param s The user input command to be read.
     * @return The trimmed command string.
     * @throws AssertionError If the command is null or empty.
     */
    public String readCommand(String s) throws AssertionError {
        assert s != null : "Command cannot be null";
        assert !s.trim().isEmpty() : "Command cannot be empty";

        return s.trim();
    }

    /**
     * Generates a welcome message.
     *
     * @param name The name of the application or system.
     * @return A welcome message string.
     */
    public String showWelcome(String name) {
        return "Hello! I'm " + name + "\n" + "What can I do for you?\n";
    }

    /**
     * Generates a goodbye message.
     *
     * @return A goodbye message string.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Generates a string representation of the task list with a title.
     * Iterates through the task list and adds each task to the display string.
     *
     * @param title The title of the task list to be displayed.
     * @param tasks The list of tasks to display.
     * @return A string representing the title and tasks in the list.
     */
    public String showTaskList(String title, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(title);
        for (int i = 0; i < tasks.getSize(); i++) {
            sb.append("\n" + (i + 1) + "." + tasks.get(i));
        }
        return sb.toString();
    }

    /**
     * Displays a list of available commands for the user.
     *
     * @return A string listing all available commands.
     */
    public String showHelp() {
        return "Available commands: bye, list, delete, mark, unmark, todo, deadline, event, find, update, help";
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     * @return The error message string.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Displays a generic message.
     *
     * @param message The message to be displayed.
     * @return The message string.
     */
    public String showMessage(String message) {
        return message;
    }
}
