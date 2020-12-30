package at.technikumwien.statisticsservice.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "t_sight_statistics")
public class SightStatistics {

	@EmbeddedId
	private SightStatisticsPK key;

	@MapsId("monthId")
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "month_id")
	private Month month;

	@MapsId("yearId")
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "year_id")
	private Year year;

	@Column
	private Long clicks;

	public SightStatistics(Month month, Year year) {
		this(null, month, year, null);
	}
}
