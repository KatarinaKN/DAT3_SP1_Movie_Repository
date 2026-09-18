package app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="actors")
@Getter
@NoArgsConstructor
@ToString
public class Actor implements IEntity {
    @Id
    private long id;

    private String name;

    @ManyToMany(mappedBy = "actors", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ToString.Exclude
    private Set<Movie> movies   = new HashSet<>();

    public Actor(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Long getID() {
        return this.id;
    }
}
