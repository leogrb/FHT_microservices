package at.technikumwien.statisticsservice;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import at.technikumwien.statisticsservice.model.Article;
import at.technikumwien.statisticsservice.model.EMonth;
import at.technikumwien.statisticsservice.model.Sight;
import at.technikumwien.statisticsservice.model.SightStatistics;
import at.technikumwien.statisticsservice.model.SightStatisticsPK;
import lombok.extern.java.Log;

@Component
@Log
public class ArticleService {

    @Autowired
    SightStatisticsRepository sightStatisticsRepository;

    @StreamListener(Sink.INPUT)
    public void handleArticleEvent(final ArticleEvent articleEvent) {
        if (articleEvent.getEventType().equals(EArticleEventType.CLICKED)) {
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
        Long month = (long) calendar.get(Calendar.MONTH) + 1;
        Long year = (long) calendar.get(Calendar.YEAR);
        if (EMonth.isEMonth(month)) {
            Optional<SightStatistics> sightStatistics = sightStatisticsRepository.findById(new SightStatisticsPK(month, year, id));
            sightStatistics.ifPresentOrElse(
                    (s) -> {
                        s.setClicks(s.getClicks() + 1);
                        sightStatisticsRepository.save(s);
                        log.info("Sight with ID " + id + " has now " + s.getClicks() + " clicks in " + month + "/" + year + ".");
                    },
                    () -> {
                        SightStatistics newSightStatistics = new SightStatistics();
                        newSightStatistics.setKey(new SightStatisticsPK(month, year, id));
                        newSightStatistics.setClicks(1L);
                        sightStatisticsRepository.save(newSightStatistics);
                        log.info("New SightStatistics Entry created. Sight with ID " + id + " has now 1 click in " + month + "/" + year + ".");
                    });
        } else {
            log.info("SightStatistics cannot be updated. Invalid month: " + month);
        }
    }
}
