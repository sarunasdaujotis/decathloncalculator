package com.sarunasdaujotis.decathlon;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import com.sarunasdaujotis.decathlon.functions.DecathlonAthletePopulator;
import com.sarunasdaujotis.decathlon.model.Competition;
import com.sarunasdaujotis.decathlon.model.DecathlonAthlete;
import com.sarunasdaujotis.decathlon.parsers.CSVParser;

public class Main {
	private static final Logger LOGGER = Logger.getLogger("Main");

	public static void main(String... args) throws IOException {
		if (args.length != 2) {
			LOGGER.log(Level.SEVERE, "Please enter input file name and output file name");
			System.exit(0);
		}
		String inputFileName = args[0];
		String outputFileName = args[1];
		LOGGER.log(Level.INFO, "Input file " + inputFileName + ", output file " + outputFileName);

		DecathlonAthletePopulator decathlonAthletePopulator = new DecathlonAthletePopulator();

		List<DecathlonAthlete> decathlonAthletes = new CSVParser(inputFileName)
				.parse()
				.stream()
				.map(decathlonAthletePopulator)
				.collect(Collectors.toList());

		LOGGER.log(Level.INFO, "Total athletes scored " + decathlonAthletes.size());

		final Competition competition = Competition.create(decathlonAthletes);

		Files.writeString(Path.of(outputFileName), competition.toString());
	}
}
