package com.ase.ase;

import com.ase.ase.activemq.MessagingService;
import com.ase.ase.activemq.UpdateDataMessage;
import com.ase.ase.dao.*;
import com.ase.ase.services.DownloadPopulationData;
import com.ase.ase.services.DownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Timer;
import java.util.TimerTask;
import javax.jms.JMSException;

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
      OverviewRepository repository,
      MessagingService messagingService
  ) {
    messagingService.init("new-data");
    repeatPushingDataForDemo(messagingService);
    return (args) -> {
      downloadPopulationData.downloadPopulation();
      service.downloadAllCovidData();
      messagingService.send(new UpdateDataMessage(true));
      log.info(repository.findById(1l).toString());
    };
  }

  private void repeatPushingDataForDemo(MessagingService messagingService) {
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        messagingService.send(new UpdateDataMessage(false));
      }
    }, 0, 10000);
  }
}
