package at.technikumwien.bonusservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class Author {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private LocalDate birthdate;
}
