package com.sarunasdaujotis.decathlon.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;
import com.sarunasdaujotis.decathlon.functions.FieldFormula;
import com.sarunasdaujotis.decathlon.functions.TrackFormula;

public enum Event {
    RUN_100(1, "25.4347", "18", "1.81", EventUnit.SECONDS, new TrackFormula()),
    LONG_JUMP(2, "0.14354", "220", "1.4", EventUnit.CENTIMETERS, new FieldFormula()),
    SHOT_PUT(3, "51.39", "1.5", "1.05", EventUnit.METERS, new FieldFormula()),
    HIGH_JUMP(4,"0.8465", "75", "1.42", EventUnit.CENTIMETERS, new FieldFormula()),
    RUN_400(5, "1.53775", "82", "1.81", EventUnit.SECONDS, new TrackFormula()),
    RUN_110(6, "5.74352", "28.5", "1.92", EventUnit.SECONDS, new TrackFormula()),
    DISCUS_THROW(7, "12.91", "4", "1.1", EventUnit.METERS, new FieldFormula()),
    POLE_VAULT(8, "0.2797", "100", "1.35", EventUnit.CENTIMETERS, new FieldFormula()),
    JAVELIN_THROW(9, "10.14", "7", "1.08", EventUnit.METERS, new FieldFormula()),
    RUN_1500(10, "0.03768", "480", "1.85", EventUnit.MINUTES, new TrackFormula());

    private final int position;
    private final BigDecimal parameterA;
    private final BigDecimal parameterB;
    private final BigDecimal ParameterC;
    private final EventUnit eventUnit;
    private final BiFunction<Event, BigDecimal, BigDecimal> formula;

    public static final List<Event> EVENTS = List.of(values());

    Event(final int position,
            final String parameterA,
            final String parameterB,
            final String parameterC,
            final EventUnit eventUnit,
            final BiFunction<Event, BigDecimal, BigDecimal> formula) {
        this.position = position;
        this.parameterA = new BigDecimal(parameterA);
        this.parameterB = new BigDecimal(parameterB);
        this.ParameterC = new BigDecimal(parameterC);
        this.eventUnit = eventUnit;
        this.formula = formula;
    }

    public int getPosition() {
        return position;
    }

    public BigDecimal getParameterA() {
        return parameterA;
    }

    public BigDecimal getParameterB() {
        return parameterB;
    }

    public BigDecimal getParameterC() {
        return ParameterC;
    }

    public EventUnit getEventUnit() {
        return eventUnit;
    }

    public BigDecimal calculateEventScore(BigDecimal result) {
        return formula.apply(this, result);
    }
}