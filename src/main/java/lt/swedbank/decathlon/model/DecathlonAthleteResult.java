package lt.swedbank.decathlon.model;

import java.math.BigDecimal;

/**
 * @author Sarunas Daujotis
 */
public class DecathlonAthleteResult {
    private BigDecimal result;
    private BigDecimal score;
    private Event event;

    public DecathlonAthleteResult(BigDecimal result, Event event) {
        this.result = result;
        this.event = event;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
