package assignments.four;

public interface PersonService {

    boolean savePerson(Person person);

    Person getPerson(String id);

    boolean deletePerson(String id);
}
