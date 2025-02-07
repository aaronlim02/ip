package notchatgpt;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class NotChatGPT {
    private static final String FILENAME = "data.txt";
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public NotChatGPT() {
        this.ui = new Ui();
        this.storage = new Storage(FILENAME);
        ArrayList<Task> loadedTasks = storage.load();
        this.tasks = new TaskList(loadedTasks);
    }

    public static String showWelcome() {
        return "Hello! I'm NotChatGPT\nWhat can I do for you?\n";
    }

    public String getResponse(String rawInput) {

        String input = ui.readCommand(rawInput);
        String output;

        if (Parser.isByeCommand(input)) {
            output = ui.showGoodbye();
        } else if (Parser.isListCommand(input)) {
            output = ui.showTaskList("Here are your tasks: ", tasks);
        } else if (Parser.isDeleteCommand(input)) {
            output = handleDelete(input);
        } else if (Parser.isMarkCommand(input)) {
            output = handleMark(input);
        } else if (Parser.isUnmarkCommand(input)) {
            output = handleUnmark(input);
        } else if (Parser.isTodoCommand(input)) {
            output = handleTodo(input);
        } else if (Parser.isDeadlineCommand(input)) {
            output = handleDeadline(input);
        } else if (Parser.isEventCommand(input)) {
            output = handleEvent(input);
        } else if (Parser.isFindCommand(input)) {
            output = handleFind(input);
        } else {
            output = ui.showError("I don't understand.");
        }
        return output;
    }

    private String handleFind(String input) {
        String output;
        if (input.length() <= 5) {
            output = ui.showError("Could not find. Target is required!");
            return output;
        }
        String target = Parser.parseFindCommand(input);
        TaskList matchingTasks = new TaskList(new ArrayList<Task>());
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(target)) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.getSize() == 0) {
            output = ui.showMessage("No tasks found.");
        } else {
            output = ui.showTaskList("Tasks found: ", matchingTasks);
        }
        return output;
    }

    private String handleDelete(String input) {
        String output;
        try {
            int index = Parser.parseDeleteIndex(input);
            Task deletedTask = tasks.delete(index);
            output = ui.showMessage("Noted! I've removed:\n  " + deletedTask);
            storage.save(tasks.getAllTasks());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            output = ui.showError("Invalid task number.");
        }
        return output;
    }

    private String handleMark(String input) {
        String output;
        try {
            int index = Parser.parseMarkUnmarkIndex(input);
            tasks.mark(index);
            output = ui.showMessage("Nice! I've marked this task as done:\n  " + tasks.get(index));
            storage.save(tasks.getAllTasks());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            output = ui.showError("Invalid task number.");
        }
        return output;
    }

    private String handleUnmark(String input) {
        String output;
        try {
            int index = Parser.parseMarkUnmarkIndex(input);
            tasks.unmark(index);
            output = ui.showMessage("OK, I've marked this task as not done yet:\n  " + tasks.get(index));
            storage.save(tasks.getAllTasks());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            output = ui.showError("Invalid task number.");
        }
        return output;
    }

    private String handleTodo(String input) {
        String output;
        if (input.length() <= 5) {
            output = ui.showError("Could not add To-do. Description is required!");
            return output;
        }
        String description = Parser.parseTodoDescription(input);
        tasks.add(new ToDo(description));
        output = ui.showMessage("To-do added: " + description);
        storage.save(tasks.getAllTasks());
        return output;
    }

    private String handleDeadline(String input) {
        String output;
        try {
            String[] details = Parser.parseDeadlineDetails(input);
            LocalDate by = LocalDate.parse(details[1]);
            tasks.add(new Deadline(details[0], by));
            output = ui.showMessage("Deadline added: " + details[0]);
            storage.save(tasks.getAllTasks());
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            output = ui.showError("Invalid deadline format. Use: deadline <desc> /by <yyyy-mm-dd>");
            return output;
        }
        return output;
    }

    private String handleEvent(String input) {
        String output;
        try {
            String[] details = Parser.parseEventDetails(input);
            LocalDate from = LocalDate.parse(details[1]);
            LocalDate to = LocalDate.parse(details[2]);
            tasks.add(new Event(details[0], from, to));
            output = ui.showMessage("Event added: " + details[0]);
            storage.save(tasks.getAllTasks());
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            output = ui.showError("Invalid event format. Use: event <desc> /from <yyyy-mm-dd> /to <yyyy-mm-dd>");
        }
        return output;
    }
}
