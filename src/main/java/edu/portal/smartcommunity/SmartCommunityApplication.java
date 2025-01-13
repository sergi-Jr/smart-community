package edu.portal.smartcommunity;

import net.datafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Locale;

@SpringBootApplication
public class SmartCommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartCommunityApplication.class, args);
	}

	@Bean
	public static Faker getFaker() {
		return new Faker();
	}
}
