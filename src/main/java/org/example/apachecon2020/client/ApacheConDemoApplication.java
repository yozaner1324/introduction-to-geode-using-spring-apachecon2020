package org.example.apachecon2020.client;

import java.util.LinkedList;
import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.config.annotation.EnableClusterConfiguration;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;

@SpringBootApplication
@EnableEntityDefinedRegions
@EnableClusterConfiguration
public class ApacheConDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApacheConDemoApplication.class, args);
	}

	@Bean
	ApplicationRunner runner(AttendeesRepository repository,
							 AttendeeFunctionExecutions functionExecutions) {
		return args -> {
			List<Attendee> attendees = new LinkedList<>();
			attendees.add(new Attendee(1L, "Jack", "Black"));
			attendees.add(new Attendee(2L, "Emily", "Black"));
			attendees.add(new Attendee(3L, "Janet", "Lee"));
			attendees.add(new Attendee(4L, "Robert", "Smith"));
			attendees.add(new Attendee(5L, "Amanda", "Howard"));
			attendees.add(new Attendee(6L, "Greg", "Logan"));

			repository.saveAll(attendees);

			System.out.printf("Average length of first name: %f",
					functionExecutions.calculateAverageFirstNameLength());
		};
	}
}
