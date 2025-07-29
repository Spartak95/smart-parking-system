package com.xcoder.smart.parking.system;

import com.xcoder.smart.parking.system.allocation.Slot;
import com.xcoder.smart.parking.system.allocation.SlotRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner initSlots(SlotRepository slotRepository) {
		return args -> {
			if (slotRepository.count() == 0) {
				slotRepository.save(new Slot(null, "A1", true, null));
				slotRepository.save(new Slot(null, "A2", true, null));
				slotRepository.save(new Slot(null, "B1", true, null));
			}
		};
	}
}
