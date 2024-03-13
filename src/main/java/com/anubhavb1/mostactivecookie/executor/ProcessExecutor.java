package com.anubhavb1.mostactivecookie.executor;

/**
 * Represents an executor capable of processing command-line arguments to perform a specific task,
 * such as filtering cookie log data. Implementations of this interface should define how the arguments
 * are processed and what actions are taken based on those arguments.
 */
public interface ProcessExecutor {

    /**
     * Executes a process based on the given command-line arguments.
     * This method is responsible for interpreting the arguments and performing
     * the required action, such as filtering cookies from a log file.
     *
     * @param args Array of command-line arguments passed to the application.
     * @return An integer representing the status code of the process execution.
     *         A standard convention is followed where 0 indicates successful execution,
     *         and any non-zero value indicates an error or specific exit condition.
     *         Implementations can define their own error codes for more detailed feedback.
     */
    int executeProcess(String[] args);
}

