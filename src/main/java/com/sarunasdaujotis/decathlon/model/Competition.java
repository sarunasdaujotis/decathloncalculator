package com.sarunasdaujotis.decathlon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@XmlRootElement
public final class Competition {

	private final Map<String, List<DecathlonAthlete>> decathlonAthletesByScore;

	private Competition(final Map<String, List<DecathlonAthlete>> decathlonAthletesByScore) {
		this.decathlonAthletesByScore = decathlonAthletesByScore;
	}

	public static Competition create(final List<DecathlonAthlete> decathlonAthletes) {
		Map<BigDecimal, List<DecathlonAthlete>> groupedByScore = decathlonAthletes.stream()
				.collect(Collectors.groupingBy(DecathlonAthlete::getScore));

		Map<BigDecimal, List<DecathlonAthlete>> sortedByValue = groupedByScore.entrySet().stream()
				.sorted(Map.Entry.<BigDecimal, List<DecathlonAthlete>>comparingByKey().reversed())
				.collect(Collectors.toMap(Map.Entry::getKey,
						Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

		Map<String, List<DecathlonAthlete>> decathlonAthletesByScore = new LinkedHashMap<>();
		int place = 1;
		for (Map.Entry<BigDecimal, List<DecathlonAthlete>> entry : sortedByValue.entrySet()) {
			if (entry.getValue().size() > 1) {
				int sharedPlace = place + 1;
				for (DecathlonAthlete decathlonAthlete : entry.getValue()) {
					final List<DecathlonAthlete> decathlonAthleteList =
							decathlonAthletesByScore.getOrDefault(place + "-" + sharedPlace, new ArrayList<>());
					decathlonAthleteList.add(decathlonAthlete);
                    decathlonAthletesByScore.putIfAbsent(place + "-" + sharedPlace, decathlonAthleteList);
				}
				place = sharedPlace + 1;
			} else {
                final String placeKey = Integer.toString(place);
                final List<DecathlonAthlete> decathlonAthleteList =
						decathlonAthletesByScore.getOrDefault(placeKey, new ArrayList<>());
				entry.getValue().stream()
						.findFirst()
						.ifPresent(decathlonAthlete -> {
                            decathlonAthleteList.add(decathlonAthlete);
                            decathlonAthletesByScore.putIfAbsent(placeKey, decathlonAthleteList);
                        });
				place++;
			}
		}

		return new Competition(decathlonAthletesByScore);
	}

	@XmlElement(name = "athlete")
	public Map<String, List<DecathlonAthlete>> getDecathlonAthletes() {
		return decathlonAthletesByScore;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Competition that = (Competition) o;
		return Objects.equals(decathlonAthletesByScore, that.decathlonAthletesByScore);
	}

	@Override
	public int hashCode() {
		return Objects.hash(decathlonAthletesByScore);
	}
}


