package at.technikumwien.bonusservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "t_author_bonus")
public class AuthorBonus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column
	private Long authorId;

	@NotNull
	@Column
	private Long clicks;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable = false, name = "bankaccountid")
	private BankAccount bankAccount;

	public AuthorBonus(Long authorId, Long clicks, BankAccount bankAccount) {
		this(null, authorId, clicks, bankAccount);
	}
}
