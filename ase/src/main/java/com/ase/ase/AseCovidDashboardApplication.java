package com.ase.ase;

import com.ase.ase.activemq.MessagingService;
import com.ase.ase.activemq.UpdateDataMessage;
import com.ase.ase.downloadServices.DownloadPopulationData;
import com.ase.ase.downloadServices.DownloadScheduleService;
import com.ase.ase.downloadServices.DownloadService;
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
  public CommandLineRunner start(
      DownloadService downloadService,
      DownloadScheduleService scheduleService,
      DownloadPopulationData downloadPopulationService,
      MessagingService messagingService
  ) {
    return (args) -> {
      messagingService.init("new-data");
      downloadPopulationService.downloadPopulation();
      boolean downloaded = downloadService.downloadAllCovidData();
      messagingService.send(new UpdateDataMessage(downloaded));
      scheduleService.setTimerForDownloadingNewData();
    };
  }
}
