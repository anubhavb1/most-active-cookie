package com.anubhavb1.mostactivecookie.executor;

/**
 * Enumerates the possible process statuses with associated exit codes and descriptions.
 */
public enum ProcessStatus {
    SUCCESS(0, "The process completed successfully."),
    PROGRAM_FAILED(1, "The process failed due to an error.");

    private final int code;
    private final String description;

    ProcessStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * Gets the exit code associated with the process status.
     *
     * @return The exit code.
     */
    public int getCode() {
        return code;
    }

    /**
     * Gets the human-readable description of the process status.
     *
     * @return The status description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a formatted status message that could be used for logging or user feedback.
     *
     * @return A formatted status message.
     */
    public String getFormattedStatus() {
        return String.format("Status: %s - %s", name(), getDescription());
    }
}

