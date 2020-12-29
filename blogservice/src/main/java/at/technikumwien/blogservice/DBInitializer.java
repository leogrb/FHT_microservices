package at.technikumwien.blogservice;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class DBInitializer {
    @Autowired
    private ArticleRepository articleRepository;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void handleApplicationReady(ApplicationReadyEvent event) {
    	// for some reason, there are two application context -> it calls then twice
    	// this method and second time it throws an exception in SQL (unique mails)
    	if (!(event.getApplicationContext() instanceof AnnotationConfigApplicationContext)) {
    		return;
    	}
        Article article = articleRepository.save(
                new Article(
                        "Ein Nachmittag im Labyrinth in Schönbrunn",
                        "Es handelt sich um einen Wandelgang zwischen hohen und schmalen Hecken, ohne Sackgassen und Verirrungen, der zum „Lustwandeln“ einladen sollte.",
                        LocalDate.of(2020, 1, 1),
                        false,
                        new Author("Leo", "Gruber", "dummy@outlook.com", LocalDate.of(1997, 1, 15)),
                        new Sight("Schloss Schönbrunn", "Das Schloss Schönbrunn, in seiner heutigen Form im 18. Jahrhundert als Sommerresidenz für Erzherzogin Maria Theresia errichtet, liegt seit 1892 im 13. Wiener Gemeindebezirk, Hietzing.", "Wien")
                )
        );

        Article article2 = articleRepository.save(
                new Article(
                        "Ein sonniges Wochenende in der Wachau",
                        "Eine schöne Runde verspricht der St. Michael Rundweg von Spitz nach St. Michael und wieder retour. Rund 7 Kilometer Abwechslung mit Weingärten, Wälder und der Donau warten auf dich.",
                        LocalDate.of(2020, 5, 20),
                        true,
                        new Author("Stefan", "Miljevic", "dummy2@outlook.com", LocalDate.of(1998, 6, 15)),
                        new Sight("Wachau", "Die Wachau ist die Landschaft im und um das Tal der Donau zwischen Melk und Krems an der Donau in Niederösterreich. Im Jahr 2000 wurde sie als Kulturlandschaft Wachau mit den Stiften Melk und Göttweig sowie der Altstadt von Krems in die Liste des UNESCO-Weltkultur- und -naturerbes aufgenommen.", "Melk")
                )
        );

        Author author = article.getAuthor();

        Article article3 = articleRepository.save(
                new Article(
                        "Tagesausflug auf die Festung Hohensalzburg",
                        "Es handelte sich um einen Wandelgang zwischen hohen und schmalen Hecken, ohne Sackgassen und Verirrungen, der zum „Lustwandeln“ einladen sollte.",
                        LocalDate.of(2018, 6, 1),
                        false,
                        new Author(author.getId(), author.getFirstname(), author.getLastname(), author.getEmail(), author.getBirthdate()),
                        new Sight("Festung Hohensalzburg", "Die Festung Hohensalzburg ist das Wahrzeichen der Stadt Salzburg. Sie liegt auf einem Stadtberg oberhalb der Stadt Salzburg, dem Festungsberg, der sich nach Nordwesten in den Mönchsberg fortsetzt. ", "Salzburg")
                )
        );

        Sight sight = article.getSight();

        Article article4 = articleRepository.save(
                new Article(
                        "Aussicht Genießen auf der Gloriette",
                        "Die Gloriette wurde im Jahr 1775 als letzte Baulichkeit des Gartens nach Plänen von Johann Ferdinand Hetzendorf von Hohenberg als „Ruhmestempel“, zugleich Hauptblickfang (Point de vue) des Gartens und auf 241 m ü. A. Aussichtspunkt über denselben erbaut.",
                        LocalDate.of(2020, 4, 1),
                        false,
                        new Author(author.getId(), author.getFirstname(), author.getLastname(), author.getEmail(), author.getBirthdate()),
                        new Sight(sight.getId(), sight.getName(), sight.getDescription(), sight.getCity())
                )
        );
    }
}
