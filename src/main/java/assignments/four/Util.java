package assignments.four;

public class Util {

    public static void assertPersonIsNotNull(Person person) {
        if (person == null) {
            throw new RuntimeException("Can not continue - parameter person is null!");
        }
    }

    public static void assertIdIsNotNull(String id) {
        if (id == null) {
            throw new RuntimeException("Can not continue - parameter id is null!");
        }
    }
}
