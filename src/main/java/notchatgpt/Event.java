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

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
            + from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: "
            + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
