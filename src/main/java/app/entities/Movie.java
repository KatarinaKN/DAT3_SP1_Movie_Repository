package app.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="movies")
@Getter
@NoArgsConstructor
@ToString
public class Movie implements IEntity {
    @Id
    private Long id;

    private String title;
    LocalDate release_date;

    @JsonProperty("vote_average")
    private double averageRating;

    //Mange film har en instruktør
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Director director;

    //Mange film har mange skuespillere
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Actor> actors = new HashSet<>();

    //Mange film har mange genrer
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Genre> genres = new HashSet<>();

    public Movie(Long id, String title, LocalDate release_date, double averageRating, Director director, Set<Actor> actors, Set<Genre> genres) {
        this.id = id;
        this.title = title;
        this.release_date = release_date;
        this.averageRating = averageRating;
        this.director = director;
        this.actors = actors;
        this.genres = genres;
    }

    @Override
    public Long getID() {
        return this.id;
    }

}
