package com.ase.ase;

import com.ase.ase.dao.*;
import com.ase.ase.services.DownloadOverview;
import com.ase.ase.services.DownloadPopulationData;
import com.ase.ase.services.DownloadService;
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
			//repository.save(new Overview(3474856, 330343, 328677, 34537, 291042, 4764, 4418));
			downloadPopulationData.downloadTimeline();
			service.downloadAllCovidData();
			log.info(repository.findById(1l).toString());
		};
	}

}
