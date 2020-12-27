package at.technikumwien.statisticsservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "t_sight_statistics")
@Table
public class SightStatistics {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Long sightId;

	@Column
	private Long totalClicks;

	@Column
	private Long monthlyClicks;

	public SightStatistics(Long sightId, Long totalClicks, Long monthlyClicks) {
		this(null, sightId, totalClicks, monthlyClicks);
	}
}
