package app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Movie implements IEntity {
    @Id
    private int id;

    @Setter
    private String title;
    @Setter
    private LocalDate releaseDate;
    private double popularity;
    @Setter
    private double averageRating;

    //Mange film har en instruktør
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id")
    @ToString.Exclude
    private Director director;

    //Mange film har mange skuespillere
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            //Navn på koblingstabel er movie_actor
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            //Many to many kræver koblingstabel der peger på PK
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    @ToString.Exclude
    private Set<Actor> actors = new HashSet<>();

    //Mange film har mange genrer.
    // Ændret LAZY to EAGER fordi Hibernate lukker til db efter at have hentet film og får ikke genrer med
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            //Navn på koblingstabel er movie_genre. Det er standardkonvetion i JPA/SQL at navngive sådan
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @ToString.Exclude
    private Set<Genre> genres = new HashSet<>();

    //For test, can be deleted if needed
    public Movie(int id, String title, LocalDate releaseDate, double averageRating) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.averageRating = averageRating;
    }

    @Override
    public int getID() {
        return this.id;
    }
}
