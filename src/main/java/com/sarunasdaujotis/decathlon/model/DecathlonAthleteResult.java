package com.sarunasdaujotis.decathlon.model;

import java.math.BigDecimal;
import java.util.Objects;

public final class DecathlonAthleteResult {
    private final BigDecimal result;
    private final BigDecimal score;
    private final Event event;

    private DecathlonAthleteResult(BigDecimal result, BigDecimal score, Event event) {
        this.result = result;
        this.score = score;
        this.event = event;
    }

    public BigDecimal getResult() {
        return result;
    }

    public BigDecimal getScore() {
        return score;
    }

    public Event getEvent() {
        return event;
    }

    public static DecathlonAthleteResult create(final BigDecimal result, final Event event) {
        final BigDecimal score = event.calculateEventScore(result);
        return new DecathlonAthleteResult(result, score, event);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DecathlonAthleteResult that = (DecathlonAthleteResult) o;
        return Objects.equals(result, that.result) &&
                Objects.equals(score, that.score) &&
                event == that.event;
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, score, event);
    }

    @Override
    public String toString() {
        return "DecathlonAthleteResult{" +
                "result=" + result +
                ", score=" + score +
                ", event=" + event +
                '}';
    }
}
