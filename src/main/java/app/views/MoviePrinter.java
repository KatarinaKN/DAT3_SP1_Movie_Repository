package app.views;
import app.dtos.GenreDTO;
import app.dtos.MovieDTO;

import java.util.stream.Collectors;

public class MoviePrinter {

    public void printMovieDetails (MovieDTO movieDTO) {
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

}


