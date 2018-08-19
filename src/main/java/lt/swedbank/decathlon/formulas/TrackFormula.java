package lt.swedbank.decathlon.formulas;

import lt.swedbank.decathlon.model.DecathlonAthleteResult;
import lt.swedbank.decathlon.model.Event;

import java.math.BigDecimal;

public class TrackFormula {
    public BigDecimal calculate(DecathlonAthleteResult athleteResult) {
        Event event = athleteResult.getEvent();
        BigDecimal scored = athleteResult.getResult();

        double power = Math.pow(event.getParameterB().subtract(scored).doubleValue(), event.getParameterC().doubleValue());
        return event.getParameterA()
                .multiply(new BigDecimal(power))
                .setScale(0, BigDecimal.ROUND_DOWN);
    }
}
