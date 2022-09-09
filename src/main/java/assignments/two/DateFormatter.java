package assignments.two;

import java.time.LocalDate;
import java.util.logging.Logger;

public class DateFormatter {

    private static final int DATE_STRING_ACCEPTED_LENGTH = 8;
    private static final Logger LOGGER = Logger.getLogger(DateFormatter.class.getName());

    public DateFormatter() {
    }

    public static String formatDate(String date) {

        if (date == null || date.isBlank()) {
            LOGGER.warning("Can not format parameter date to LocalDate. Parameter is null or empty!");
            throw new RuntimeException("Can not format parameter date to LocalDate. Parameter is null or empty!");
        }
        String dateStringNoBackSlash = date.replaceAll("[/]", "");

        if (dateStringNoBackSlash.length() != DATE_STRING_ACCEPTED_LENGTH) {
            LOGGER.warning("Can not format date to LocalDate. Parameter must follow pattern ^([0-9]+(/[0-9]+)+)$");
            throw new RuntimeException("Can not format date to LocalDate. Parameter must follow pattern ^([0-9]+(/[0-9]+)+)$");
        }

        for (int i = 0; i < dateStringNoBackSlash.length(); i++) {
            Character character = dateStringNoBackSlash.charAt(i);
            boolean isDigit = Character.isDigit(character);
            if (!isDigit) {
                throw new RuntimeException("Can not format date to LocalDate. Character at index: " + i + " is not a digit!");
            }
        }

        //30129999
        String day = dateStringNoBackSlash.substring(0, 2);
        isWithinLogicalBoundaries(day, DATE.DAY);
        String month = dateStringNoBackSlash.substring(2, 4);
        isWithinLogicalBoundaries(month, DATE.MONTH);
        String year = dateStringNoBackSlash.substring(4, 8);

        return LocalDate.of(toInteger(year), toInteger(month), toInteger(day)).toString();
    }

    private static boolean isWithinLogicalBoundaries(String dateEntity, DATE bounds) {
        int dayAsInt = Integer.parseInt(dateEntity);
        boolean valid = dayAsInt >= bounds.getFirst() && dayAsInt <= bounds.getLast();
        if (!valid) {
            LOGGER.warning("Invalid date :" + dateEntity + ". Must be between " + bounds.getFirst() + " and " + bounds.getLast() + ".");
            throw new RuntimeException("Invalid date: " + dateEntity + ". Must be between " + bounds.getFirst() + " and " + bounds.getLast() + ".");
        }
        return true;
    }


    private static Integer toInteger(String str) {
        return Integer.parseInt(str);
    }

}
