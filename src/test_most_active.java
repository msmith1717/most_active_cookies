import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class test_most_active {
    @Test
    void singleMostActive() throws Exception {
        String filename = "cookie_log.csv";
        String day = "2018-12-09";
        Log log = new Log(filename, day);
        List<String> most_active = log.most_active();
        Assertions.assertEquals(1, most_active.size());
        Assertions.assertEquals("AtY0laUfhglK3lC7", most_active.get(0));
    }

    @Test
    void multipleMostActive () throws Exception {
        String filename = "cookie_log.csv";
        String day = "2018-12-08";
        Log log = new Log(filename, day);
        List<String> most_active = log.most_active();
        String[] expected = new String[]{"SAZuXPGUrfbcn5UA", "4sMM2LxV07bPJzwf", "fbcn5UAVanZf6UtG"};
        Assertions.assertEquals(expected.length, most_active.size());
        for (String cookie : expected) {
            Assertions.assertTrue(most_active.contains(cookie));
        }
    }
}
