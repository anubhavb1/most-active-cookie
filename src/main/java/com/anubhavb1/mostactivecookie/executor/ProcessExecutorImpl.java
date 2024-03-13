package com.anubhavb1.mostactivecookie.executor;

import com.anubhavb1.mostactivecookie.exception.LogParsingException;
import com.anubhavb1.mostactivecookie.cookiefilter.CookieFilter;
import com.anubhavb1.mostactivecookie.logparser.CommandInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.anubhavb1.mostactivecookie.logparser.CookieLogParser.parseArguments;
import static com.anubhavb1.mostactivecookie.executor.ProcessStatus.SUCCESS;
import static com.anubhavb1.mostactivecookie.executor.ProcessStatus.PROGRAM_FAILED;

/**
 * Concrete implementation of the ProcessExecutor interface, responsible for executing
 * the cookie filtering process based on command line inputs.
 */
public class ProcessExecutorImpl implements ProcessExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessExecutorImpl.class);
    private final CookieFilter cookieFilter;

    public ProcessExecutorImpl(CookieFilter cookieFilter) {
        this.cookieFilter = cookieFilter;
    }

    /**
     * Executes the main process logic, including parsing command line inputs and filtering cookies.
     * Logs errors and returns appropriate status codes based on the execution outcome.
     *
     * @param args the command line arguments passed to the application
     * @return the exit code representing the outcome of the process execution
     */
    @Override
    public int executeProcess(String[] args) {
        try {
            CommandInput commandInput = parseArguments(args);
            cookieFilter.filterMostActiveCookies(commandInput);
            LOGGER.info("Process completed successfully.");
            return SUCCESS.getCode();
        } catch (LogParsingException e) {
            LOGGER.error("Error parsing log file: {}", e.getMessage(), e);
        } catch (RuntimeException e) {
            LOGGER.error("Unexpected error occurred during process execution: {}", e.getMessage(), e);
        }
        return PROGRAM_FAILED.getCode();
    }
}

