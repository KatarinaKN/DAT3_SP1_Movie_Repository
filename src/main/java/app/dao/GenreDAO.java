package app.dao;

import app.entities.Genre;
import jakarta.persistence.EntityManagerFactory;

public class GenreDAO extends GenericDAO<Genre, Long>{
    public GenreDAO(EntityManagerFactory emf) {
        super(emf, Genre.class);
    }
}
