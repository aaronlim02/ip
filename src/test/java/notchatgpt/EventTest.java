package notchatgpt;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest() {
        Event test = new Event("sample event",
            LocalDate.parse("2025-01-01"),
            LocalDate.parse("2025-01-02"));
        assertEquals("[E][ ] sample event (from: Jan 1 2025 to: Jan 2 2025)", test.toString());
    }
}
