import assignments.four.Person;
import assignments.four.PersonService;
import assignments.four.PersonServiceImpl;
import org.junit.Assert;
import org.junit.Test;

public class PersonServiceTest {

    private final String ID_PERSON_1 = "1";
    private final String ID_PERSON_2 = "2";
    private final String ID_PERSON_2_DUPLICATE = "2";
    private final Person PERSON_1 = Person.builder().id(ID_PERSON_1).build();
    private final Person PERSON_2 = Person.builder().id(ID_PERSON_2).build();
    private final Person PERSON_2_DUPLICATE = Person.builder().id(ID_PERSON_2_DUPLICATE).build();
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
        Assert.assertFalse(personService.savePerson(PERSON_2_DUPLICATE));
    }

    @Test
    public void testGetPerson() {
        Assert.assertEquals(PERSON_1, personService.getPerson(ID_PERSON_1));
    }

    @Test
    public void testGetNonExistingPerson() {
        Assert.assertThrows(RuntimeException.class, () -> personService.getPerson(ID_PERSON_1));
    }
}
