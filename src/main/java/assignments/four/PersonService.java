package assignments.four;

import java.util.HashSet;
import java.util.Set;

import static assignments.four.Util.assertIdIsNotNull;
import static assignments.four.Util.assertPersonIsNotNull;

public class PersonService {

    private static final Set<Person> PERSONS = new HashSet<>();
    private static PersonService instance = null;

    private PersonService() {
    }

    public static PersonService getInstance() {
        if (instance == null) {
            instance = new PersonService();
        }
        return instance;
    }

    public boolean addPerson(Person person) {
        assertPersonIsNotNull(person);
        if (PERSONS.contains(person)) {
            return false;
        } else {
            PERSONS.add(person);
            return true;
        }
    }

    public Person getPerson(String id) {
        assertIdIsNotNull(id);
        boolean exists = PERSONS.stream().anyMatch(person -> person.getId().equalsIgnoreCase(id));
        if (exists) {
            return PERSONS.stream().filter(person -> person.getId().equalsIgnoreCase(id)).findFirst().orElse(null);
        } else
            throw new RuntimeException("No such person with id " + id + "exists.");
    }
}
