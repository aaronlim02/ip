package notchatgpt;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        String input;
        try {
            input = ui.readCommand(rawInput);
        } catch (AssertionError e) {
            return ui.showError("Command cannot be empty");
        }
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
        } else if (Parser.isUpdateCommand(input)) {
            output = handleUpdate(input);
        } else {
            output = ui.showError("I don't understand.");
        }
        return output;
    }
    @SuppressWarnings("checkstyle:OperatorWrap")
    private String handleUpdate(String input) {
        String output;
        if (input.length() <= 7) {
            output = ui.showError("Could not update. Target is required! "
                    + "Use: update <id> /<parameter> <content>");
            return output;
        }
        Task editTask;
        try {
            String[] parts = Parser.parseUpdateDetails(input);
            int id = Integer.parseInt(parts[0]) - 1;
            editTask = tasks.get(id);
            ArrayList<String> editables = new ArrayList<>();
            if (editTask instanceof ToDo) {
                editables = new ArrayList<String>(List.of("desc"));
            } else if (editTask instanceof Deadline) {
                editables = new ArrayList<String>(Arrays.asList("desc", "by"));
            } else if (editTask instanceof Event) {
                editables = new ArrayList<String>(Arrays.asList("desc", "from", "to"));
            }
            if (!editables.contains(parts[1])) {
                return "Task does not support parameter: " + parts[1];
            } else if (parts[1].equals("desc")) {
                editTask.description = parts[2];
            } else if (parts[1].equals("by")) {
                assert editTask instanceof Deadline;
                ((Deadline) editTask).by = LocalDate.parse(parts[2]);
            } else if (parts[1].equals("from")) {
                assert editTask instanceof Event;
                ((Event) editTask).from = LocalDate.parse(parts[2]);
            } else if (parts[1].equals("to")) {
                assert editTask instanceof Event;
                ((Event) editTask).to = LocalDate.parse(parts[2]);
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return "Unable to parse command. Use: update <id> /<parameter> <content>";
        } catch (DateTimeParseException e) {
            output = ui.showError("Invalid date format. Use: <yyyy-mm-dd>");
            return output;
        }
        storage.save(tasks.getAllTasks());
        return "Task has been edited to: " + editTask;
    }

    private String handleFind(String input) {
        String output;
        if (input.length() <= 5) {
            output = ui.showError("Could not find. Target is required! Use: find <target phrase>");
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
            output = ui.showError("Invalid task number. Use: delete <id>");
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
            output = ui.showError("Invalid task number. Use: mark <id>");
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
            output = ui.showError("Invalid task number. Use: unmark <id>");
        }
        return output;
    }

    private String handleTodo(String input) {
        String output;
        if (input.length() <= 5) {
            output = ui.showError("Could not add To-do. Description is required! Use: todo <desc>");
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
        if (input.length() <= 9) {
            output = ui.showError("Could not add Deadline. Description is required!"
                    + "Use: deadline <desc> /by <yyyy-mm-dd>");
            return output;
        }
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
        if (input.length() <= 6) {
            output = ui.showError("Could not add Event. Description is required!"
                    + "Use: event <desc> /from <yyyy-mm-dd> /to <yyyy-mm-dd>");
            return output;
        }
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
