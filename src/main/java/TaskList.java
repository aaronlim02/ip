import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int index) {
        return tasks.remove(index);
    }

    public void mark(int index) {
        tasks.get(index).setStatusIcon('X');
    }

    public void unmark(int index) {
        tasks.get(index).setStatusIcon(' ');
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}