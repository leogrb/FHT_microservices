package at.technikumwien.bonusservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Sight {
    private Long id;
    private String name;
    private String description;
    private String city;
}
