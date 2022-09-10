package assignments.two;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Logger;

public class DateFormatter {

    private static final int DATE_STRING_ACCEPTED_LENGTH = 8;
    private static final Logger LOGGER = Logger.getLogger(DateFormatter.class.getName());
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.BASIC_ISO_DATE;

    public DateFormatter() {
    }

    public static String formatDate(String date) {

        String inputNoBackslash = verifyPreConditions(date);

        String day = inputNoBackslash.substring(0, 2);
        String month = inputNoBackslash.substring(2, 4);
        String year = inputNoBackslash.substring(4, 8);

        LocalDate formattedDate;
        formattedDate = parseDate(day, month, year);
        return formattedDate.toString();
    }

    private static String verifyPreConditions(String input) {

        verifyInputIsNotNull(input);

        verifyInputIsNotBlank(input);

        String inputNoBackslashes = input.replaceAll("[/]", "");

        verifyInputIsCorrectLength(inputNoBackslashes);

        verifyInputIsDigits(inputNoBackslashes);

        return inputNoBackslashes;
    }

    private static LocalDate parseDate(String day, String month, String year) {
        LocalDate date;
        try {
            date = LocalDate.parse(year + month + day, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            LOGGER.warning(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return date;
    }

    private static void verifyInputIsNotNull(String input) {
        if (input == null) {
            String message = "Can not format parameter date to LocalDate. Parameter is null!";
            LOGGER.warning(message);
            throw new NullPointerException(message);
        }
    }

    private static void verifyInputIsNotBlank(String input) {
        if (input.isBlank()) {
            String message = "Can not format parameter date to LocalDate. Parameter is empty!";
            LOGGER.warning(message);
            throw new RuntimeException(message);
        }
    }

    private static void verifyInputIsCorrectLength(String inputNoBackslash) {
        if (inputNoBackslash.length() != DATE_STRING_ACCEPTED_LENGTH) {
            String message = "Can not format date to LocalDate. Parameter must follow pattern ^([0-9]+(/[0-9]+)+)$ / (dd/MM/YYYY)";
            LOGGER.warning(message);
            throw new RuntimeException(message);
        }
    }

    private static void verifyInputIsDigits(String dateStringNoBackSlash) {
        for (int i = 0; i < dateStringNoBackSlash.length(); i++) {
            char character = dateStringNoBackSlash.charAt(i);
            boolean isDigit = Character.isDigit(character);
            if (!isDigit) {
                throw new RuntimeException("Can not format date to LocalDate. Character at index: " + i + " is not a digit!");
            }
        }
    }
}
