package at.technikumwien.blogservice;

import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/resources/articles")
@CrossOrigin
@Log
public class ArticleResource {

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private Source source;

	@GetMapping
	public List<Article> retrieveAll() {
		return articleRepository.findAll();
	}

	@GetMapping("/{id}")
	public Article retrieve(@PathVariable long id) {
		final Optional<Article> article = articleRepository.findById(id);
		if (!article.isPresent()) {
			throw new EmptyResultDataAccessException("Can't find article with id " + id, 1);
		}
		sendEventMessage(article.get());
		return article.get();
	}

	@PostMapping(path = "/create")
	public ResponseEntity<?> create(@RequestBody Article article) {
		article.setId(null);
		article = articleRepository.save(article);
		URI location = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(getClass()).retrieve(article.getId()))
				.toUri();
		return ResponseEntity.created(location).build();
	}

	private void sendEventMessage(Article article) {
		final ArticleEvent event = new ArticleEvent(Instant.now().getEpochSecond(), article, EArticleEventType.CLICKED);
		Message<ArticleEvent> message = MessageBuilder.withPayload(event).build();
		source.output().send(message);
	}
}
