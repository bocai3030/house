package org.house;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan
@EnableAutoConfiguration
@EnableScheduling
public class Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	@Autowired
	private Environment env;

	/**
	 * Spring profiles can be configured with a program arguments<br>
	 * --spring.profiles.active=your-active-profile
	 */
	@PostConstruct
	public void initApplication() throws IOException {
		if (this.env.getActiveProfiles().length == 0) {
			Application.LOGGER.warn("No Spring profile configured, running with default configuration");
		} else {
			Application.LOGGER.info("Running with Spring profile(s) : {}",
					Arrays.toString(this.env.getActiveProfiles()));
		}
	}

	/**
	 * Main method, used to run the application.
	 */
	public static void main(final String[] args) throws UnknownHostException {
		final SpringApplication app = new SpringApplication(Application.class);
		app.setShowBanner(false);
		final SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);

		Application.addDefaultProfile(app, source);

		app.run(args).getEnvironment();

	}

	/**
	 * Set a default profile if it has not been set
	 */
	private static void addDefaultProfile(final SpringApplication app, final SimpleCommandLinePropertySource source) {
		if (!source.containsProperty("spring.profiles.active")) {
			app.setAdditionalProfiles("dev");
		} else {
			final String profile = source.getProperty("spring.profiles.active");
			if (profile != null) {
				Application.LOGGER.info("Running with Spring profile(s) : {}", profile);
			}
		}
	}
}