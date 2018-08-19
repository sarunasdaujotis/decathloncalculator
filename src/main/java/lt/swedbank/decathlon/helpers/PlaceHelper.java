package lt.swedbank.decathlon.helpers;

import lt.swedbank.decathlon.model.DecathlonAthlete;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PlaceHelper {
    public static void calculate(List<DecathlonAthlete> decathlonAthletes) {
        Map<BigDecimal, List<DecathlonAthlete>> groupedByScore = decathlonAthletes.stream()
                .collect(Collectors.groupingBy(DecathlonAthlete::getScore));

        Map<BigDecimal, List<DecathlonAthlete>> sortedByValue = groupedByScore.entrySet().stream()
                .sorted(Map.Entry.<BigDecimal, List<DecathlonAthlete>>comparingByKey()
                        .reversed())
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        int place = 1;
        for (Map.Entry<BigDecimal, List<DecathlonAthlete>> entry : sortedByValue.entrySet()) {
            if (entry.getValue().size() > 1) {
                int sharedPlace = place + 1;
                for (DecathlonAthlete decathlonAthlete : entry.getValue()) {
                    decathlonAthlete.setPlace(place + "-" + sharedPlace);
                }
                place = sharedPlace + 1;
            } else {
                Optional<DecathlonAthlete> decathlonAthleteOptional = entry.getValue().stream().findFirst();
                if (decathlonAthleteOptional.isPresent()) {
                    decathlonAthleteOptional.get().setPlace(Integer.toString(place));
                }
                place++;
            }
        }
    }
}
