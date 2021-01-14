package at.technikumwien.statisticsservice.sight;

import com.netflix.discovery.EurekaClient;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/resources/sight-statistics")
@CrossOrigin
@Log
public class SightStatisticsResource {

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @Autowired
    private SightStatisticsRepository sightStatisticsRepository;

    @Autowired
    private EurekaClient eurekaClient;

    private static final String BLOG_SERVICE_ID = "BLOGSERVICE";

    @GetMapping
    public List<SightStatisticsDto> retrieveAll() {
        log.info("Retrieving all sight statistics ...");
        List<SightStatistics> sightStats = sightStatisticsRepository.findAll();
        List<SightStatisticsDto> result = new ArrayList<>();
        for (SightStatistics sightStat : sightStats) {
            Sight sight = getSightForSightStatistics(sightStat);
            if (sight != null) {
                result.add(SightStatisticsDto.of(sightStat, sight));
            }
        }
        return result;
    }

    private Sight getSightForSightStatistics(SightStatistics sightStatistics) {
        try {
            return restTemplate.getForObject(getSightResourceUrl(sightStatistics.getKey().getSightId()), Sight.class);
        } catch (HttpClientErrorException e) {
            log.info("Rest Template Client Error. " + e.getMessage());
            return null;
        }
    }

    private String getSightResourceUrl(Long sightId) {
        return "http://" + BLOG_SERVICE_ID + "/resources/sights/" + sightId;
    }

}
