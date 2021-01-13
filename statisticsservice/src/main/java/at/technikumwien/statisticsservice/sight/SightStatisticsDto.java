package at.technikumwien.statisticsservice.sight;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SightStatisticsDto {

	private Long yearId;
	private Long monthId;
	private Long clicks;
	private Sight sight;

	public static SightStatisticsDto of(SightStatistics sightStatistics, Sight sight) {
		return new SightStatisticsDto(sightStatistics.getKey().getYearId(), sightStatistics.getKey().getMonthId(),
				sightStatistics.getClicks(), sight);
	}

}
