package lt.swedbank.decathlon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@XmlType(propOrder={"fullName", "score", "place", "result"})
public class DecathlonAthlete {
    private String fullName;
    private BigDecimal score;
    private String place;
    private final List<DecathlonAthleteResult> result = new ArrayList<>(9);

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @XmlElement(name = "eventResult")
    public List<DecathlonAthleteResult> getResult() {
        return result;
    }
}
