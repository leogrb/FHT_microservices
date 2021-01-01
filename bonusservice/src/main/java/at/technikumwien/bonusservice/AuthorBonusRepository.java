package at.technikumwien.bonusservice;

import at.technikumwien.bonusservice.model.AuthorBonus;
import at.technikumwien.bonusservice.model.AuthorBonusPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorBonusRepository extends JpaRepository<AuthorBonus, AuthorBonusPK> {
    List<AuthorBonus> findByKeyMonthIdAndKeyYearId(Long monthId, Long yearId);
}
