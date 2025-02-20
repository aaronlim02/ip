package notchatgpt;

/**
 * Parses user input and determines the type of command given.
 */
@SuppressWarnings("checkstyle:Regexp")
public class Parser {
    /**
     * Checks if the input is a bye command.
     *
     * @param input User input.
     * @return True if input is "bye", false otherwise.
     */
    public static boolean isByeCommand(String input) {
        return input.equals("bye");
    }

    /**
     * Checks if the input is a list command.
     *
     * @param input User input.
     * @return True if input is "list", false otherwise.
     */
    public static boolean isListCommand(String input) {
        return input.equals("list");
    }

    /**
     * Checks if the input is a delete command.
     *
     * @param input User input.
     * @return True if input starts with "delete", false otherwise.
     */
    public static boolean isDeleteCommand(String input) {
        return input.startsWith("delete");
    }

    /**
     * Parses the index for a delete command.
     *
     * @param input User input.
     * @return Index of the task to delete.
     * @throws NumberFormatException If the index is not a valid number.
     */
    public static int parseDeleteIndex(String input) throws NumberFormatException {
        String[] parts = input.split(" ");
        return Integer.parseInt(parts[1]) - 1;
    }

    /**
     * Checks if the input is a mark command.
     *
     * @param input User input.
     * @return True if input starts with "mark", false otherwise.
     */
    public static boolean isMarkCommand(String input) {
        return input.startsWith("mark");
    }

    /**
     * Checks if the input is an unmark command.
     *
     * @param input User input.
     * @return True if input starts with "unmark", false otherwise.
     */
    public static boolean isUnmarkCommand(String input) {
        return input.startsWith("unmark");
    }

    /**
     * Parses the index for a mark/unmark command.
     *
     * @param input User input.
     * @return Index of the task to mark/unmark.
     * @throws NumberFormatException If the index is not a valid number.
     */
    public static int parseMarkUnmarkIndex(String input) throws NumberFormatException {
        String[] parts = input.split(" ");
        return Integer.parseInt(parts[1]) - 1;
    }

    /**
     * Checks if the input is a todo command.
     *
     * @param input User input.
     * @return True if input starts with "todo", false otherwise.
     */
    public static boolean isTodoCommand(String input) {
        return input.startsWith("todo");
    }

    /**
     * Parses the description of a todo task.
     *
     * @param input User input.
     * @return Description of the todo task.
     */
    public static String parseTodoDescription(String input) {
        return input.substring(5).trim();
    }

    /**
     * Checks if the input is a deadline command.
     *
     * @param input User input.
     * @return True if input starts with "deadline", false otherwise.
     */
    public static boolean isDeadlineCommand(String input) {
        return input.startsWith("deadline");
    }

    /**
     * Parses the description and due date of a deadline task.
     *
     * @param input User input.
     * @return Array containing the description and due date.
     */
    public static String[] parseDeadlineDetails(String input) {
        String[] parts = input.substring(9).split("/by");
        String description = parts[0].trim();
        String by = parts[1].trim();
        return new String[]{description, by};
    }

    /**
     * Checks if the input is an event command.
     *
     * @param input User input.
     * @return True if input starts with "event", false otherwise.
     */
    public static boolean isEventCommand(String input) {
        return input.startsWith("event");
    }

    /**
     * Parses the description, start date, and end date of an event task.
     *
     * @param input User input.
     * @return Array containing the description, start date, and end date.
     */
    public static String[] parseEventDetails(String input) {
        String[] parts = input.substring(6).split("/from|/to");
        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        return new String[]{description, from, to};
    }

    /**
     * Checks if the input is a find command.
     *
     * @param input User input.
     * @return True if input starts with "find", false otherwise.
     */
    public static boolean isFindCommand(String input) {
        return input.startsWith("find");
    }

    /**
     * Parses the keyword for a find command.
     *
     * @param input User input.
     * @return Keyword to search for.
     */
    public static String parseFindCommand(String input) {
        return input.substring(5).trim();
    }

    /**
     * Checks if the input is an update command.
     *
     * @param input User input.
     * @return True if input starts with "update", false otherwise.
     */
    public static boolean isUpdateCommand(String input) {
        return input.startsWith("update");
    }

    /**
     * Parses the update command details.
     *
     * @param rawInput User input.
     * @return Array containing task ID, update type, and new value.
     */
    public static String[] parseUpdateDetails(String rawInput) {
        String input = rawInput.substring(7).trim();
        String id = input.substring(0, input.indexOf(' '));
        String parameter = input.substring(input.indexOf(' ') + 1);
        if (parameter.startsWith("/desc")) {
            return new String[]{id, "desc", parameter.substring(6)};
        } else if (parameter.startsWith("/from")) {
            return new String[]{id, "from", parameter.substring(6)};
        } else if (parameter.startsWith("/to")) {
            return new String[]{id, "to", parameter.substring(4)};
        } else if (parameter.startsWith("/by")) {
            return new String[]{id, "by", parameter.substring(4)};
        }
        return new String[]{};
    }

    /**
     * Checks if the input is a help command.
     *
     * @param input User input.
     * @return True if input is "help", false otherwise.
     */
    public static boolean isHelpCommand(String input) {
        return input.equals("help");
    }
}
