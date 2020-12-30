package at.technikumwien.statisticsservice;

import at.technikumwien.statisticsservice.model.*;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Component
@Log
public class ArticleService {

    @Autowired
    SightStatisticsRepository sightStatisticsRepository;

    @StreamListener(Sink.INPUT)
    public void handleArticleEvent(final ArticleEvent articleEvent) {
        if (articleEvent.getEventType().equals(ArticleEventType.CLICKED)) {
            log.info("New article CLICKED event registered of time: " + articleEvent.getTimestamp());
            Article article = articleEvent.getArticle();
            if (article != null) {
                handleArticleNotNull(article);
            } else {
                log.info("ArticleEvent unused. Article is null.");
            }
        }
    }

    private void handleArticleNotNull(Article article) {
        Sight sight = article.getSight();
        if (sight != null) {
            handleSightNotNull(sight);
        } else {
            log.info("ArticleEvent unused. Sight is null. No Statistics added.");
        }
    }

    private void handleSightNotNull(Sight sight) {
        Long id = sight.getId();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        Optional<SightStatistics> sightStatistics = sightStatisticsRepository.findById(new SightStatisticsPK((long) month, (long) year, id));
        sightStatistics.ifPresentOrElse(
                (s) -> {
                    s.setClicks(s.getClicks() + 1);
                    sightStatisticsRepository.save(s);
                    log.info("Sight with ID " + id + " has now " + s.getClicks() + " clicks in " + month + "/" + year + ".");
                },
                () -> {
                    SightStatistics newSightStatistics = new SightStatistics();
                    String monthName = EMonth.getMonthNameToId((long) month);
                    if (monthName != null) {
                        newSightStatistics.setMonth(new Month((long) month, monthName));
                        newSightStatistics.setKey(new SightStatisticsPK((long) month, (long) year, id));
                        newSightStatistics.setClicks(1L);
                        sightStatisticsRepository.save(newSightStatistics);
                        log.info("New SightStatistics Entry created. Sight with ID " + id + " has now 1 click in " + month + "/" + year + ".");
                    } else {
                        log.info("SightStatistics cannot be updated. Invalid month: " + month);
                    }
                });
    }
}
