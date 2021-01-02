package com.ase.ase.services;

import com.ase.ase.activemq.MessagingService;
import com.ase.ase.activemq.UpdateDataMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class DownloadScheduleService {
    @Autowired
    private MessagingService messagingService;
    @Autowired
    private DownloadService downloadService;

    private void downloadDataPeriodically() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                boolean downloaded = downloadService.downloadAllCovidData();
                messagingService.send(new UpdateDataMessage(downloaded));
            }
        }, 0, 86400000); //24h = 86 400 000ms
    }

    public void setTimerForDownloadingNewData() {
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
                downloadDataPeriodically();
            }
        }, date.getTime());
    }
}
