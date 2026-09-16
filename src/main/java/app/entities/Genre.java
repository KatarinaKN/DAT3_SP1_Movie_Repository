package app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="genres")
@Getter
@NoArgsConstructor
@ToString
public class Genre implements IEntity {
    @Id
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "genres", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ToString.Exclude
    private Set<Movie> movies   = new HashSet<>();

    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Long getID() {
        return this.id;
    }
}

