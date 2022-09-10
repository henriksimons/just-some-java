package assignments.four;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class Util {

    private static final Gson parser = new Gson();

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

    public static String readInputStream(InputStream inputStream){
        return new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    public static Gson getParser() {
        return parser;
    }
}
