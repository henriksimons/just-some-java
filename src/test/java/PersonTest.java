import assignments.four.Person;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class PersonTest {

    @Test
    public void testCreatingPersonWithNoId() {
        assertThrows(IllegalArgumentException.class, () -> Person.builder().build());
    }
}
