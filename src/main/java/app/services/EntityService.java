package app.services;

import app.dtos.*;
import app.entities.Actor;
import app.entities.Director;
import app.entities.Genre;
import app.entities.Movie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EntityService {

    private final MovieService movieService = new MovieService();
    private final List<MovieDTO> movieDTOS = movieService.getMovies();

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
            Genre genre = new Genre(genreDTO.getId(), genreDTO.getName());
            genres.add(genre);
        }
        return genres;
    }

    private Set<Actor> convertToActorEntities(MovieDTO movieDTO) {
        List<ActorDTO> actorDTOS = movieDTO.getCredits().getCast();
        Set<Actor> actors = new HashSet<>();
        for (ActorDTO actorDTO : actorDTOS) {
            Actor actor = new Actor(actorDTO.getId(), actorDTO.getName());
            actors.add(actor);
        }
        return actors;
    }

    private Director convertToDirectorEntity(MovieDTO movieDTO) {
        List<CrewMemberDTO> crewMemberDTOS = movieDTO.getCredits().getCrew();
        int directorDTOId = crewMemberDTOS.stream()
                .filter(crewMemberDTO -> "Director".equals(crewMemberDTO.getJob()))
                .mapToInt(crewMemberDTO -> crewMemberDTO.getId()).sum();

        String directorDTOName = crewMemberDTOS.stream()
                .filter(crewMemberDTO -> "Director".equals(crewMemberDTO.getJob()))
                .map(crewMemberDTO -> crewMemberDTO.getName()).toString();

        return new Director(directorDTOId, directorDTOName);
    }
}
