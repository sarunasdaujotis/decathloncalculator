package com.sarunasdaujotis.decathlon.functions;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;
import com.sarunasdaujotis.decathlon.model.Competition;
import com.sarunasdaujotis.decathlon.model.DecathlonAthlete;
import com.sarunasdaujotis.decathlon.model.DecathlonAthleteResult;
import com.sarunasdaujotis.decathlon.model.Event;
import com.sarunasdaujotis.decathlon.parsers.CSVParser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DecathlonAthleteResultPopulatorTest {
    private CSVParser csvParser;
    private DecathlonAthletePopulator decathlonAthletePopulator;

    @Before
    public void setUp() {
        csvParser = new CSVParser("src/test/resources/input.csv");
        decathlonAthletePopulator = new DecathlonAthletePopulator();
    }

    @Test
    public void parse() {
        final List<DecathlonAthlete> collect = csvParser.parse()
                .stream()
                .map(decathlonAthletePopulator)
                .collect(Collectors.toList());

        final DecathlonAthlete decathlonAthlete = collect.get(0);

        assertNotNull(decathlonAthlete);
        assertEquals("Siim Susi", decathlonAthlete.getFullName());
        assertEquals(10, decathlonAthlete.getResult().size());

        final DecathlonAthleteResult firstResult = decathlonAthlete.getResult().get(0);
        assertEquals(new BigDecimal("12.61"), firstResult.getResult());
        assertEquals(new BigDecimal("536"), firstResult.getScore());
        assertEquals(Event.RUN_100, firstResult.getEvent());

        final DecathlonAthleteResult lastResult = decathlonAthlete.getResult().get(9);
        assertEquals(new BigDecimal("325.72"), lastResult.getResult());
        assertEquals(new BigDecimal("421"), lastResult.getScore());
        assertEquals(Event.RUN_1500, lastResult.getEvent());

        Competition.create(collect);
    }
}