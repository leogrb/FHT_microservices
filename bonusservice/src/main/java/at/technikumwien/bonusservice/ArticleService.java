package at.technikumwien.bonusservice;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Component
@Log
public class ArticleService {

	@StreamListener(Sink.INPUT)
	public void handleArticleEvent(final ArticleEvent articleEvent) {
		if (articleEvent.getEventType().equals(ArticleEventType.CLICKED)) {
			// TODO handle click
			log.info("Clicked bonusservice");
		}
	}

}
