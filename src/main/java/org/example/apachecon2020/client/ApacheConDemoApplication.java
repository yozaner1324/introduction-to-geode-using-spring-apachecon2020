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
			Attendee jack = new Attendee(1L, "Jack", "Black");
			Attendee emily = new Attendee(2L, "Emily", "Black");
			Attendee janet = new Attendee(3L, "Janet", "Lee");

			repository.save(jack);
			repository.save(emily);
			repository.save(janet);

			System.out.println("Finding all attendees with last name \"Black\": ");
			repository.findAllByLastName("Black").forEach(System.out::println);

			System.out.println("Finding all attendees whose first name starts with \"J\": ");
			repository.findAllByFirstNameStartsWith("J").forEach(System.out::println);

			System.out.println("Finding all attendees whose id is NOT \"1\" using a query: ");
			repository.findAllByIdNotUsingQuery(1L).forEach(System.out::println);
		};
	}
}
