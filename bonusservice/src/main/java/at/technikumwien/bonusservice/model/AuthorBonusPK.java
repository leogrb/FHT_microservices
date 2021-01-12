package at.technikumwien.bonusservice.model;

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
public class AuthorBonusPK implements Serializable {
	private static final long serialVersionUID = -5406726227377231245L;
	@Column(name = "monthId")
	protected Long monthId;
	@Column(name = "yearId")
	protected Long yearId;
	@Column(name = "authorId")
	protected Long authorId;
}