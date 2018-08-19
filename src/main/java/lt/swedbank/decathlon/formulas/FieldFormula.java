package lt.swedbank.decathlon.formulas;

import lt.swedbank.decathlon.model.DecathlonAthleteResult;
import lt.swedbank.decathlon.model.Event;
import lt.swedbank.decathlon.model.EventUnit;

import java.math.BigDecimal;

public class FieldFormula {

    public BigDecimal calculate(DecathlonAthleteResult athleteResult) {
        Event event = athleteResult.getEvent();
        BigDecimal scored = athleteResult.getResult();
        if (event.getEventUnit() == EventUnit.CENTIMETERS) {
            scored = scored.multiply(BigDecimal.valueOf(100));
        }

        double power = Math.pow(scored.subtract(event.getParameterB()).doubleValue(), event.getParameterC().doubleValue());
        return event.getParameterA()
                .multiply(new BigDecimal(power))
                .setScale(0, BigDecimal.ROUND_DOWN);
    }
}
