package at.technikumwien.statisticsservice.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.technikumwien.statisticsservice.SightStatisticsRepository;
import at.technikumwien.statisticsservice.model.SightStatistics;
import lombok.extern.java.Log;

@RestController
@RequestMapping("/resources/sight-statistics")
@CrossOrigin
@Log
public class SightStatisticsResource {

	@Autowired
	private SightStatisticsRepository sightStatisticsRepository;

	@GetMapping
	public List<SightStatistics> retrieveAll() {
		log.info("Retrieving all sight statistics ...");
		return sightStatisticsRepository.findAll();
	}
}
