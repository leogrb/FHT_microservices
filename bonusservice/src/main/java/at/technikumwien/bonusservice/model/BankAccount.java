package at.technikumwien.bonusservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
