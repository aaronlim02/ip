package notchatgpt;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for the TaskList class.
     * Initializes the task list with the given list of tasks.
     *
     * @param tasks An ArrayList of Task objects to initialize the task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The task to be added to the task list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified index and returns it.
     *
     * @param index The index of the task to be deleted.
     * @return The task that was removed from the list.
     */
    public Task delete(int index) {
        return tasks.remove(index);
    }

    /**
     * Marks the task at the specified index as completed by setting its status icon to 'X'.
     *
     * @param index The index of the task to be marked as completed.
     */
    public void mark(int index) {
        tasks.get(index).setStatusIcon('X');
    }

    /**
     * Unmarks the task at the specified index, setting its status icon to a space (' ').
     *
     * @param index The index of the task to be unmarked.
     */
    public void unmark(int index) {
        tasks.get(index).setStatusIcon(' ');
    }

    /**
     * Returns the total number of tasks in the task list.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the entire list of tasks.
     *
     * @return An ArrayList of all tasks in the task list.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}
