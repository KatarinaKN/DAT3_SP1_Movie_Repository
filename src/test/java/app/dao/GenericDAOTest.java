package app.dao;


import app.config.HibernateTestConfig;
import app.entities.Movie;
import app.exceptions.ApiException;
import com.fasterxml.jackson.annotation.JacksonInject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GenericDAOTest {
    private final EntityManagerFactory emf = HibernateTestConfig.getEntityManagerFactory();

    JacksonInject.Value created;
    MovieDAO movieDAO;

    //Test Entities
    Movie movie1 = new Movie(
            1,
            "Blinkende Lygter",
            LocalDate.of(2000, 9, 29),
            7.2
    );

    Movie movie2 = new Movie(
            2,
            "I Kina Spiser De Hunde",
            LocalDate.of(1999, 9, 24),
            7.4
    );

    Movie movie3 = new Movie(
            3,
            "Klovn - The Movie",
            LocalDate.of(2010, 9, 24),
            7.6
    );

    Movie missing = new Movie(

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
    void create() {
        //create movie
        Movie created = movieDAO.create(movie1);

        //Make sure its not null
        assertThat(created.getID(), notNullValue());
        Movie fetched = movieDAO.read(created.getID());

        assertThat(fetched.getTitle(), is("Blinkende Lygter"));
        assertThat(fetched.getID(), is(created.getID()));
    }

    @Test
    void readAll() {

        //create movies
        movieDAO.create(movie1);
        movieDAO.create(movie2);
        movieDAO.create(movie3);

        //get movies
        List<Movie> allMovies = movieDAO.readAll();

        //Test
        assertThat(allMovies, hasSize(3));
    }

    @Test
    void update() {
        //create movie
        movieDAO.create(movie1);
        assertThat(movie1.getID(), notNullValue());
        Movie fetchedMovie1 = movieDAO.read(movie1.getID());

        //Same id
        assertThat(fetchedMovie1.getID(), is(movie1.getID()));

        //Update user 1
        fetchedMovie1.setAverageRating(8);
        fetchedMovie1.setRelease_date(LocalDate.of(1998, 4, 27));
        fetchedMovie1.setTitle("Peter Plys");

        //Update
        movieDAO.update(fetchedMovie1);

        //Get updated user 1
        Movie fetchedUpdated = movieDAO.read(fetchedMovie1.getID());
        assertThat(fetchedUpdated.getID(), is(fetchedMovie1.getID()));

        //Compare user 1 updated to non updated
        //Not the same
        assertThat(fetchedMovie1, not(fetchedUpdated));
        //Same ID
        assertThat(fetchedMovie1.getID(), is(fetchedUpdated.getID()));
    }

    @Test
    void delete() {
        //create movie
        movieDAO.create(movie1);
        assertThat(movie1.getID(), notNullValue());
        Movie fetchedMovie1 = movieDAO.read(movie1.getID());

        boolean deleted = movieDAO.delete(fetchedMovie1.getID());

        assertThat(deleted, is(true));
        ApiException ex = assertThrows(ApiException.class, () -> movieDAO.read(movie1.getID()));
        assertThat(ex.getCode(), is(HttpStatus.NOT_FOUND.value()));
    }


    //Exceptions

    @Test
    void create_withNullMovie_throwsApiException() {
        ApiException ex = assertThrows(ApiException.class, () -> movieDAO.create(null));
        assertThat(ex.getCode(), is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    void read_withNullMovie_throwsApiException() {
        ApiException ex = assertThrows(ApiException.class, () -> movieDAO.read(null));
        assertThat(ex.getCode(), is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    void read_withMissingId_throwsApiException() {
        ApiException ex = assertThrows(ApiException.class, () -> movieDAO.read(5));
        assertThat(ex.getCode(), is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    void update_withNullMovie_throwsApiException() {
        ApiException ex = assertThrows(ApiException.class, () -> movieDAO.update(null));
        assertThat(ex.getCode(), is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    void delete_withNullId_throwsApiException() {
        ApiException ex = assertThrows(ApiException.class, () -> movieDAO.delete(null));
        assertThat(ex.getCode(), is(HttpStatus.BAD_REQUEST.value()));
    }

}

