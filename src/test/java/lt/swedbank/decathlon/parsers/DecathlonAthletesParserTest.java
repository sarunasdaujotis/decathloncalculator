package lt.swedbank.decathlon.parsers;

import lt.swedbank.decathlon.model.DecathlonAthlete;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DecathlonAthletesParserTest {
    private List<String> resultList;

    @Before
    public void setUp() {
        resultList = new ArrayList<>();
        String[] resultArray = {"test name", "0.1", "0.2", "0.3", "0.4", "0.5", "0.6", "0.7", "0.8", "0.9", "5.15.10"};
        resultList.addAll(Arrays.asList(resultArray));
    }

    @After
    public void destroy() {
        resultList = null;
    }

    @Test
    public void parse() {
        DecathlonAthlete decathlonAthlete = DecathlonAthletesParser.parse(resultList);

        assertNotNull(decathlonAthlete);
        assertEquals(decathlonAthlete.getFullName(), "test name");
        assertEquals(decathlonAthlete.getResult().size(), 10);
        assertEquals(decathlonAthlete.getResult().get(0).getResult(), new BigDecimal("0.1"));
        assertEquals(decathlonAthlete.getResult().get(9).getResult(), new BigDecimal("315.10"));
    }
}