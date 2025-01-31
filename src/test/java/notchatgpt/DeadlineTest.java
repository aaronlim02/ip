package notchatgpt;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest() {
        Deadline test = new Deadline("sample deadline", LocalDate.parse("2025-01-01"));
        assertEquals("[D][ ] sample deadline (by: Jan 1 2025)", test.toString());
    }
}
