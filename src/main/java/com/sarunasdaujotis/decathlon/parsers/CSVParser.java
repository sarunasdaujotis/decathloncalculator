package com.sarunasdaujotis.decathlon.parsers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVParser {
    private static final String COMMA_SEPARATOR = ";";

    private final String inputFileName;

    public CSVParser(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public List<List<String>> parse() {
        try (Stream<String> lines = Files.lines(Paths.get(inputFileName))) {
            return lines.map(s -> Arrays.asList(s.split(COMMA_SEPARATOR)))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Unable to read file " + inputFileName);
        }
    }
}
