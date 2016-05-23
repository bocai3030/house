package org.house;

import org.house.service.EarthDataController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@ComponentScan
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(EarthDataController.class);
	}

	public static void main(String[] args) throws Exception {
		final SpringApplication app = new SpringApplication(Application.class);
        app.setShowBanner(false);        
        app.run(args).getEnvironment();
	}

}