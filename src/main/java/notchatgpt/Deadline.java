/**
 * Task with a specific deadline
 *
 * @param description Description of task
 * @param by Deadline of task
 */
package notchatgpt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
            + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
