package lt.swedbank.decathlon.formulas;

import lt.swedbank.decathlon.model.DecathlonAthleteResult;
import lt.swedbank.decathlon.model.Event;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class FieldFormulaTest {
    private FieldFormula fieldFormula;

    @Before
    public void setUp() {
        fieldFormula = new FieldFormula();
    }

    @After
    public void destroy() {
        fieldFormula = null;
    }

    @Test
    public void calculate() {
        DecathlonAthleteResult athleteResult = new DecathlonAthleteResult(BigDecimal.valueOf(70.70), Event.JAVELIN_THROW);
        BigDecimal result = fieldFormula.calculate(athleteResult);

        assertEquals(900, result.intValue());
    }
}