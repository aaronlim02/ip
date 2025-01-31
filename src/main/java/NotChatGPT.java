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

    public void run() {
        ui.showWelcome("Not ChatGPT");
        boolean isExit = false;

        while (!isExit) {
            String input = ui.readCommand();

            if (Parser.isByeCommand(input)) {
                isExit = true;
                ui.showGoodbye();
            } else if (Parser.isListCommand(input)) {
                ui.showTaskList(tasks);
            } else if (Parser.isDeleteCommand(input)) {
                handleDelete(input);
            } else if (Parser.isMarkCommand(input)) {
                handleMark(input);
            } else if (Parser.isUnmarkCommand(input)) {
                handleUnmark(input);
            } else if (Parser.isTodoCommand(input)) {
                handleTodo(input);
            } else if (Parser.isDeadlineCommand(input)) {
                handleDeadline(input);
            } else if (Parser.isEventCommand(input)) {
                handleEvent(input);
            } else {
                ui.showError("I don't understand.");
            }
        }
    }

    private void handleDelete(String input) {
        try {
            int index = Parser.parseDeleteIndex(input);
            Task deletedTask = tasks.delete(index);
            ui.showMessage("Noted! I've removed:\n  " + deletedTask);
            storage.save(tasks.getAllTasks());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.showError("Invalid task number.");
        }
    }

    private void handleMark(String input) {
        try {
            int index = Parser.parseMarkUnmarkIndex(input);
            tasks.mark(index);
            ui.showMessage("Nice! I've marked this task as done:\n  " + tasks.get(index));
            storage.save(tasks.getAllTasks());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.showError("Invalid task number.");
        }
    }

    private void handleUnmark(String input) {
        try {
            int index = Parser.parseMarkUnmarkIndex(input);
            tasks.unmark(index);
            ui.showMessage("OK, I've marked this task as not done yet:\n  " + tasks.get(index));
            storage.save(tasks.getAllTasks());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.showError("Invalid task number.");
        }
    }

    private void handleTodo(String input) {
        if (input.length() <= 5) {
            ui.showError("Could not add To-do. Description is required!");
            return;
        }
        String description = Parser.parseTodoDescription(input);
        tasks.add(new ToDo(description));
        ui.showMessage("To-do added: " + description);
        storage.save(tasks.getAllTasks());
    }

    private void handleDeadline(String input) {
        try {
            String[] details = Parser.parseDeadlineDetails(input);
            LocalDate by = LocalDate.parse(details[1]);
            tasks.add(new Deadline(details[0], by));
            ui.showMessage("Deadline added: " + details[0]);
            storage.save(tasks.getAllTasks());
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            ui.showError("Invalid deadline format. Use: deadline <desc> /by <yyyy-mm-dd>");
        }
    }

    private void handleEvent(String input) {
        try {
            String[] details = Parser.parseEventDetails(input);
            LocalDate from = LocalDate.parse(details[1]);
            LocalDate to = LocalDate.parse(details[2]);
            tasks.add(new Event(details[0], from, to));
            ui.showMessage("Event added: " + details[0]);
            storage.save(tasks.getAllTasks());
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            ui.showError("Invalid event format. Use: event <desc> /from <yyyy-mm-dd> /to <yyyy-mm-dd>");
        }
    }

    public static void main(String[] args) {
        new NotChatGPT().run();
    }
}