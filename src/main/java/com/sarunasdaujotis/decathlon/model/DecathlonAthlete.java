package com.sarunasdaujotis.decathlon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@XmlType(propOrder={"fullName", "score", "place", "result"})
public final class DecathlonAthlete implements Comparator<DecathlonAthlete> {
    private final String fullName;
    private final BigDecimal score;
    private final List<DecathlonAthleteResult> result = new ArrayList<>(9);

    private DecathlonAthlete(final String fullName, BigDecimal score, final List<DecathlonAthleteResult> result) {
        this.fullName = fullName;
        this.score = score;
        this.result.addAll(result);
    }

    public String getFullName() {
        return fullName;
    }

    public BigDecimal getScore() {
        return score;
    }

    @XmlElement(name = "eventResult")
    public List<DecathlonAthleteResult> getResult() {
        return Collections.unmodifiableList(result);
    }

    public static DecathlonAthlete create(final String fullName, final List<DecathlonAthleteResult> result) {
        return new DecathlonAthlete(fullName, calculateScore(result), result);
    }

    private static BigDecimal calculateScore(final List<DecathlonAthleteResult> result) {
        return result.stream().map(DecathlonAthleteResult::getScore).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DecathlonAthlete that = (DecathlonAthlete) o;
        return Objects.equals(fullName, that.fullName) &&
                Objects.equals(score, that.score) &&
                Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, score, result);
    }

    @Override
    public int compare(final DecathlonAthlete o1, final DecathlonAthlete o2) {
        return o1.getScore().compareTo(o2.getScore());
    }
}
