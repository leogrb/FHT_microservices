package at.technikumwien.blogservice.sight;

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
@RequestMapping("/resources/sights")
@CrossOrigin
public class SightResource {

	@Autowired
	private SightRepository sightRepository;

	@GetMapping
	public List<Sight> retrieveAll() {
		return sightRepository.findAll();
	}

	@GetMapping("/{id}")
	public Sight retrieve(@PathVariable long id) {
		final Optional<Sight> sight = sightRepository.findById(id);
		if (!sight.isPresent()) {
			throw new EmptyResultDataAccessException("Can't find sight with id " + id, 1);
		}
		return sight.get();
	}
}
