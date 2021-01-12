package at.technikumwien.blogservice.sight;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "t_sight")
public class Sight {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(length = 100)
	private String name;

	@NotNull
	@Column(length = 1000)
	private String description;

	@NotNull
	@Column(length = 100)
	private String city;

	public Sight(String name, String description, String city) {
		this(null, name, description, city);
	}
}
