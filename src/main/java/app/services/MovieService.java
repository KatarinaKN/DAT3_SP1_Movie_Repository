package app.services;

import app.dtos.MovieDTO;
import app.dtos.MovieIdDTO;
import app.dtos.MovieSearchResultDTO;

import java.util.ArrayList;
import java.util.List;

public class MovieService {

    private final APIService apiService = new APIService();

    //Henter id på danske film fra de sidste fem år.
    private List<MovieIdDTO> getMovieIds() {
        List<MovieIdDTO> movieIds = new ArrayList<>();

        int page = 1;
        int totalPages = 1;

        while (page <= totalPages) {
            System.out.println("Henter side " + page + " af " + totalPages);
            String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + System.getenv("API_KEY")
                    + "&primary_release_date.gte=2021-09-14"
                    + "&with_origin_country=DK"
                    + "&page=" + page;

            MovieSearchResultDTO result = apiService.fetchAndConvert(url, MovieSearchResultDTO.class);

            movieIds.addAll(result.getResults());
            totalPages = result.getTotalPages();
            page++;
        }

        return movieIds;
    }

    //Henter data på film ud fra id.
    public List<MovieDTO> getMovies() {
        List<MovieDTO> allMovies = new ArrayList<>();
        List<MovieIdDTO> movieIdDTOS = getMovieIds();

        for (MovieIdDTO movieId : movieIdDTOS) {
            //For-loop sat ind for at teste, om vi kan skrive tre sider ud. Programmet crashede, da vi prøvede at køre
            //alle 85 sider med data
            // for (int i = 1; i < 3; i++) {
            int id = movieId.getId();

            String url = "https://api.themoviedb.org/3/movie/" + id + "?api_key=" + System.getenv("API_KEY")
                    + "&append_to_response=credits";

            MovieDTO result = apiService.fetchAndConvert(url, MovieDTO.class);
            allMovies.add(result);
        }

        return allMovies;
    }
}