/**
 * Task with a starting and ending date
 *
 * @param description Description of task
 * @param from starting date of task
 * @param to ending date of task
 */
package notchatgpt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs an Event task with a description, start date, and end date.
     *
     * @param description Description of the event task.
     * @param from        Starting date of the event.
     * @param to          Ending date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task, including its type,
     * description, start date, and end date.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
            + from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: "
            + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
