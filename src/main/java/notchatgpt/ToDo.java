/**
 * Task with no specific dates
 * @param description Description of task
 */
package notchatgpt;

public class ToDo extends Task {
  public ToDo(String description) {
    super(description);
  }

  @Override
  public String toString() {
    return "[T]" + super.toString();
  }
}
