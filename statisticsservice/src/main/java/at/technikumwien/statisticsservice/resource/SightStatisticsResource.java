package at.technikumwien.statisticsservice.resource;

import at.technikumwien.statisticsservice.SightStatisticsRepository;
import at.technikumwien.statisticsservice.model.EMonth;
import at.technikumwien.statisticsservice.model.SightStatistics;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(path = "/{yearId}/{monthId}")
    public List<SightStatistics> retrieveAllByMonth(@PathVariable long yearId, @PathVariable long monthId) {
        if (EMonth.isEMonth(monthId)) {
            log.info("Retrieving all sight statistics of " + monthId + "/" + yearId + "...");
            return sightStatisticsRepository.findByKeyMonthIdAndKeyYearId(monthId, yearId);
        } else {
            log.info("Invalid month");
            return null;
        }
    }

}
