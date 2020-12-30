package com.ase.ase;

import com.ase.ase.activemq.MessagingService;
import com.ase.ase.activemq.UpdateDataMessage;
import com.ase.ase.services.DownloadPopulationData;
import com.ase.ase.services.DownloadScheduleService;
import com.ase.ase.services.DownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.jms.JMSException;

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
  ) throws JMSException {
    messagingService.init("new-data");
    scheduleService.setTimerForDownloadingNewData(messagingService, downloadService);
    return (args) -> {
      downloadPopulationService.downloadPopulation();
      boolean downloaded = downloadService.downloadAllCovidData();
      messagingService.send(new UpdateDataMessage(downloaded));
    };
  }
}
