import assignments.four.Person;
import assignments.four.PersonService;
import assignments.four.PersonServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonServiceTest {

    private final String PERSON_1_ID = "1";
    private final String PERSON_2_ID = "2";

    private final Person PERSON_1 = Person.builder().id(PERSON_1_ID).build();
    private final Person PERSON_2 = Person.builder().id(PERSON_2_ID).build();

    private final PersonService personService = PersonServiceImpl.getInstance();

    @Test
    public void testCreatingAccountService() {
        PersonService personService1 = PersonServiceImpl.getInstance();
        PersonService personService2 = PersonServiceImpl.getInstance();
        assertEquals(personService1, personService2);
    }

    @Test
    public void testSavePerson() {
        assertTrue(personService.savePerson(PERSON_1));
    }

    @Test
    public void testSaveNull() {
        assertThrows(RuntimeException.class, () -> personService.savePerson(null));
    }

    @Test
    public void testSaveDuplicatePerson() {
        assertTrue(personService.savePerson(PERSON_2));
        assertFalse(personService.savePerson(PERSON_2));
    }

    @Test
    public void testGetPerson() {
        assertEquals(PERSON_1, personService.getPerson(PERSON_1_ID));
    }

    @Test
    public void testGetNonExistingPerson() {
        assertThrows(RuntimeException.class, () -> personService.getPerson(PERSON_1_ID));
    }

    @Test
    public void testDeletePerson() {
        assertTrue(personService.savePerson(PERSON_1));
        assertTrue(personService.deletePerson(PERSON_1_ID));
    }
}
