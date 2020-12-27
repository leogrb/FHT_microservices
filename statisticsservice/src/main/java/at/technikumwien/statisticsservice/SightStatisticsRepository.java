package at.technikumwien.statisticsservice;

import org.springframework.data.jpa.repository.JpaRepository;

import at.technikumwien.statisticsservice.model.SightStatistics;

public interface SightStatisticsRepository extends JpaRepository<SightStatistics, Long> {
}