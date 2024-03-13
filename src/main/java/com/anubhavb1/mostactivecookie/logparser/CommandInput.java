package com.anubhavb1.mostactivecookie.logparser;

import java.time.LocalDate;

/** Class to handle command input, with file name and selected date */
public class CommandInput {
    private String fileName;
    private LocalDate selectedDate;

    // No argument constructor
    public CommandInput() {
    }

    // All arguments constructor for convenience
    public CommandInput(String fileName, LocalDate selectedDate) {
        setFileName(fileName);
        setSelectedDate(selectedDate);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("File name cannot be null or empty");
        }
        this.fileName = fileName;
    }

    public LocalDate getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(LocalDate selectedDate) {
        if (selectedDate == null) {
            throw new IllegalArgumentException("Selected date cannot be null");
        }
        this.selectedDate = selectedDate;
    }
}
