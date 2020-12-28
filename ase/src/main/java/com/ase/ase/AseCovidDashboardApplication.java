package com.ase.ase;

import com.ase.ase.activemq.MessagingService;
import com.ase.ase.activemq.UpdateDataMessage;
import com.ase.ase.services.DownloadPopulationData;
import com.ase.ase.services.DownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Calendar;
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
  public CommandLineRunner start(
      DownloadService downloadService,
      DownloadPopulationData downloadPopulationService,
      MessagingService messagingService
  ) throws JMSException {
    messagingService.init("new-data");
    setTimerForDownloadingNewData(messagingService, downloadService);
    return (args) -> {
      downloadPopulationService.downloadPopulation();
      boolean downloaded = downloadService.downloadAllCovidData();
      messagingService.send(new UpdateDataMessage(downloaded));
    };
  }

  private void repeatPushingDataForDemo(MessagingService messagingService, DownloadService downloadService) {
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        boolean downloaded = downloadService.downloadAllCovidData();
        messagingService.send(new UpdateDataMessage(downloaded));
      }
    }, 0, 86400000); //24h = 86 400 000ms
  }

  private void setTimerForDownloadingNewData(MessagingService messagingService, DownloadService downloadService) {
    Timer timer = new Timer();
    Calendar date = Calendar.getInstance();

    //when it is before 02:10pm. update data today else update it tomorrow
    if(date.get(Calendar.HOUR_OF_DAY) > 14 || (date.get(Calendar.HOUR_OF_DAY) == 14 && date.get(Calendar.MINUTE) >= 10)) {
      date.add(Calendar.DAY_OF_MONTH, 1);
    }
    date.set(Calendar.HOUR_OF_DAY, 14);
    date.set(Calendar.MINUTE, 15);
    date.set(Calendar.SECOND, 0);

    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        repeatPushingDataForDemo(messagingService, downloadService);
      }
    }, date.getTime());
  }
}
