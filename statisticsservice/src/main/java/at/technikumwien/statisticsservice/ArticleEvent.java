package at.technikumwien.statisticsservice;

import at.technikumwien.statisticsservice.model.Article;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArticleEvent {
	private long timestamp;
	private Article article;
	private ArticleEventType eventType;
}