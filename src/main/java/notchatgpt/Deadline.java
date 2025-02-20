/**
 * Task with a specific deadline
 *
 * @param description Description of task
 * @param by Deadline of task
 */
package notchatgpt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a Deadline task with the given description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task,
     * including its type, description, and due date formatted as "MMM d yyyy".
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
            + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
