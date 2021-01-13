package at.technikumwien.statisticsservice.author;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Author {
	private Long id;
	private String firstname;
	private String lastname;
	private String email;
	private LocalDate birthdate;
}
