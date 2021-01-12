package at.technikumwien.blogservice.author;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

}
