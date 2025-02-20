/**
 * The Task superclass. The subclasses ToDo, Deadline, and Event inherit from this.
 *
 * @param description Description of task
 */
package notchatgpt;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description.
     *
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * "X" if done, " " if not done.
     *
     * @return Status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the status icon of the task.
     * "X" marks the task as done, " " marks it as not done.
     *
     * @param c Character representing the status of the task ('X' for done, ' ' for not done)
     */
    public void setStatusIcon(char c) {
        if (c == ' ') {
            isDone = false;
        } else if (c == 'X') {
            isDone = true;
        }
    }

    /**
     * Returns the string representation of the task.
     *
     * @return Formatted string representing the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
