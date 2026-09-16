package app.dao;

import app.entities.Actor;
import jakarta.persistence.EntityManagerFactory;

public class ActorDAO extends GenericDAO<Actor, Long> {
    public ActorDAO(EntityManagerFactory emf, Class<Actor> entityClass) {
        super(emf, entityClass);
    }
}
