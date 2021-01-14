package at.technikumwien.bonusservice.author;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/resources/author-bonus")
@CrossOrigin
@Log
public class AuthorBonusResource {

	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;

	@Autowired
	private AuthorBonusRepository authorBonusRepository;

	@Autowired
	private EurekaClient eurekaClient;

	private static final String BLOG_SERVICE_ID = "BLOGSERVICE";

	@GetMapping
	public List<AuthorBonusDto> retrieveAll() {
		log.info("Retrieving all author bonus data...");
		List<AuthorBonus> authorBonuses = authorBonusRepository.findAll();
		List<AuthorBonusDto> result = new ArrayList<>();
		for (AuthorBonus authorBonus : authorBonuses) {
			Author author = restTemplate.getForObject(getAuthorResourceUrl(authorBonus.getKey().getAuthorId()),
					Author.class);
			result.add(AuthorBonusDto.of(authorBonus, author));
		}
		return result;
	}

	private String getAuthorResourceUrl(Long authorId) {
		return "http://" + BLOG_SERVICE_ID + "/resources/authors/" + authorId;
	}
}
