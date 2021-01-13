package at.technikumwien.blogservice.author;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resources/authors")
@CrossOrigin
public class AuthorResource {

	@Autowired
	private AuthorRepository authorRepository;

	@GetMapping
	public List<Author> retrieveAll() {
		return authorRepository.findAll();
	}

	@GetMapping("/{id}")
	public Author retrieve(@PathVariable long id) {
		final Optional<Author> author = authorRepository.findById(id);
		if (!author.isPresent()) {
			throw new EmptyResultDataAccessException("Can't find author with id " + id, 1);
		}
		return author.get();
	}

}
