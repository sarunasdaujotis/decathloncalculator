package com.sarunasdaujotis.decathlon.functions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.BiFunction;
import com.sarunasdaujotis.decathlon.model.Event;

public class TrackFormula implements BiFunction<Event, BigDecimal, BigDecimal> {

    @Override
    public BigDecimal apply(final Event event, final BigDecimal scored) {
        double power = Math.pow(event.getParameterB().subtract(scored).doubleValue(), event.getParameterC().doubleValue());
        return event.getParameterA()
                .multiply(new BigDecimal(power))
                .setScale(0, RoundingMode.DOWN);
    }
}
