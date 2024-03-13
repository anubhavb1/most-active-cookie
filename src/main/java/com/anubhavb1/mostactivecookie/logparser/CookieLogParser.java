package com.anubhavb1.mostactivecookie.logparser;

import static java.nio.file.Files.newBufferedReader;
import static java.nio.file.Paths.get;
import static java.time.LocalDate.parse;
import com.anubhavb1.mostactivecookie.exception.LogParsingException;
import com.anubhavb1.mostactivecookie.logparser.CookieLogRecord;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CookieLogParser {
    private static final Logger log = LoggerFactory.getLogger(CookieLogParser.class);

    public static List<CookieLogRecord> parseLog(String filePath) throws LogParsingException {
        try {
            return new CsvToBeanBuilder<CookieLogRecord>(newBufferedReader(get(filePath)))
                    .withType(CookieLogRecord.class)
                    .withFilter(line -> !line[0].startsWith("cookie")) // Skip header if present
                    .build()
                    .parse();
        } catch (Exception e) {
            log.error("Failed to parse log file: {}", filePath, e);
            throw new LogParsingException("Error parsing log file", e);
        }
    }

    public static CommandInput parseArguments(String[] args) throws LogParsingException {
        Options options = defineCommandLineOptions();
        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);
            String fileName = cmd.getOptionValue("file");
            String dateString = cmd.getOptionValue("date");

            CommandInput commandInput = new CommandInput();
            commandInput.setFileName(fileName);
            commandInput.setSelectedDate(parse(dateString));

            return commandInput;
        } catch (ParseException e) {
            log.error("Error parsing command line arguments", e);
            showCommandLineHelp(options);
            throw new LogParsingException("Error parsing command line arguments", e);
        }
    }

    private static Options defineCommandLineOptions() {
        Options options = new Options();

        Option fileOption = new Option("f", "file", true, "File path of the cookie log");
        fileOption.setRequired(true);

        Option dateOption = new Option("d", "date", true, "Date for which to find the most active cookie (format: YYYY-MM-DD)");
        dateOption.setRequired(true);

        options.addOption(fileOption);
        options.addOption(dateOption);

        return options;
    }

    private static void showCommandLineHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("mostactivecookie", options);
    }
}