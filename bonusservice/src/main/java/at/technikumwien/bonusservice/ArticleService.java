package at.technikumwien.bonusservice;

import at.technikumwien.bonusservice.model.Article;
import at.technikumwien.bonusservice.model.Author;
import at.technikumwien.bonusservice.model.AuthorBonus;
import at.technikumwien.bonusservice.model.BankAccount;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
@Log
public class ArticleService {
    @Autowired
    private AuthorBonusRepository authorBonusRepository;

    @StreamListener(Sink.INPUT)
    public void handleArticleEvent(final ArticleEvent articleEvent) {
        if (articleEvent.getEventType().equals(ArticleEventType.CLICKED)) {
            log.info("New article CLICKED event registered of time: " + articleEvent.getTimestamp());
            Article article = articleEvent.getArticle();
            if (article != null) {
                handleArticleNotNull(article);
            } else {
                log.info("ArticleEvent unused. Article is null");
            }
        }
    }

    private void handleArticleNotNull(Article article) {
        Author author = article.getAuthor();
        if (author != null) {
            handleAuthorNotNull(author);
        } else {
            log.info("ArticleEvent unused. Author is null. No Bonus added.");
        }
    }

    private void handleAuthorNotNull(Author author) {
        Long id = author.getId();
        AuthorBonus authorBonus = authorBonusRepository.findByAuthorId(id);
        if (authorBonus != null) {
            authorBonus.setClicks(authorBonus.getClicks() + 1);
        } else {
            authorBonus = new AuthorBonus(id, 1L, new BankAccount("AT831488912991291929", "GIBAATWWWXX", "Erste Bank"));
        }
        authorBonusRepository.save(authorBonus);
        log.info("Author with ID " + id + " has now " + authorBonus.getClicks() + " registered clicks.");
    }

}
