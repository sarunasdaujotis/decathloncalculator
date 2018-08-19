package lt.swedbank.decathlon;

import lt.swedbank.decathlon.helpers.PlaceHelper;
import lt.swedbank.decathlon.helpers.ScoreHelper;
import lt.swedbank.decathlon.helpers.XmlHelper;
import lt.swedbank.decathlon.model.Competition;
import lt.swedbank.decathlon.model.DecathlonAthlete;
import lt.swedbank.decathlon.parsers.CSVParser;
import lt.swedbank.decathlon.parsers.DecathlonAthletesParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Main {
    private static final Logger LOGGER = Logger.getLogger("Main");

    public static void main(String... args) throws JAXBException, IOException {
        if (args.length != 2) {
            LOGGER.log(Level.SEVERE, "Please enter input file name and output file name");
            System.exit(0);
        }
        String inputFileName = args[0];
        String outputFileName = args[1];
        LOGGER.log(Level.INFO, "Input file " + inputFileName + ", output file " + outputFileName);

        List<DecathlonAthlete> decathlonAthletes = new CSVParser(inputFileName)
                .parse()
                .stream()
                .map(DecathlonAthletesParser::parse)
                .map(ScoreHelper::calculate)
                .collect(Collectors.toList());

        Comparator<DecathlonAthlete> decathlonAthleteComparator = Comparator
                .comparing(DecathlonAthlete::getScore)
                .reversed();
        Collections.sort(decathlonAthletes, decathlonAthleteComparator);

        PlaceHelper.calculate(decathlonAthletes);

        LOGGER.log(Level.INFO, "Total athletes scored " + decathlonAthletes.size());

        XmlHelper.generateDocument(new Competition(decathlonAthletes), outputFileName);
    }
}
