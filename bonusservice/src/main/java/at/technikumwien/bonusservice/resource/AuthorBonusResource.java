package at.technikumwien.bonusservice.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.technikumwien.bonusservice.AuthorBonusRepository;
import at.technikumwien.bonusservice.model.AuthorBonus;
import lombok.extern.java.Log;

// TODO: Still to be defined what resources are needed outside

@RestController
@RequestMapping("/resources/authorbonus")
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
}
