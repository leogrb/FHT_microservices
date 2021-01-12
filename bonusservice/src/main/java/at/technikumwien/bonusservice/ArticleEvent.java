package at.technikumwien.bonusservice;

import at.technikumwien.bonusservice.model.Article;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArticleEvent {
	private long timestamp;
	private Article article;
	private EArticleEventType eventType;
}