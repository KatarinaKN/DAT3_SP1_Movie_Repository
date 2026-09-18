package app.dao;

import app.entities.Director;
import jakarta.persistence.EntityManagerFactory;

public class DirectorDAO extends GenericDAO<Director, Integer> {
    public DirectorDAO(EntityManagerFactory emf) {
        super(emf, Director.class);
    }
}
