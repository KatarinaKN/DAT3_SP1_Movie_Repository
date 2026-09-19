package app.dao;

import app.entities.Actor;
import jakarta.persistence.EntityManagerFactory;

public class ActorDAO extends GenericDAO<Actor, Integer> {
    public ActorDAO(EntityManagerFactory emf) {
        super(emf, Actor.class);
    }
}
