package com.anubhavb1.mostactivecookie.exception;

/** Custom exception for errors encountered while parsing cookie log data */
public class LogParsingException extends Exception {

    // Constructor with just the error message
    public LogParsingException(String message) {
        super(message);
    }

    // Constructor with both a message and a cause
    public LogParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor with just the cause
    public LogParsingException(Throwable cause) {
        super(cause);
    }
}