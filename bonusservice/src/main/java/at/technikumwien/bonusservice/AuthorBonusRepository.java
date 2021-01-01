package at.technikumwien.bonusservice;

import at.technikumwien.bonusservice.model.AuthorBonusPK;
import org.springframework.data.jpa.repository.JpaRepository;

import at.technikumwien.bonusservice.model.AuthorBonus;

public interface AuthorBonusRepository extends JpaRepository<AuthorBonus, AuthorBonusPK> {
}
