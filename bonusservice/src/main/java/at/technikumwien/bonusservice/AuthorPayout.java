package at.technikumwien.bonusservice;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import at.technikumwien.bonusservice.model.AuthorBonus;
import at.technikumwien.bonusservice.model.BankAccount;
import lombok.extern.java.Log;

@Log
@Component
public class AuthorPayout {
    @Autowired
    AuthorBonusRepository authorBonusRepository;

    private final int MULTIPLIER = 1;

    @Scheduled(fixedRate = 5000)
    public void payout() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        Long month = (long) calendar.get(Calendar.MONTH) + 1;
        Long year = (long) calendar.get(Calendar.YEAR);
        List<AuthorBonus> authorBonusList = authorBonusRepository.findByKeyMonthIdAndKeyYearId(month, year);
        log.info(
                "\n-------------------------------------\n"
                        + "Scheduled Author Bonus Payout for " + month + "/" + year
                        + "\n-------------------------------------\n");
        authorBonusList.forEach(a -> {
            BankAccount bankAccount = a.getBankAccount();
            if (bankAccount != null) {
                log.info(
                        "\n-------------------------------------" +
                                "\nAuthor ID: " + a.getKey().getAuthorId() +
                                "\nBankAccount: " + bankAccount.getBank() + " " + bankAccount.getIban() + " " + bankAccount.getBic() +
                                "\nBonus Payout: " + a.getClicks() * MULTIPLIER + " Cent" +
                                "\n-------------------------------------\n");
            } else {
                log.info(
                        "\n-------------------------------------\n" +
                                "Author ID: " + a.getKey().getAuthorId() + "\nPayout failed: No Bank Account Info."
                                + "\n-------------------------------------\n");
            }
        });
    }

}
