package lt.swedbank.decathlon.helpers;

import lt.swedbank.decathlon.formulas.FieldFormula;
import lt.swedbank.decathlon.formulas.TrackFormula;
import lt.swedbank.decathlon.model.DecathlonAthlete;
import lt.swedbank.decathlon.model.DecathlonAthleteResult;

import java.math.BigDecimal;
import java.util.List;

public class ScoreHelper {
    private static FieldFormula fieldFormula = new FieldFormula();
    private static TrackFormula trackFormula = new TrackFormula();

    public static DecathlonAthlete calculate(DecathlonAthlete decathlonAthlete) {
        List<DecathlonAthleteResult> decathlonAthleteResults = decathlonAthlete.getResult();
        BigDecimal finalScore = BigDecimal.ZERO;
        for (DecathlonAthleteResult decathlonAthleteResult : decathlonAthleteResults) {
            BigDecimal calculated = BigDecimal.ZERO;
            switch (decathlonAthleteResult.getEvent().getEventType()) {
                case TRACK:
                    calculated = trackFormula.calculate(decathlonAthleteResult);
                    break;
                case FIELD:
                    calculated = fieldFormula.calculate(decathlonAthleteResult);
            }
            finalScore = finalScore.add(calculated);
            decathlonAthleteResult.setScore(calculated);
        }
        decathlonAthlete.setScore(finalScore);
        return decathlonAthlete;
    }
}
