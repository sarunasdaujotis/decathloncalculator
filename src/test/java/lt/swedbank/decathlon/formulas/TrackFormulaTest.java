package lt.swedbank.decathlon.formulas;

import lt.swedbank.decathlon.model.DecathlonAthleteResult;
import lt.swedbank.decathlon.model.Event;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TrackFormulaTest {
    private TrackFormula trackFormula;

    @Before
    public void setUp() {
        trackFormula = new TrackFormula();
    }

    @After
    public void destroy() {
        trackFormula = null;
    }
    @Test
    public void calculate() {
        DecathlonAthleteResult athleteResult = new DecathlonAthleteResult(BigDecimal.valueOf(305), Event.RUN_1500);
        BigDecimal result = trackFormula.calculate(athleteResult);

        assertEquals(531, result.intValue());
    }
}