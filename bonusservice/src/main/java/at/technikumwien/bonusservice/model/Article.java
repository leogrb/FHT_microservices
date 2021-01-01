package at.technikumwien.bonusservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
