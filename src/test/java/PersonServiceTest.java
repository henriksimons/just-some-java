import assignments.four.Person;
import assignments.four.PersonService;
import assignments.four.PersonServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class PersonServiceTest {

    private final String PERSON_1_ID = "1";
    private final String PERSON_2_ID = "2";

    private final Person PERSON_1 = Person.builder().id(PERSON_1_ID).build();
    private final Person PERSON_2 = Person.builder().id(PERSON_2_ID).build();

    private final PersonService personService = PersonServiceImpl.getInstance();

    @Test
    public void testSavePerson() {
        Assert.assertTrue(personService.savePerson(PERSON_1));
    }

    @Test
    public void testSaveNull() {
        Assert.assertThrows(RuntimeException.class, () -> personService.savePerson(null));
    }

    @Test
    public void testSaveDuplicatePerson() {
        Assert.assertTrue(personService.savePerson(PERSON_2));
        Assert.assertFalse(personService.savePerson(PERSON_2));
    }

    @Test
    public void testGetPerson() {
        Assert.assertEquals(PERSON_1, personService.getPerson(PERSON_1_ID));
    }

    @Test
    public void testGetNonExistingPerson() {
        Assert.assertThrows(RuntimeException.class, () -> personService.getPerson(PERSON_1_ID));
    }

    @Test
    public void testDeletePerson() {
        Assert.assertTrue(personService.savePerson(PERSON_1));
        Assert.assertTrue(personService.deletePerson(PERSON_1_ID));
    }
}
