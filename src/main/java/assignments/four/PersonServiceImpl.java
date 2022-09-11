package assignments.four;

import java.util.HashSet;
import java.util.Set;

import static assignments.four.Util.assertIdIsNotNull;
import static assignments.four.Util.assertPersonIsNotNull;

public class PersonServiceImpl implements PersonService {

    private static final Set<Person> PERSONS = new HashSet<>();
    private static PersonServiceImpl instance = null;

    private PersonServiceImpl() {
    }

    public static PersonServiceImpl getInstance() {
        if (instance == null) {
            instance = new PersonServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean savePerson(Person person) {
        assertPersonIsNotNull(person);
        if (PERSONS.contains(person)) {
            return false;
        } else {
            PERSONS.add(person);
            return true;
        }
    }

    @Override
    public Person getPerson(String id) {
        assertIdIsNotNull(id);
        boolean exists = PERSONS.stream().anyMatch(person -> person.getId().equalsIgnoreCase(id));
        if (exists) {
            return PERSONS.stream().filter(person -> person.getId().equalsIgnoreCase(id)).findFirst().orElse(null);
        } else
            throw new RuntimeException("No such person with id " + id + " exists.");
    }
}
