package at.technikumwien.bonusservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "t_bank_account")
public class BankAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(length = 50)
	private String iban;

	@NotNull
	@Column(length = 50)
	private String bic;

	@NotNull
	@Column(length = 100)
	private String bank;

	public BankAccount(String iban, String bic, String bank) {
		this(null, iban, bic, bank);
	}
}
