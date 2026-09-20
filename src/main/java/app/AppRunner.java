package app;

import app.dtos.MovieDTO;
import app.services.MovieService;
import app.views.MoviePrinter;


import java.util.List;

public class AppRunner {

    public void run() {
        MovieService movieService = new MovieService();
        MoviePrinter printer = new MoviePrinter();
        List<MovieDTO> movies = movieService.getMovies();


       // printer.printFindMoviesByGenre();
       // printer.printAllMovies();
        printer.printTopTenLowestRating();

            /*
        //Test af print af tre film
        int count = 0;
        for (MovieDTO movie : movies) {
            printer.printMovieDetails(movie);
            count++;

            if (count==3) {
                break;
            }
        }
             */
    }
}
