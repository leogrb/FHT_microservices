package at.technikumwien.blogservice.sight;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
}
