package app.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="genres")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Genre implements IEntity {
    @Id
    private int id;
    private String name;

    @ManyToMany(mappedBy = "genres", cascade = {CascadeType.MERGE})
    @ToString.Exclude
    private Set<Movie> movies   = new HashSet<>();

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getID() {
        return this.id;
    }
}

