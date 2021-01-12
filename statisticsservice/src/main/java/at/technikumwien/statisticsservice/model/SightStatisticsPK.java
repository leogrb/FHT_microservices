package at.technikumwien.statisticsservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Embeddable
public class SightStatisticsPK implements Serializable {
	private static final long serialVersionUID = 4783705354041992675L;
	@Column(name = "monthId")
	protected Long monthId;
	@Column(name = "yearId")
	protected Long yearId;
	@Column(name = "sightId")
	protected Long sightId;
}
