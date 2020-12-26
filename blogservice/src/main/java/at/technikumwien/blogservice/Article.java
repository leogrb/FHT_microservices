package at.technikumwien.blogservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "t_article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 100)
    private String title;

    @NotNull
    @Column(length = 1000)
    private String description;

    @NotNull
    private LocalDate publicationDate;

    @NotNull
    private boolean topNews;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(nullable = false, name = "authorid")
    private Author author;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(nullable = false, name = "sightid")
    private Sight sight;

    public Article(String title, String description, LocalDate publicationDate, boolean topNews, Author author, Sight sight) {
        this(null, title, description, publicationDate, topNews, author, sight);
    }
}
