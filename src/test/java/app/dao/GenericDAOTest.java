package app.dao;


import app.config.HibernateTestConfig;
import app.entities.Movie;
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
            1L,
            "Blinkende Lygter",
            LocalDate.of(2000, 9, 29),
            7.2
    );

    Movie movie2 = new Movie(
            2L,
            "I Kina Spiser De Hunde",
            LocalDate.of(1999, 9, 24),
            7.4
    );

    Movie movie3 = new Movie(
            3L,
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
    void create() {
        //create movie
        Movie created = movieDAO.create(movie1);

        //Make sure its not null
        assertThat(created.getID(), notNullValue());
        Movie fetched = movieDAO.read(created.getID());

        assertThat(fetched.getTitle(), is("Blinkende Lygter"));

    }




}

