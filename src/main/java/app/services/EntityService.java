package app.services;

import app.dtos.ActorDTO;
import app.dtos.GenreDTO;
import app.dtos.MovieDTO;
import app.entities.Actor;
import app.entities.Genre;
import app.entities.Movie;

import java.util.ArrayList;
import java.util.List;

public class EntityService {

    //Vi har dto'er
    //Dto'er skal laves til entiteter.
    //MovieDTO indeholder data til Movie, Genre, Actor.

  /*  public Movie convertToMovie(MovieDTO movieDTO) {

        List<ActorDTO> actorDTOS = movieDTO.getCredits().getCast();
        List<Actor> actors = new ArrayList<>();

        for (ActorDTO actorDTO : actorDTOS) {
            Actor actor = new Actor(actorDTO.getId(), actorDTO.getName());
            actors.add(actor);
        }

        List<GenreDTO> genreDTOS = movieDTO.getGenres();
        List<Genre> genres = new ArrayList<>();

        for (GenreDTO genreDTO : genreDTOS) {
            Genre genre = new Genre(genreDTO.getId(), genreDTO.getName());
            genres.add(genre);
        }



        //Director
        //Movie movie  = new Movie (actor, genre, director
        return
    } */
}
