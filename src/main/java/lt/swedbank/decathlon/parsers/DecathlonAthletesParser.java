package lt.swedbank.decathlon.parsers;

import lt.swedbank.decathlon.model.DecathlonAthlete;
import lt.swedbank.decathlon.model.DecathlonAthleteResult;
import lt.swedbank.decathlon.model.Event;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DecathlonAthletesParser {
    private static final int ATHLETE_RESULT_SIZE = 11;
    private static final int ATHLETE_NAME_POSITION = 0;

    public static DecathlonAthlete parse(List<String> list) {
        if (list.size() != ATHLETE_RESULT_SIZE) {
            throw new RuntimeException("Input line must have " + ATHLETE_RESULT_SIZE + "elements");
        }
        DecathlonAthlete decathlonAthlete = new DecathlonAthlete();
        decathlonAthlete.setFullName(list.get(ATHLETE_NAME_POSITION));

        List<DecathlonAthleteResult> decathlonAthleteResults = Arrays.stream(Event.values())
                .map(event -> extractEventResult(list, event))
                .collect(Collectors.toList());

        decathlonAthlete.getResult().addAll(decathlonAthleteResults);

        return decathlonAthlete;
    }

    private static DecathlonAthleteResult extractEventResult(List<String> list, Event event) {
        String value = list.get(event.ordinal() + 1).trim();
        if (event == Event.RUN_1500) {
            String[] timeParts = value.replaceFirst("\\.", ":").split(":");
            BigDecimal timeInSeconds;
            if (timeParts.length == 1) {
                timeInSeconds = new BigDecimal(timeParts[0]);
            } else {
                timeInSeconds = new BigDecimal(timeParts[0])
                        .multiply(BigDecimal.valueOf(60))
                        .add(new BigDecimal(timeParts[1]));
            }
            return new DecathlonAthleteResult(timeInSeconds, event);
        }
        return new DecathlonAthleteResult(new BigDecimal(value), event);
    }
}
