package notchatgpt;

public class Parser {
    public static boolean isByeCommand(String input) {
        return input.equals("bye");
    }

    public static boolean isListCommand(String input) {
        return input.equals("list");
    }

    public static boolean isDeleteCommand(String input) {
        return input.startsWith("delete");
    }

    public static int parseDeleteIndex(String input) throws NumberFormatException {
        String[] parts = input.split(" ");
        return Integer.parseInt(parts[1]) - 1;
    }

    public static boolean isMarkCommand(String input) {
        return input.startsWith("mark");
    }

    public static boolean isUnmarkCommand(String input) {
        return input.startsWith("unmark");
    }

    public static int parseMarkUnmarkIndex(String input) throws NumberFormatException {
        String[] parts = input.split(" ");
        return Integer.parseInt(parts[1]) - 1;
    }

    public static boolean isTodoCommand(String input) {
        return input.startsWith("todo");
    }

    public static String parseTodoDescription(String input) {
        return input.substring(5).trim();
    }

    // Deadline parsing
    public static boolean isDeadlineCommand(String input) {
        return input.startsWith("deadline");
    }

    public static String[] parseDeadlineDetails(String input) {
        String[] parts = input.substring(9).split("/by");
        String description = parts[0].trim();
        String by = parts[1].trim();
        return new String[]{description, by};
    }

    public static boolean isEventCommand(String input) {
        return input.startsWith("event");
    }

    public static String[] parseEventDetails(String input) {
        String[] parts = input.substring(6).split("/from|/to");
        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        return new String[]{description, from, to};
    }

    public static boolean isFindCommand(String input) {
        return input.startsWith("find");
    }

    public static String parseFindCommand(String input) {
        return input.substring(5).trim();
    }

    public static boolean isUpdateCommand(String input) {
        return input.startsWith("update");
    }

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
}
