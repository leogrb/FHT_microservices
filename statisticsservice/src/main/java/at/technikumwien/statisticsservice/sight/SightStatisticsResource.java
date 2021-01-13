package at.technikumwien.statisticsservice.sight;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/resources/sight-statistics")
@CrossOrigin
@Log
public class SightStatisticsResource {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private SightStatisticsRepository sightStatisticsRepository;

	@Autowired
	private EurekaClient eurekaClient;

	@GetMapping
	public List<SightStatisticsDto> retrieveAll() {
		log.info("Retrieving all sight statistics ...");
		List<SightStatistics> sightStats = sightStatisticsRepository.findAll();
		List<SightStatisticsDto> result = new ArrayList<>();
		for (SightStatistics sightStat : sightStats) {
			Sight sight = restTemplate.getForObject(getSightResourceUrl(sightStat.getKey().getSightId()), Sight.class);
			result.add(SightStatisticsDto.of(sightStat, sight));
		}
		return result;
	}

	private String getSightResourceUrl(Long sightId) {
		Application application = eurekaClient.getApplication("blogservice");
		InstanceInfo instanceInfo = application.getInstances().get(0);
		String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "resources/sights/"
				+ sightId;
		return url;
	}

}
