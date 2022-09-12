package assignments.four;

import java.util.HashMap;
import java.util.logging.Logger;

import static assignments.four.Utils.assertIdIsNotNull;
import static assignments.four.Utils.assertPersonId;

public class PersonServiceImpl implements PersonService {

    private static final Logger LOGGER = Logger.getLogger(PersonServiceImpl.class.getName());
    private static final HashMap<String, Person> PERSONS_BY_ID = new HashMap<>();
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
        assertPersonId(person);
        String key = person.getId();
        if (PERSONS_BY_ID.containsKey(key)) {
            return false;
        } else {
            PERSONS_BY_ID.put(key, person);
            return true;
        }
    }

    @Override
    public Person getPerson(String id) {
        assertIdIsNotNull(id);
        boolean exists = PERSONS_BY_ID.containsKey(id);
        if (exists) {
            return PERSONS_BY_ID.get(id);
        } else
            throw new RuntimeException("No person with id " + id + " exists.");
    }

    @Override
    public boolean deletePerson(String id) {
        assertIdIsNotNull(id);
        boolean exists = PERSONS_BY_ID.containsKey(id);
        if (exists) {
            PERSONS_BY_ID.remove(id); //What to do with accounts?
            return true;
        } else
            LOGGER.warning("No person with id " + id + " exists to delete.");
        return false;
    }
}
