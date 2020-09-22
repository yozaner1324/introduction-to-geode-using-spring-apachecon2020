package org.example.apachecon2020.client;

import java.util.LinkedList;
import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableClusterConfiguration;
import org.springframework.data.gemfire.config.annotation.EnableContinuousQueries;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.listener.annotation.ContinuousQuery;

import org.apache.geode.cache.query.CqEvent;

@SpringBootApplication
@EnableEntityDefinedRegions
@EnableClusterConfiguration(useHttp = true, requireHttps = false)
@ClientCacheApplication(subscriptionEnabled = true)
public class ApacheConDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApacheConDemoApplication.class, args);
	}

	@Bean
	ApplicationRunner runner(AttendeesRepository repository) {
		return args -> {
			List<Attendee> attendees = new LinkedList<>();
			attendees.add(new Attendee(1L, "Jack", "Black"));
			attendees.add(new Attendee(2L, "Emily", "Black"));
			attendees.add(new Attendee(3L, "Janet", "Lee"));
			attendees.add(new Attendee(4L, "Robert", "Smith"));
			attendees.add(new Attendee(5L, "Amanda", "Howard"));
			attendees.add(new Attendee(6L, "Greg", "Logan"));

			for(Attendee attendee : attendees) {
				System.out.printf("Saving %s%n", attendee);
				repository.save(attendee);
				Thread.sleep(2000);
			}
		};
	}

	@ContinuousQuery(name = "AttendeeCQ", query = "SELECT * FROM /Attendees")
	public void handleEvent(CqEvent event) {
		System.out.printf("Found %s in Attendees region%n", event.getNewValue());
	}
}
