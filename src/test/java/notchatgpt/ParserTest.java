package notchatgpt;

import org.junit.jupiter.api.Test;
import static notchatgpt.Parser.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseTodoDescriptionTest1() {
        assertEquals("test", parseTodoDescription("todo test"));
    }
    @Test
    public void parseTodoDescriptionTest2() {
        assertEquals("test 2", parseTodoDescription("todo test 2"));
    }
    @Test
    public void parseDeadlineDetailsTest() {
        String[] listString = parseDeadlineDetails("deadline hw /by 2025-01-01");
        assertEquals("hw", listString[0]);
        assertEquals("2025-01-01", listString[1]);
    }
    @Test
    public void parseDeadlineDetailsTest2() {
        String[] listString = parseDeadlineDetails("deadline hw 2 /by 2025-12-12");
        assertEquals("hw 2", listString[0]);
        assertEquals("2025-12-12", listString[1]);
    }
    @Test
    public void parseEventDetailsTest() {
        String[] listString = parseEventDetails("event exam /from 2025-01-01 /to 2025-01-02");
        assertEquals("exam", listString[0]);
        assertEquals("2025-01-01", listString[1]);
        assertEquals("2025-01-02", listString[2]);
    }
    @Test
    public void parseEventDetailsTest2() {
        String[] listString = parseEventDetails("event exam 2 /from 2025-01-11 /to 2025-01-12");
        assertEquals("exam 2", listString[0]);
        assertEquals("2025-01-11", listString[1]);
        assertEquals("2025-01-12", listString[2]);
    }
}
