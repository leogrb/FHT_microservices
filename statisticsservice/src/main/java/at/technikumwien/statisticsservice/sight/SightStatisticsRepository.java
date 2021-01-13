package at.technikumwien.statisticsservice.sight;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SightStatisticsRepository extends JpaRepository<SightStatistics, SightStatisticsPK> {
    List<SightStatistics> findByKeyMonthIdAndKeyYearId(long monthId, long yearId);
}
