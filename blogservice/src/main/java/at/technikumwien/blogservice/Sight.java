package at.technikumwien.blogservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
