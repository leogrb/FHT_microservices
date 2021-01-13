package at.technikumwien.statisticsservice.sight;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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

	@Column
	@NotNull
	private Long clicks;
}
