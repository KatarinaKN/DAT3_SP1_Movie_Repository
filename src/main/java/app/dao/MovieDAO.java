package app.dao;

import app.entities.Movie;
import jakarta.persistence.EntityManagerFactory;

public class MovieDAO extends GenericDAO<Movie, Long> {
    public MovieDAO(EntityManagerFactory emf) {
        super(emf, Movie.class);
    }
}
