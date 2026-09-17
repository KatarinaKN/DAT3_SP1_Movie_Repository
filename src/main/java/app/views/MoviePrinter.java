package app.views;

public class MoviePrinter {
}

/*package app.view;

import app.dto.MovieDTO;

import java.util.List;

public class MoviePrinter {

    public void printMovieDetails (MovieDTO movieDTO) {
        if (movieDTO == null) {
            System.out.println("Ingen film at vise");
            return;
        }
        System.out.println("======== FILMOVERSIGT ========");
        System.out.println("Titel: " + movieDTO.getTitle());
        System.out.println("Udgivelsesår: " + movieDTO.getReleaseYear());
        System.out.println("Bedømmelse: " + movieDTO.getVoteAverage());
        System.out.println("Handling: " + movieDTO.getOverview());
        System.out.println("===================== ========");
    }

    public void printMovieList(String header, List<MovieDTO> movieList) {
        System.out.println("\n======== " + header + " ========");

        if (movieList == null || movieList.isEmpty()) {
            System.out.println("Ingen film fundet.");
            return;
        }

        for (int i = 0; i < movieList.size(); i++) {
            MovieDTO movie = movieList.get(i);
            System.out.println((i + 1) + ". " + movie.getTitle()
                    + " (" + movie.getReleaseYear() + ") - Rating: " + movie.getVoteAverage());
        }
        System.out.println("==========================================");
    }
}


 */