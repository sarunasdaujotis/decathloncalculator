package com.sarunasdaujotis.decathlon.functions;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.sarunasdaujotis.decathlon.model.DecathlonAthlete;
import com.sarunasdaujotis.decathlon.model.DecathlonAthleteResult;
import com.sarunasdaujotis.decathlon.model.Event;

public class DecathlonAthletePopulator implements Function<List<String>, DecathlonAthlete> {

	private static final int ATHLETE_RESULT_SIZE = 11;
	private static final int ATHLETE_NAME_POSITION = 0;

	@Override
	public DecathlonAthlete apply(final List<String> decathlonAthleteRawData) {
		if (decathlonAthleteRawData.size() != ATHLETE_RESULT_SIZE) {
			throw new IllegalArgumentException("Input line must have " + ATHLETE_RESULT_SIZE + " elements");
		}

		final String decathlonAthleteName = decathlonAthleteRawData.get(ATHLETE_NAME_POSITION);

		final List<DecathlonAthleteResult> decathlonAthleteResults = Event.EVENTS
				.stream()
				.map(event -> extractAndCalculateScore(decathlonAthleteRawData, event))
				.collect(Collectors.toList());

		return DecathlonAthlete.create(decathlonAthleteName, decathlonAthleteResults);
	}

	DecathlonAthleteResult extractAndCalculateScore(List<String> list, Event event) {
		String value = list.get(event.getPosition()).trim();
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
			return DecathlonAthleteResult.create(timeInSeconds, event);
		}
		return DecathlonAthleteResult.create(new BigDecimal(value), event);
	}
}