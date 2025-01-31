package notchatgpt;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toStringTest() {
        ToDo test = new ToDo("sample todo");
        assertEquals("[T][ ] sample todo", test.toString());
    }
}
