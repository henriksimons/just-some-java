import assignments.four.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonTest {

    @Test
    public void testCreatingPersonWithNoId() {
        assertThrows(IllegalArgumentException.class, () -> Person.builder().build());
    }
}
