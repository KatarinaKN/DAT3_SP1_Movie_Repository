package app.dao;

import app.entities.Genre;
import jakarta.persistence.EntityManagerFactory;

public class GenreDAO extends GenericDAO<Genre, Integer>{
    public GenreDAO(EntityManagerFactory emf) {
        super(emf, Genre.class);
    }
}
