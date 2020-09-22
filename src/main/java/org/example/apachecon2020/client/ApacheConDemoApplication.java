package org.example.apachecon2020.client;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.config.annotation.EnableClusterConfiguration;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;

@SpringBootApplication
@EnableEntityDefinedRegions
@EnableClusterConfiguration(useHttp = true, requireHttps = false)
public class ApacheConDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApacheConDemoApplication.class, args);
	}

	@Bean
	ApplicationRunner runner(AttendeesRepository repository) {
		return args -> {
			Attendee john = new Attendee(1L, "Jack", "Black");
			System.out.printf("Saving attendee %s with id 1L%n", john);
			repository.save(john);
			System.out.printf("Retrieved attendee %s for id 1L%n", repository.findById(1L).get());
		};
	}
}
