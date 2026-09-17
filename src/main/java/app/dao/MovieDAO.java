package app.dao;

import app.entities.Movie;
import jakarta.persistence.EntityManagerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MovieDAO extends GenericDAO<Movie, Long> {
    public MovieDAO(EntityManagerFactory emf) {
        super(emf, Movie.class);
    }

    /**
     * Retrieves all movies and sorts them by average rating in ascending order.
     * @return a list of 10 movies, sorted from lowest to highest using average rating.
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
     * @return a list of 10 movies, sorted from highest to lowest using average rating.
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
     * @return a list of all movies that is equal to the param
     */
    public List<Movie> getMovieByGenre(String genre){
        List<Movie> movieList = readAll();

        return movieList.stream().
                filter(m -> m.getGenres().stream()
                                .anyMatch(g -> g.getName().equalsIgnoreCase(genre))).
                toList();

    }
}
