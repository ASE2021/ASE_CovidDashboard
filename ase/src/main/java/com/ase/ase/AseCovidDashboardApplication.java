package com.ase.ase;

import com.ase.ase.dao.*;
import com.ase.ase.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AseCovidDashboardApplication {
	private static final Logger log = LoggerFactory.getLogger(AseCovidDashboardApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AseCovidDashboardApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(
			DownloadService service,
			DownloadPopulationData downloadPopulationData,
			OverviewRepository repository
	) {
		return (args) -> {
			downloadPopulationData.downloadPopulation();
			service.downloadAllCovidData();
			log.info(repository.findById(1l).toString());
		};
	}

}
