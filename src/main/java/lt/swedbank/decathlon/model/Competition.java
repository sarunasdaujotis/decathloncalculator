package lt.swedbank.decathlon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Competition {
    private List<DecathlonAthlete> decathlonAthletes;

    public Competition() {
    }

    public Competition(List<DecathlonAthlete> decathlonAthletes) {
        this.decathlonAthletes = decathlonAthletes;
    }

    @XmlElement(name = "athlete")
    public List<DecathlonAthlete> getDecathlonAthletes() {
        return decathlonAthletes;
    }
}


