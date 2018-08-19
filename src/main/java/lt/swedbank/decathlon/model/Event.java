package lt.swedbank.decathlon.model;

import java.math.BigDecimal;

public enum Event {
    RUN_100("25.4347", "18", "1.81", EventType.TRACK, EventUnit.SECONDS),
    LONG_JUMP("0.14354", "220", "1.4", EventType.FIELD, EventUnit.CENTIMETERS),
    SHOT_PUT("51.39", "1.5", "1.05", EventType.FIELD, EventUnit.METERS),
    HIGH_JUMP("0.8465", "75", "1.42", EventType.FIELD, EventUnit.CENTIMETERS),
    RUN_400("1.53775", "82", "1.81", EventType.TRACK, EventUnit.SECONDS),
    RUN_110("5.74352", "28.5", "1.92", EventType.TRACK, EventUnit.SECONDS),
    DISCUS_THROW("12.91", "4", "1.1", EventType.FIELD, EventUnit.METERS),
    POLE_VAULT("0.2797", "100", "1.35", EventType.FIELD, EventUnit.CENTIMETERS),
    JAVELIN_THROW("10.14", "7", "1.08", EventType.FIELD, EventUnit.METERS),
    RUN_1500("0.03768", "480", "1.85", EventType.TRACK, EventUnit.MINUTES);

    private BigDecimal parameterA;
    private BigDecimal parameterB;
    private BigDecimal ParameterC;
    private EventType eventType;
    private EventUnit eventUnit;

    Event(String parameterA, String parameterB, String parameterC, EventType eventType, EventUnit eventUnit) {
        this.parameterA = new BigDecimal(parameterA);
        this.parameterB = new BigDecimal(parameterB);
        this.ParameterC = new BigDecimal(parameterC);
        this.eventType = eventType;
        this.eventUnit = eventUnit;
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

    public EventType getEventType() {
        return eventType;
    }

    public EventUnit getEventUnit() {
        return eventUnit;
    }
}
