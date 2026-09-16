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
@Table(name = "movies")
@Getter
@NoArgsConstructor
@ToString
public class Movie implements IEntity {
    @Id
    private Long id;

    private String title;
    LocalDate release_date;

    private double averageRating;

    //Mange film har en instruktør
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id")
    @ToString.Exclude
    private Director director;

    //Mange film har mange skuespillere
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            //Navn på koblingstabel er movie_actor
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            //Many to many kræver koblingstabel der peger på PK
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    @ToString.Exclude
    private Set<Actor> actors = new HashSet<>();

    //Mange film har mange genrer
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            //Navn på koblingstabel er movie_genre. Det er standardkonvetion i JPA/SQL at navngive sådan
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
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
