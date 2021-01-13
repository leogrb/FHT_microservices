package at.technikumwien.bonusservice.author;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorBonusRepository extends JpaRepository<AuthorBonus, AuthorBonusPK> {
	List<AuthorBonus> findByKeyMonthIdAndKeyYearId(Long monthId, Long yearId);
}
