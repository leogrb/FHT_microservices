package at.technikumwien.bonusservice.author;

import com.netflix.discovery.EurekaClient;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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
            Author author = getAuthorForAuthorBonus(authorBonus);
            if (author != null) {
                result.add(AuthorBonusDto.of(authorBonus, author));
            }
        }
        return result;
    }

    private Author getAuthorForAuthorBonus(AuthorBonus authorBonus) {
        try {
            return restTemplate.getForObject(getAuthorResourceUrl(authorBonus.getKey().getAuthorId()), Author.class);
        } catch (HttpClientErrorException e) {
            log.info("Rest Template Client Error. " + e.getMessage());
            return null;
        }
    }

    private String getAuthorResourceUrl(Long authorId) {
        return "http://" + BLOG_SERVICE_ID + "/resources/authors/" + authorId;
    }
}
