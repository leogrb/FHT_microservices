package at.technikumwien.bonusservice.article;

import java.time.LocalDate;

import at.technikumwien.bonusservice.author.Author;
import at.technikumwien.bonusservice.sight.Sight;
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
