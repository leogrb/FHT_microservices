package at.technikumwien.bonusservice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import at.technikumwien.bonusservice.model.AuthorBonus;
import at.technikumwien.bonusservice.model.AuthorBonusPK;

public interface AuthorBonusRepository extends JpaRepository<AuthorBonus, AuthorBonusPK> {
	List<AuthorBonus> findByKeyMonthIdAndKeyYearId(Long monthId, Long yearId);
}
