package app.entities;

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
    private int id;

    private String title;
    private LocalDate releaseDate;
    private double popularity;
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

    public Movie(int id, String title, LocalDate releaseDate, double popularity, double averageRating, Director director, Set<Actor> actors, Set<Genre> genres) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
        this.averageRating = averageRating;
        this.director = director;
        this.actors = actors;
        this.genres = genres;
    }

    //For test, can be deleted if needed
    public Movie(int id, String title, LocalDate releaseDate, double averageRating) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.averageRating = averageRating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRelease_date(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public int getID() {
        return this.id;
    }


}
