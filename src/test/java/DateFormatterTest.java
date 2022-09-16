import assignments.two.DateFormatter;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateFormatterTest {


    @Test
    public void testFormatNullDate() {
        assertThrows(RuntimeException.class, () -> DateFormatter.formatDate(null));
    }

    @Test
    public void testFormatBlankDate() {
        assertThrows(RuntimeException.class, () -> DateFormatter.formatDate(""));
    }

    @Test
    public void testFormatTooLongDate() {
        assertThrows(RuntimeException.class, () -> DateFormatter.formatDate("01/01/20229"));
    }

    @Test
    public void testFormatDateWrongDividers() {
        assertThrows(RuntimeException.class, () -> DateFormatter.formatDate("01#01@20229"));
    }

    @Test
    public void testFormatDate() {
        String validDate = "31/12/2022";
        String formattedDate = DateFormatter.formatDate(validDate);
        assertEquals(formattedDate, LocalDate.of(2022, 12, 31).toString());
    }

    @Test
    public void testFormatDateLeapYear() {
        String validDate = "29/02/2004";
        String formattedDate = DateFormatter.formatDate(validDate);
        assertEquals(formattedDate, LocalDate.of(2004, 2, 29).toString());
    }

    @Test
    public void testFormatDateNegativeYear() {
        String invalidDate = "29/02/-0";
        assertThrows(RuntimeException.class, () -> DateFormatter.formatDate(invalidDate));
    }

    @Test
    public void testFormatDateWithWrongDay() {
        String invalidDate = "32/12/2022";
        assertThrows(RuntimeException.class, () -> DateFormatter.formatDate(invalidDate));
    }

    @Test
    public void testFormatDateWithWrongDay2() {
        String invalidDate = "00/12/2022";
        assertThrows(RuntimeException.class, () -> DateFormatter.formatDate(invalidDate));
    }

    @Test
    public void testFormatDateWithWrongMonth() {
        String invalidDate = "31/13/2022";
        assertThrows(RuntimeException.class, () -> DateFormatter.formatDate(invalidDate));
    }

    @Test
    public void testFormatDateWithWrongMonth2() {
        String invalidDate = "01/00/2022";
        assertThrows(RuntimeException.class, () -> DateFormatter.formatDate(invalidDate));
    }

    @Test
    public void testFormatDateWithWrongInput() {
        String invalidDate = "12#12#2000";
        assertThrows(RuntimeException.class, () -> DateFormatter.formatDate(invalidDate));
    }

    @Test
    public void testFormatDateWithWrongInput2() {
        String invalidDate = "plain wrong";
        assertThrows(RuntimeException.class, () -> DateFormatter.formatDate(invalidDate));
    }
}
