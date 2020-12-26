package at.technikumwien.blogservice;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/resources/articles")
@CrossOrigin
@Log
public class ArticleResource {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping
    public List<Article> retrieveAll() {
        return articleRepository.findAll();
    }

    @GetMapping("/{id}")
    public Article retrieve(@PathVariable long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException("Can't find article with id " + id, 1));
    }

    @PostMapping(path = "/create")
    public ResponseEntity<?> create(@RequestBody Article article) {
        article.setId(null);
        article = articleRepository.save(article);
        URI location = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(getClass()).retrieve(article.getId())
        ).toUri();
        return ResponseEntity.created(location).build();
    }
}
