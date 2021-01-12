package at.technikumwien.statisticsservice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import at.technikumwien.statisticsservice.model.SightStatistics;
import at.technikumwien.statisticsservice.model.SightStatisticsPK;

public interface SightStatisticsRepository extends JpaRepository<SightStatistics, SightStatisticsPK> {
    List<SightStatistics> findByKeyMonthIdAndKeyYearId(long monthId, long yearId);
}
