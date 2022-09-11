package assignments.four;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class Util {

    private static final Gson parser = new Gson();

    public static String formatPersonId(String id) {
        assertIdIsNotNull(id);

        String idNoSeparators = id.trim().replaceAll("-\\s+", "");

        char[] formattedIdCharArray = idNoSeparators.toCharArray();

        for (int i = 0; i < formattedIdCharArray.length; i++) {
            if (!Character.isDigit(formattedIdCharArray[i])) {
                throw new RuntimeException("Person id can only consist of digits.");
            }
        }
        return String.valueOf(formattedIdCharArray);
    }

    public static void assertPersonIsNotNull(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Can not continue - parameter person is null!");
        }
    }

    public static void assertPersonId(Person person) {
        if (person != null && person.getId() == null) {
            throw new RuntimeException("Can not continue - person does not have an id!");
        }
    }

    public static void assertIdIsNotNull(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Can not continue - parameter id is null!");
        }
    }

    public static String readInputStream(InputStream inputStream) {
        return new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    public static Gson getParser() {
        return parser;
    }
}
