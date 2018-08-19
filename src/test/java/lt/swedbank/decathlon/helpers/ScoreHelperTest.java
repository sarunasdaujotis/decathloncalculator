package lt.swedbank.decathlon.helpers;

import lt.swedbank.decathlon.model.DecathlonAthlete;
import lt.swedbank.decathlon.model.DecathlonAthleteResult;
import lt.swedbank.decathlon.model.Event;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ScoreHelperTest {
    private DecathlonAthlete decathlonAthlete;

    @Before
    public void setUp() {
        decathlonAthlete = new DecathlonAthlete();
        decathlonAthlete.setFullName("athlete athlete");

        decathlonAthlete.getResult().add(new DecathlonAthleteResult(BigDecimal.valueOf(10.395), Event.RUN_100));
        decathlonAthlete.getResult().add(new DecathlonAthleteResult(BigDecimal.valueOf(7.76), Event.LONG_JUMP));
        decathlonAthlete.getResult().add(new DecathlonAthleteResult(BigDecimal.valueOf(18.4), Event.SHOT_PUT));
        decathlonAthlete.getResult().add(new DecathlonAthleteResult(BigDecimal.valueOf(2.20), Event.HIGH_JUMP));
        decathlonAthlete.getResult().add(new DecathlonAthleteResult(BigDecimal.valueOf(46.17), Event.RUN_400));
        decathlonAthlete.getResult().add(new DecathlonAthleteResult(BigDecimal.valueOf(13.8), Event.RUN_110));
        decathlonAthlete.getResult().add(new DecathlonAthleteResult(BigDecimal.valueOf(56.17), Event.DISCUS_THROW));
        decathlonAthlete.getResult().add(new DecathlonAthleteResult(BigDecimal.valueOf(5.28), Event.POLE_VAULT));
        decathlonAthlete.getResult().add(new DecathlonAthleteResult(BigDecimal.valueOf(77.19), Event.JAVELIN_THROW));
        decathlonAthlete.getResult().add(new DecathlonAthleteResult(BigDecimal.valueOf(233.79), Event.RUN_1500));
    }

    @After
    public void destroy() {
        decathlonAthlete = null;
    }

    @Test
    public void calculate() {
        DecathlonAthlete calculated = ScoreHelper.calculate(decathlonAthlete);
        assertEquals(9990, calculated.getScore().intValue());
    }
}