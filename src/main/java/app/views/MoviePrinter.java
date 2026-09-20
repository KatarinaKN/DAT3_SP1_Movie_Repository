package app.views;

import app.config.HibernateConfig;
import app.dao.MovieDAO;
import app.dao.GenreDAO;

import app.entities.Actor;
import app.entities.Genre;
import app.entities.Movie;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class MoviePrinter {
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    MovieDAO movieDAO = new MovieDAO(emf);
    GenreDAO genreDAO = new GenreDAO(emf);

    public void printAllMovies() {
        List<Movie> allMovies = movieDAO.readAll();
        System.out.println("Alle film:");
        for (Movie m : allMovies) {
            System.out.println("\t" + m.getTitle());
            System.out.println("\tInstruktør: " + (m.getDirector() != null ? m.getDirector().getName() : "Ukendt"));
            //Skuespillere, kommasepareret
            String actorNames = m.getActors().stream()
                    .map(Actor::getName)
                    .collect(Collectors.joining(", "));
            System.out.println("\tSkuespillere: " + (actorNames.isEmpty() ? "Ingen" : actorNames));
            System.out.println();
        }
    }

    public void printGenres() {
        List <Genre> genres = genreDAO.readAll();
        System.out.println("Alle genrer:");
        for (Genre g : genres) {
            System.out.println("\t" + g.getName());
        }
    }

    public void printFindMovieByKeyword() {
        String keyword = "sommer";
        List<Movie> moviesWithKeyword = movieDAO.getMovieByName(keyword);
        System.out.println("Film med " + keyword + ": ");
        for (Movie m : moviesWithKeyword) {
            System.out.println("\t" + m.getTitle());
        }
    }

    public void printFindMoviesByGenre() {
        String genre = "Thriller";
        List<Movie> moviesByGenre = movieDAO.getMovieByGenre(genre);
        System.out.println("Film i genre " + genre + ":");
        for (Movie m : moviesByGenre){
            System.out.println("\t" + m.getTitle());
        }
        System.out.println();
    }

    public void printTopTenLowestRating() {
        System.out.println("Top 10 laveste rating:");
        for (Movie m : movieDAO.sortByLowestRating()) {
            System.out.println("\t" + m.getTitle() + " - Rating: " + m.getAverageRating());
        }
        System.out.println();

        System.out.println("Top 10 højeste rating:");
        for (Movie m : movieDAO.sortByHighestRating()) {
            System.out.println("\t" + m.getTitle() + " - Rating: " + m.getAverageRating());
        }
        System.out.println();
    }


   /* public void printMovieDetails (MovieDTO movieDTO) {
        if (movieDTO == null) {
            System.out.println("Ingen film at vise");
            return;
        }

        //Hent udgivelsesår
        String releaseYear = "Ukendt";
        if (movieDTO.getReleaseDate() != null){
            releaseYear = String.valueOf(movieDTO.getReleaseDate().getYear());
        }

        //Formater genrer til kommasepareret
        String genreNames = "Ingen genrer angivet";
        // Check om genre-felt overhovedet er sendt med og om det er udfyldt (det var det ikke på alle film)
        if (movieDTO.getGenres() != null && !movieDTO.getGenres().isEmpty()) {
            genreNames = movieDTO.getGenres().stream()
                    .map(GenreDTO::getName)
                    .collect(Collectors.joining(", "));
        }

        System.out.println("======== FILMOVERSIGT ========");
        System.out.println("Titel: " + movieDTO.getTitle());
        System.out.println("Udgivelsesår: " + movieDTO.getReleaseDate());
        System.out.println("Bedømmelse: " + movieDTO.getAverageRating());
        System.out.println("Genre: " + genreNames);
        System.out.println("Popularitet: " + movieDTO.getPopularity());
        System.out.println("===================== ========");
    }

    */

}


