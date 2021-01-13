package at.technikumwien.bonusservice.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.technikumwien.bonusservice.AuthorBonusRepository;
import at.technikumwien.bonusservice.model.AuthorBonus;
import at.technikumwien.bonusservice.model.EMonth;
import lombok.extern.java.Log;

@RestController
@RequestMapping("/resources/author-bonus")
@CrossOrigin
@Log
public class AuthorBonusResource {

	@Autowired
	private AuthorBonusRepository authorBonusRepository;

	@GetMapping
	public List<AuthorBonus> retrieveAll() {
		log.info("Retrieving all author bonus data...");
		return authorBonusRepository.findAll();
	}

	@GetMapping(path = "/{yearId}/{monthId}")
	public List<AuthorBonus> retrieveAllByMonth(@PathVariable long yearId, @PathVariable long monthId) {
		if (EMonth.isEMonth(monthId)) {
			log.info("Retrieving all sight statistics of " + monthId + "/" + yearId + "...");
			return authorBonusRepository.findByKeyMonthIdAndKeyYearId(monthId, yearId);
		} else {
			log.info("Invalid month");
			return null;
		}
	}
}
