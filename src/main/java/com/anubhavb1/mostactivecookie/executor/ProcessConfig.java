package com.anubhavb1.mostactivecookie.executor;

import com.anubhavb1.mostactivecookie.cookiefilter.CookieFilter;
import com.anubhavb1.mostactivecookie.cookiefilter.CookieFilterImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up application beans related to process execution.
 * It defines beans necessary for running the application, including the command line runner,
 * cookie filter, and process executor.
 */
@Configuration
public class ProcessConfig {

    // Constructor injection for ApplicationContext is optional since it's not used directly,
    // but keeping it here if future configurations need it.
    private final ApplicationContext applicationContext;

    public ProcessConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Defines the CommandLineRunner bean responsible for starting the application process.
     * @param processExecutor The process executor bean used to execute the main logic.
     * @return A CommandLineRunner bean.
     */
    @Bean
    public CommandLineRunner commandLineRunner(ProcessExecutor processExecutor) {
        return new ProcessRunner(applicationContext, processExecutor);
    }

    /**
     * Defines the CookieFilter bean used for filtering cookies based on provided criteria.
     * @return A CookieFilter bean.
     */
    @Bean
    public CookieFilter cookieFilter() {
        return new CookieFilterImpl();
    }

    /**
     * Defines the ProcessExecutor bean that handles the execution logic for processing cookies.
     * @param cookieFilter The cookie filter bean used within the execution process.
     * @return A ProcessExecutor bean.
     */
    @Bean
    public ProcessExecutor processExecutor(CookieFilter cookieFilter) {
        return new ProcessExecutorImpl(cookieFilter);
    }
}

