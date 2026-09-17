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


    public List<Movie> sortByHighestRating(){
        List<Movie> movieList = readAll();

         return movieList.stream()
                 .limit(10)
                 .sorted(
                         Comparator.comparing(Movie::getAverageRating)
                                 .reversed())
                 .toList();
    }

    public List<Movie> sortByLowestRating(){
        List<Movie> movieList = readAll();

        return movieList.stream().
                limit(10)
                .sorted(
                        Comparator.comparing(Movie::getAverageRating)).
                toList();

    }
}
