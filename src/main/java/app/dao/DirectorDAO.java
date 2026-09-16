package app.dao;

import app.entities.Director;
import jakarta.persistence.EntityManagerFactory;

public class DirectorDAO extends GenericDAO<Director, Long> {
    public DirectorDAO(EntityManagerFactory emf, Class<Director> entityClass) {
        super(emf, entityClass);
    }
}
