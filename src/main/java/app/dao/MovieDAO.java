package app.dao;

import app.entities.Movie;
import app.exceptions.ApiException;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.http.HttpStatus;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MovieDAO extends GenericDAO<Movie, Long> {
    public MovieDAO(EntityManagerFactory emf) {
        super(emf, Movie.class);
    }

    /**
     * Retrieves all movies and sorts them by average rating in ascending order.
     * @return a list of 10 movies, sorted from lowest to highest using average rating, empty list if none exist.
     */
    public List<Movie> sortByHighestRating(){
        List<Movie> movieList = readAll();

         return movieList.stream()
                 .limit(10)
                 .sorted(
                         Comparator.comparing(Movie::getAverageRating)
                                 .reversed())
                 .toList();
    }

    /**
     * Retrieves all movies and sorts them by average rating in descending order.
     * @return a list of 10 movies, sorted from highest to lowest using average rating, empty list if none exist.
     */
    public List<Movie> sortByLowestRating(){
        List<Movie> movieList = readAll();

        return movieList.stream().
                limit(10)
                .sorted(
                        Comparator.comparing(Movie::getAverageRating)).
                toList();

    }

    /**
     * Retrieves all movies and filters them using param
     * @param genre
     * @return a list of all movies that is equal to the param, emptyList if none exist
     * @throws ApiException if genre is null (status code 400)
     */
    public List<Movie> getMovieByGenre(String genre){
        if(genre == null){
            throw new ApiException(HttpStatus.BAD_REQUEST.value(), "Genre can't be null");
        }

        List<Movie> movieList = readAll();

        return movieList.stream().
                filter(m -> m.getGenres().stream()
                                .anyMatch(g -> g.getName().equalsIgnoreCase(genre))).
                toList();

    }
}
