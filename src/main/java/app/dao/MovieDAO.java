package app.dao;

import app.entities.Movie;
import app.exceptions.ApiException;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.http.HttpStatus;

import java.util.Comparator;
import java.util.List;

public class MovieDAO extends GenericDAO<Movie, Integer> {
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
                 .sorted(
                         Comparator.comparing(Movie::getAverageRating)
                                 .reversed())
                 .limit(10)
                 .toList();
    }

    /**
     * Retrieves all movies and sorts them by average rating in descending order.
     * @return a list of 10 movies, sorted from highest to lowest using average rating, empty list if none exist.
     */
    public List<Movie> sortByLowestRating(){
        List<Movie> movieList = readAll();

        return movieList.stream()
                .sorted(
                        Comparator.comparing(Movie::getAverageRating))
                .limit(10)
                .toList();

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


    /**
     * Find a movie based on the movie title.
     * @param movieName the movie title you want to search for
     * @return a list of movies, emptyList if none exist
     * @throws ApiException if movieName is null (status code 400)
     */
    public List<Movie> getMovieByName(String movieName){
        if(movieName == null){
            throw new ApiException(HttpStatus.BAD_REQUEST.value(), "Search bar can't be null");
        }

        //Get all movies
        List<Movie> movieList = readAll();

        //Streams list and filter it
        return movieList.stream()
                //for each movie, check if equal to param, turn lowercase
                .filter(m -> m.getTitle().toLowerCase().contains(movieName.toLowerCase()))
                //return result
                .toList();

    }
}
