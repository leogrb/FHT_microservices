package at.technikumwien.statisticsservice;

import at.technikumwien.statisticsservice.model.Sight;
import at.technikumwien.statisticsservice.model.SightStatisticsPK;
import org.springframework.data.jpa.repository.JpaRepository;

import at.technikumwien.statisticsservice.model.SightStatistics;

import java.util.List;

public interface SightStatisticsRepository extends JpaRepository<SightStatistics, SightStatisticsPK> {
    List<SightStatistics> findByKeyMonthIdAndKeyYearId(long monthId, long yearId);
}
