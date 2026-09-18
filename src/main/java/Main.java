import app.services.MovieService;

public class Main {
    static void main() {

        MovieService movieService = new MovieService();

        System.out.println(movieService.getMovies());

    }
}
