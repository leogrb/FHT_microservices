package at.technikumwien.statisticsservice;

import at.technikumwien.statisticsservice.model.Sight;
import at.technikumwien.statisticsservice.model.SightStatisticsPK;
import org.springframework.data.jpa.repository.JpaRepository;

import at.technikumwien.statisticsservice.model.SightStatistics;

public interface SightStatisticsRepository extends JpaRepository<SightStatistics, SightStatisticsPK> {
}
