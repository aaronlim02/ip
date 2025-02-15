/**
 * Deals with storage of the tasklist in the drive.
 * Has the load() and save() methods
 * @param filename directory of file to load/save
 */

package notchatgpt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filename;

    public Storage(String filename) {
        this.filename = filename;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                switch (parts[0]) {
                case "T":
                    tasks.add(new ToDo(parts[1]));
                    break;
                case "D":
                    LocalDate by = LocalDate.parse(parts[2]);
                    tasks.add(new Deadline(parts[1], by));
                    break;
                case "E":
                    LocalDate from = LocalDate.parse(parts[2]);
                    LocalDate to = LocalDate.parse(parts[3]);
                    tasks.add(new Event(parts[1], from, to));
                    break;
                default:
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
        return tasks;
    }

    public void save(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Task task : tasks) {
                String line = "";
                if (task instanceof ToDo) {
                    line = String.join(";", "T", task.description);
                } else if (task instanceof Deadline d) {
                    line = String.join(";", "D", d.description, d.by.toString());
                } else if (task instanceof Event e) {
                    line = String.join(";", "E", e.description, e.from.toString(), e.to.toString());
                }
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Saved to disk.");
        } catch (IOException e) {
            System.out.println("Could not save to disk.");
        }
    }
}
