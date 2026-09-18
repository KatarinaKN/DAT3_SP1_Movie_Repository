package app.services;

import app.config.HibernateConfig;
import app.dao.GenericDAO;
import app.dtos.*;
import app.entities.Actor;
import app.entities.Director;
import app.entities.Genre;
import app.entities.Movie;
import app.exceptions.ApiException;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EntityService {

    private final MovieService movieService = new MovieService();
    private final List<MovieDTO> movieDTOS = movieService.getMovies();
    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    private final GenericDAO<Genre, Integer> genreDAO = new GenericDAO<>(emf, Genre.class);
    private final GenericDAO<Actor, Integer> actorDAO = new GenericDAO<>(emf, Actor.class);
    private final GenericDAO<Director, Integer> directorDAO = new GenericDAO<>(emf, Director.class);

    public List<Movie> convertToMovieEntities() {
        List<Movie> movies = new ArrayList<>();

        for (MovieDTO movieDTO : movieDTOS) {
            int movieId = movieDTO.getId();
            String title = movieDTO.getTitle();
            double popularity = movieDTO.getPopularity();
            LocalDate releaseDate = movieDTO.getReleaseDate();
            double averageRating = movieDTO.getAverageRating();

            Set<Genre> genres = convertToGenreEntities(movieDTO);
            Set<Actor> actors = convertToActorEntities(movieDTO);
            Director director = convertToDirectorEntity(movieDTO);

            Movie movie = new Movie(movieId, title, releaseDate, popularity, averageRating, director, actors, genres);
            movies.add(movie);
        }
        return movies;
    }

    private Set<Genre> convertToGenreEntities(MovieDTO movieDTO) {
        List<GenreDTO> genreDTOS = movieDTO.getGenres();
        Set<Genre> genres = new HashSet<>();
        for (GenreDTO genreDTO : genreDTOS) {
            genres.add(getOrCreateGenre(genreDTO));
        }
        return genres;
    }

    private Set<Actor> convertToActorEntities(MovieDTO movieDTO) {
        List<ActorDTO> actorDTOS = movieDTO.getCredits().getCast();
        Set<Actor> actors = new HashSet<>();
        for (ActorDTO actorDTO : actorDTOS) {
            actors.add(getOrCreateActor(actorDTO));
        }
        return actors;
    }

    private Director convertToDirectorEntity(MovieDTO movieDTO) {
        List<CrewMemberDTO> crewMemberDTOS = movieDTO.getCredits().getCrew();

      return  crewMemberDTOS.stream()
                //Filter to get director
                .filter(crew -> "Director".equals(crew.getJob()))
                //Get the a director
                .findFirst()
                //get id and name to create a director
                .map(director-> getOrCreateDirector(director.getId(), director.getName()))
                //returns 404 if nothing was found
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND.value(), "No director was found for movie: " + movieDTO.getTitle()));
    }

    private Director getOrCreateDirector(int id, String name) {
        try {
            return directorDAO.read(id);
        } catch (ApiException e) {
            if (e.getCode() != 404) {
                throw e;
            }
            Director newDirector = new Director(id, name);
            return directorDAO.create(newDirector);
        }
    }

    private Genre getOrCreateGenre(GenreDTO genreDTO) {
        try {
            return genreDAO.read(genreDTO.getId());
        } catch (ApiException e){
            if (e.getCode() != 404) {
                throw e;
            }
            Genre newGenre = new Genre(genreDTO.getId(), genreDTO.getName());
            return genreDAO.create(newGenre);
        }
    }

    private Actor getOrCreateActor(ActorDTO actorDTO) {
        try {
            return actorDAO.read(actorDTO.getId());
        } catch (ApiException e){
            if (e.getCode() != 404) {
                throw e;
            }
            Actor newActor = new Actor(actorDTO.getId(), actorDTO.getName());
            return actorDAO.create(newActor);
        }
    }
}
