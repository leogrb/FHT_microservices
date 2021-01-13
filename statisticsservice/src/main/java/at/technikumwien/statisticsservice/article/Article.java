package at.technikumwien.statisticsservice.article;

import java.time.LocalDate;

import at.technikumwien.statisticsservice.author.Author;
import at.technikumwien.statisticsservice.sight.Sight;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Article {
	private Long id;
	private String title;
	private String description;
	private LocalDate publicationDate;
	private boolean topNews;
	private Author author;
	private Sight sight;
}
