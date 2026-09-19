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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EntityService {

    private final MovieService movieService = new MovieService();
    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    private final GenericDAO<Movie, Integer> movieDAO = new GenericDAO<>(emf, Movie.class);
    private final GenericDAO<Genre, Integer> genreDAO = new GenericDAO<>(emf, Genre.class);
    private final GenericDAO<Actor, Integer> actorDAO = new GenericDAO<>(emf, Actor.class);
    private final GenericDAO<Director, Integer> directorDAO = new GenericDAO<>(emf, Director.class);

    //Gemmer alle fetchede film (og tilhørende genrer, skuespillere og instruktører) i databasen.
    public void saveMoviesInDB() {
        List<Movie> movies = convertToMovieEntities();
        for (Movie movie : movies) {
            movieDAO.create(movie);
        }
    }

    //Konverterer MovieDTO'er til Movie-entiteter.
    private List<Movie> convertToMovieEntities() {
        List<MovieDTO> movieDTOS = movieService.getMovies();
        List<Movie> movies = new ArrayList<>();

        for (MovieDTO movieDTO : movieDTOS) {
            int movieId = movieDTO.getId();
            String title = movieDTO.getTitle();
            double popularity = movieDTO.getPopularity();
            LocalDate releaseDate = movieDTO.getReleaseDate();
            double averageRating = movieDTO.getAverageRating();

            Set<Genre> genres = convertToGenreEntities(movieDTO.getGenres());
            Set<Actor> actors = convertToActorEntities(movieDTO.getCredits().getCast());
            Director director = convertToDirectorEntity(movieDTO.getCredits().getCrew());

            Movie movie = new Movie(movieId, title, releaseDate, popularity, averageRating, director, actors, genres);
            movies.add(movie);
        }
        return movies;
    }

    //Konverterer GenreDTO'er til Genre-entiteter.
    private Set<Genre> convertToGenreEntities(List<GenreDTO> genreDTOS) {
        Set<Genre> genres = new HashSet<>();
        for (GenreDTO genreDTO : genreDTOS) {
            genres.add(getOrCreateGenre(genreDTO));
        }
        return genres;
    }

    //Konverterer ActorDTO'er til Actor-entiteter.
    private Set<Actor> convertToActorEntities(List<ActorDTO> actorDTOS) {
        Set<Actor> actors = new HashSet<>();
        for (ActorDTO actorDTO : actorDTOS) {
            actors.add(getOrCreateActor(actorDTO));
        }
        return actors;
    }

    //Finder instruktøren i listen med CrewMemberDTO'er og returnerer en Director-entitet.
    private Director convertToDirectorEntity(List<CrewMemberDTO> crewMemberDTOS) {

        return crewMemberDTOS.stream()
                //Filter to get director
                .filter(crew -> "Director".equals(crew.getJob()))
                //Get the director
                .findFirst()
                //get id and name to create a director
                .map(director -> getOrCreateDirector(director.getId(), director.getName()))
                //returns null if nothing was found
                .orElse(null);
    }

    //Tjekker, om instruktøren allerede er i databasen. Hvis ja, bliver instruktøren hentet, hvis nej, bliver instruktøren oprettet.
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

    //Tjekker, om genren allerede er i databasen. Hvis ja, bliver genren hentet, hvis nej, bliver genren oprettet.
    private Genre getOrCreateGenre(GenreDTO genreDTO) {
        try {
            return genreDAO.read(genreDTO.getId());
        } catch (ApiException e) {
            if (e.getCode() != 404) {
                throw e;
            }
            Genre newGenre = new Genre(genreDTO.getId(), genreDTO.getName());
            return genreDAO.create(newGenre);
        }
    }

    //Tjekker, om skuespilleren allerede er i databasen. Hvis ja, bliver skuespilleren hentet, hvis nej, bliver skuespilleren oprettet.
    private Actor getOrCreateActor(ActorDTO actorDTO) {
        try {
            return actorDAO.read(actorDTO.getId());
        } catch (ApiException e) {
            if (e.getCode() != 404) {
                throw e;
            }
            Actor newActor = new Actor(actorDTO.getId(), actorDTO.getName());
            return actorDAO.create(newActor);
        }
    }
}
