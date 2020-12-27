package at.technikumwien.bonusservice;

import org.springframework.data.jpa.repository.JpaRepository;

import at.technikumwien.bonusservice.model.AuthorBonus;

public interface AuthorBonusRepository extends JpaRepository<AuthorBonus, Long> {
}
