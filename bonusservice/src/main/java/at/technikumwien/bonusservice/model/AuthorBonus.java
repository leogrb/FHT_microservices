package at.technikumwien.bonusservice.model;

import javax.persistence.*;
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

	@EmbeddedId
	AuthorBonusPK key;

	@NotNull
	@Column
	private Long clicks;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable = false, name = "bankaccountid")
	private BankAccount bankAccount;
}
