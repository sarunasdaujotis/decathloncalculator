package lt.swedbank.decathlon.parsers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CSVParserTest {
    private CSVParser csvParser;

    @Before
    public void setUp() {
        csvParser = new CSVParser("src/test/resources/input.csv");
    }

    @After
    public void destroy() {
        csvParser = null;
    }

    @Test
    public void parse() {
        List<List<String>> list = csvParser.parse();
        assertFalse(list.isEmpty());
        assertFalse(list.get(0).isEmpty());
        assertEquals(11, list.get(0).size());
    }
}