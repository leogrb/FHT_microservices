package at.technikumwien.statisticsservice;

import at.technikumwien.statisticsservice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import javax.transaction.Transactional;
import java.util.Random;

@Configuration
public class DBInitializer {
    @Autowired
    private SightStatisticsRepository sightStatisticsRepository;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void handleApplicationReady(ApplicationReadyEvent event) {
        // for some reason, there are two application context -> it calls then twice
        // this method and second time it throws an exception in SQL (unique mails)
        if (!(event.getApplicationContext() instanceof AnnotationConfigApplicationContext)) {
            return;
        }

        // insert dummy data
        Random random = new Random();
        int max = 100;
        for (EMonth month : EMonth.values()) {
            SightStatistics sightStatistics = new SightStatistics(new Month(month.getId(), month.name()), new Year(1L, 2020L));
            sightStatistics.setClicks(Math.abs(random.nextLong() % max));
            sightStatistics.setKey(new SightStatisticsPK(sightStatistics.getMonth().getId(), sightStatistics.getYear().getId(), 1L));
            sightStatisticsRepository.save(sightStatistics);

            SightStatistics sightStatistics2 = new SightStatistics(new Month(month.getId(), month.name()), new Year(1L, 2020L));
            sightStatistics2.setClicks(Math.abs(random.nextLong() % max));
            sightStatistics2.setKey(new SightStatisticsPK(sightStatistics2.getMonth().getId(), sightStatistics2.getYear().getId(), 2L));
            sightStatisticsRepository.save(sightStatistics2);
        }
    }
}