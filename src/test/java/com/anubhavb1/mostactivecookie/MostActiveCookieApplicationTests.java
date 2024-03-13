package com.anubhavb1.mostactivecookie;

import com.anubhavb1.mostactivecookie.exception.LogParsingException;
import com.anubhavb1.mostactivecookie.cookiefilter.CookieFilterImpl;
import com.anubhavb1.mostactivecookie.logparser.CommandInput;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.time.LocalDate.parse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
class MostActiveCookieTests {

	private CookieFilterImpl cookieAnalysisTool;
	private final ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	@BeforeEach
	void setup() {
		cookieAnalysisTool = new CookieFilterImpl();
		System.setOut(new PrintStream(testOutput));
	}

	@AfterEach
	void reset() {
		System.setOut(originalOut);
	}

	@Test
	void validLogFileAndDate_ShouldListTopCookies() throws LogParsingException {
		CommandInput input = new CommandInput("src/logs/cookie_log.csv", parse("2018-12-09"));
		cookieAnalysisTool.filterMostActiveCookies(input);
		String result = testOutput.toString();
		assertThat(result).contains("AtY0laUfhglK3lC7");
		assertThat(result).doesNotContain("SAZuXPGUrfbcn5UA");
	}

	@Test
	void multipleTopCookiesForADate_ShouldDisplayAllTopCookies() throws LogParsingException {
		CommandInput input = new CommandInput("src/logs/cookie_log.csv", parse("2018-12-06"));
		cookieAnalysisTool.filterMostActiveCookies(input);
		String result = testOutput.toString();
		assertThat(result).contains("8xYHIASHaBa79xzf");
		assertThat(result).contains("1dSLJdsaDJLDsdSd");
	}

	@Test
	void invalidFilePath_ShouldTriggerException() {
		CommandInput input = new CommandInput("invalid/path/dummy.csv", parse("2018-12-09"));
		assertThatThrownBy(() -> cookieAnalysisTool.filterMostActiveCookies(input))
				.isInstanceOf(LogParsingException.class)
				.hasMessageContaining("Error parsing log file");
	}

	@Test
	void nonExistentDate_ShouldResultInNoOutput() throws LogParsingException {
		CommandInput input = new CommandInput("src/logs/cookie_log.csv", parse("2021-12-09"));
		cookieAnalysisTool.filterMostActiveCookies(input);
		assertThat(testOutput.toString()).isEmpty();
	}
}
