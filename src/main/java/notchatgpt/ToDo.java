/**
 * Task with no specific dates.
 * This class represents a "To Do" task that does not have any specific start or due dates.
 *
 * @param description Description of the task.
 */
package notchatgpt;

public class ToDo extends Task {

    /**
     * Constructor for the ToDo class.
     * Initializes the "To Do" task with the given description.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the "To Do" task.
     * The string format is "[T]" followed by the string representation of the task from the superclass.
     *
     * @return A string representing the "To Do" task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}