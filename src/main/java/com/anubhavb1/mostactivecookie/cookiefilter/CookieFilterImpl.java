package com.anubhavb1.mostactivecookie.cookiefilter;

import com.anubhavb1.mostactivecookie.exception.LogParsingException;
import com.anubhavb1.mostactivecookie.logparser.CommandInput;
import com.anubhavb1.mostactivecookie.logparser.CookieLogRecord;
import com.anubhavb1.mostactivecookie.logparser.CookieLogParser;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** Implements cookie filtering logic to identify and display most active cookies for a given date. */
public class CookieFilterImpl implements CookieFilter {

    @Override
    public void filterMostActiveCookies(CommandInput commandInput) throws LogParsingException {
        List<CookieLogRecord> cookieEntries = CookieLogParser.parseLog(commandInput.getFileName());
        LocalDate selectedDate = commandInput.getSelectedDate();

        Map<String, Long> cookieCounts = cookieEntries.stream()
                .filter(entry -> selectedDate.equals(entry.getTimestamp().toLocalDate()))
                .collect(Collectors.groupingBy(CookieLogRecord::getCookie, Collectors.counting()));

        long maxCount = cookieCounts.values().stream().mapToLong(count -> count).max().orElse(0);

        cookieCounts.entrySet().stream()
                .filter(entry -> entry.getValue() == maxCount)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);
    }
}