package app;

import app.services.MovieService;
import app.views.MoviePrinter;

public class AppRunner {

    public void run() {
        MovieService movieService = new MovieService();
        MoviePrinter printer = new MoviePrinter();
        //Nedenstående fetcher data fra TMDb hver gang, applikation startes
       // List<MovieDTO> movies = movieService.getMovies();


      // printer.printFindMoviesByGenre();
       // printer.printAllMovies();
        //printer.printTopTenLowestRating();
        printer.printFindMovieByKeyword();

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
