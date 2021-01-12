package at.technikumwien.bonusservice;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import at.technikumwien.bonusservice.model.Article;
import at.technikumwien.bonusservice.model.Author;
import at.technikumwien.bonusservice.model.AuthorBonus;
import at.technikumwien.bonusservice.model.AuthorBonusPK;
import at.technikumwien.bonusservice.model.BankAccount;
import at.technikumwien.bonusservice.model.EMonth;
import lombok.extern.java.Log;

@Component
@Log
public class ArticleService {
	@Autowired
	private AuthorBonusRepository authorBonusRepository;

	@StreamListener(Sink.INPUT)
	public void handleArticleEvent(final ArticleEvent articleEvent) {
		if (articleEvent.getEventType().equals(EArticleEventType.CLICKED)) {
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
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(System.currentTimeMillis()));
		Long month = (long) calendar.get(Calendar.MONTH) + 1;
		Long year = (long) calendar.get(Calendar.YEAR);
		if (EMonth.isEMonth(month)) {
			Optional<AuthorBonus> authorBonus = authorBonusRepository.findById(new AuthorBonusPK(month, year, id));
			authorBonus.ifPresentOrElse((a) -> {
				a.setClicks(a.getClicks() + 1);
				authorBonusRepository.save(a);
				log.info("Author with ID " + id + " has now " + a.getClicks() + " registered clicks in " + month + "/"
						+ year + ".");
			}, () -> {
				AuthorBonus newAuthorBonus = new AuthorBonus();
				newAuthorBonus.setKey(new AuthorBonusPK(month, year, id));
				newAuthorBonus.setClicks(1L);
				newAuthorBonus.setBankAccount(new BankAccount("AT831488912991291929", "GIBAATWWWXX", "Erste Bank")); // dummy
																														// bank
																														// account
																														// not
																														// unique
				authorBonusRepository.save(newAuthorBonus);
				log.info("New AuthroBonus entry created. Author with ID " + id + " has now "
						+ newAuthorBonus.getClicks() + " registered clicks in " + month + "/" + year + ".");
			});
		} else {
			log.info("AuthorBonus cannot be updated. Invalid month: " + month);
		}
	}

}
