package app.dao;

import app.config.HibernateTestConfig;
import app.entities.Movie;
import com.fasterxml.jackson.annotation.JacksonInject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MovieDAOTest {

    private final EntityManagerFactory emf = HibernateTestConfig.getEntityManagerFactory();

    JacksonInject.Value created;
    MovieDAO movieDAO;

    //Test Entities
    Movie movie1 = new Movie(
            1,
            "Blinkende Lygter",
            LocalDate.of(2000, 9, 29),
            9.2
    );

    Movie movie2 = new Movie(
            2,
            "I Kina Spiser De Hunde",
            LocalDate.of(1999, 9, 24),
            8.4
    );

    Movie movie3 = new Movie(
            3,
            "Klovn - The Movie",
            LocalDate.of(2010, 9, 24),
            7.6
    );


    @BeforeEach
    void beforeEach() {
        movieDAO = new MovieDAO(emf);
    }

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();

        //Clean users
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Movie").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @AfterAll
    void shutdown() {
        emf.close();
    }

    @Test
    void sortByHighestRating() {
        //create movies
        Movie created2 = movieDAO.create(movie2);
        Movie created3 = movieDAO.create(movie3);
        Movie created1 = movieDAO.create(movie1);

        //fetch movies
        Movie fetched1 = movieDAO.read(created1.getID());
        Movie fetched2 = movieDAO.read(created2.getID());
        Movie fetched3 = movieDAO.read(created3.getID());

        //get and sort movies
        List<Movie> movieList = movieDAO.sortByHighestRating();

        //test size
        assertThat(movieList, hasSize(3));

        Movie highest = movieList.get(0);
        Movie medium = movieList.get(1);
        Movie lowest = movieList.get(2);


        //Compare
        assertThat(fetched1.getAverageRating(), is(highest.getAverageRating()));
        assertThat(fetched2.getAverageRating(), is(medium.getAverageRating()));
        assertThat(fetched3.getAverageRating(), is(lowest.getAverageRating()));
    }

    @Test
    void sortByLowestRating() {
            //create movies
            Movie created2 = movieDAO.create(movie2);
            Movie created3 = movieDAO.create(movie3);
            Movie created1 = movieDAO.create(movie1);

            //fetch movies
            Movie fetched1 = movieDAO.read(created1.getID());
            Movie fetched2 = movieDAO.read(created2.getID());
            Movie fetched3 = movieDAO.read(created3.getID());


            //get and sort movies
            List<Movie> movieList = movieDAO.sortByLowestRating();

            //test size
            assertThat(movieList, hasSize(3));

            Movie lowest = movieList.get(0);
            Movie medium = movieList.get(1);
            Movie highest = movieList.get(2);


            //Compare
            assertThat(fetched3.getAverageRating(), is(lowest.getAverageRating()));
            assertThat(fetched2.getAverageRating(), is(medium.getAverageRating()));
            assertThat(fetched1.getAverageRating(), is(highest.getAverageRating()));
    }

    @Test
    void sortByHighestRating_emptyDatabase_returnsEmptyList() {
        List<Movie> movieList = movieDAO.sortByHighestRating();
        assertThat(movieList, is(empty()));
    }
}