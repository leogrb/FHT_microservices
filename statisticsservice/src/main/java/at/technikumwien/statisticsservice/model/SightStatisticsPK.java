package at.technikumwien.statisticsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Embeddable
public class SightStatisticsPK implements Serializable {
    @Column(name = "monthId")
    protected Long monthId;
    @Column(name = "yearId")
    protected Long yearId;
    @Column(name = "sightId")
    protected Long sightId;
}
