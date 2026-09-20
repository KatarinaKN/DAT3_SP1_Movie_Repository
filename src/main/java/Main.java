import app.AppRunner;
import app.services.EntityService;
import app.views.MoviePrinter;

public class Main {

    public static void main(String[] args) {
       /* AppRunner appRunner = new AppRunner();
        appRunner.run(); */

        //Gemmer fetchet API-data i egen database.
      /*  EntityService entityService = new EntityService();
        entityService.saveMoviesInDB(); */

        //Printer data fra egen database.
        MoviePrinter moviePrinter = new MoviePrinter();

     //   moviePrinter.printAllMovies();
     //   moviePrinter.printGenres();
     //   moviePrinter.printFindMovieByKeyword("sommer");
     // OBS! Virker ikke.   moviePrinter.printFindMoviesByGenre("Thriller");
     //   moviePrinter.printTopTenHighestRating();
     //   moviePrinter.printTopTenLowestRating();
    }
}