package at.technikumwien.bonusservice.bankaccount;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.iban4j.Iban;

public class BankAccountGenerator {

	private static final List<String> bankNames = Arrays.asList("Erste Bank", "Bank Austria", "Raiffeisen Bank",
			"BAWAG P.S.K.", "OberBank", "Addiko Bank", "DenizBank", "Sberbank Europe", "VakifBank International",
			"Volksbank");
	private static final List<String> bankSwifts = Arrays.asList("GIBAATWWXXX", "BKAUATWWXXX", "RZBAATWWXXX", "BAWAATWWXXX",
			"OBKLAT2LXXX", "HSEEAT2KXXX", "ESBKATWWXXX", "SABRATWWXXX", "TVBAATWWXXX", "VBOEATWWXXX");

	public static BankAccount create() {
		Iban randomIban = Iban.random();
		Random random = new Random();
		int randomIndex = random.nextInt(bankNames.size());
		return new BankAccount(randomIban.toFormattedString(), bankSwifts.get(randomIndex), bankNames.get(randomIndex));
	}

}
