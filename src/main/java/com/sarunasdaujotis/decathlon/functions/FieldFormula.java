package com.sarunasdaujotis.decathlon.functions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.BiFunction;
import com.sarunasdaujotis.decathlon.model.Event;
import com.sarunasdaujotis.decathlon.model.EventUnit;

public class FieldFormula implements BiFunction<Event, BigDecimal, BigDecimal> {

    @Override
    public BigDecimal apply(final Event event, final BigDecimal scored) {
        double power = Math.pow(convertToMeters(event, scored).subtract(event.getParameterB()).doubleValue(),
                event.getParameterC().doubleValue());
        return event.getParameterA()
                .multiply(new BigDecimal(power))
                .setScale(0, RoundingMode.DOWN);
    }

    private BigDecimal convertToMeters(final Event event, final BigDecimal scored) {
        if (event.getEventUnit() == EventUnit.CENTIMETERS) {
            return scored.multiply(BigDecimal.valueOf(100));
        }
        return scored;
    }
}
