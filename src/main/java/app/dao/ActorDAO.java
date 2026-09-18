package app.dao;

import app.entities.Actor;
import app.entities.Movie;
import jakarta.persistence.EntityManagerFactory;

public class ActorDAO extends GenericDAO<Actor, Integer> {
    public ActorDAO(EntityManagerFactory emf) {
        super(emf, Actor.class);
    }
}
