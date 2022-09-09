import assignments.two.DateFormatter;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class DateFormatterTest {

    @Test
    public void testFormatDate(){
        String validDate = "31/12/2022";
        String formattedDate = DateFormatter.formatDate(validDate);
        Assert.assertEquals(formattedDate, LocalDate.of(2022, 12, 31).toString());
    }

    @Test
    public void testFormatDateWithWrongDay(){
        String invalidDate = "32/12/2022";
        Assert.assertThrows(RuntimeException.class, () -> DateFormatter.formatDate(invalidDate));
    }

    @Test
    public void testFormatDateWithWrongDay2(){
        String invalidDate = "00/12/2022";
        Assert.assertThrows(RuntimeException.class, () -> DateFormatter.formatDate(invalidDate));
    }

    @Test
    public void testFormatDateWithWrongMonth(){
        String invalidDate = "31/13/2022";
        Assert.assertThrows(RuntimeException.class, () -> DateFormatter.formatDate(invalidDate));
    }

    @Test
    public void testFormatDateWithWrongMonth2(){
        String invalidDate = "01/00/2022";
        Assert.assertThrows(RuntimeException.class, () -> DateFormatter.formatDate(invalidDate));
    }

    @Test
    public void testFormatDateWithWrongInput(){
        String invalidDate = "12#12#2000";
        Assert.assertThrows(RuntimeException.class, () -> DateFormatter.formatDate(invalidDate));
    }

    @Test
    public void testFormatDateWithWrongInput2(){
        String invalidDate = "plain wrong";
        Assert.assertThrows(RuntimeException.class, () -> DateFormatter.formatDate(invalidDate));
    }

}
