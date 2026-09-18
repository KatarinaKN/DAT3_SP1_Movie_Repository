import app.AppRunner;
import app.config.HibernateConfig;
import app.dao.GenericDAO;
import app.entities.Movie;
import app.services.EntityService;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class Main {

    public static void main(String[] args) {
      //  AppRunner appRunner = new AppRunner();
      //  appRunner.run();

        EntityService entityService = new EntityService();
        List<Movie> movieList = entityService.convertToMovieEntities();

       // System.out.println(movieList.size());

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

        GenericDAO genericDAO = new GenericDAO(emf, Movie.class);

        for (Movie movie : movieList) {
            genericDAO.create(movie);
        }


    }
}