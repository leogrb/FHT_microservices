package at.technikumwien.blogservice;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "t_author")
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(length = 50)
	private String firstname;

	@NotNull
	@Column(length = 50)
	private String lastname;

	@NotNull
	@NotEmpty
	@Email
	@Column(unique = true)
	private String email;

	@NotNull
	private LocalDate birthdate;

	public Author(String firstname, String lastname, String email, LocalDate birthdate) {
		this(null, firstname, lastname, email, birthdate);
	}
}
