package at.technikumwien.bonusservice.author;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorBonusDto {

	private Author author;
	private Long yearId;
	private Long monthId;
	private Long clicks;

	public static AuthorBonusDto of(AuthorBonus authorBonus, Author author) {
		return new AuthorBonusDto(author, authorBonus.getKey().getYearId(), authorBonus.getKey().getMonthId(),
				authorBonus.getClicks());
	}

}
