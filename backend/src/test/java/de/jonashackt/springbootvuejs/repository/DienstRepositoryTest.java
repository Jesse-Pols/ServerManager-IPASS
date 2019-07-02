package de.jonashackt.springbootvuejs.repository;

import de.jonashackt.springbootvuejs.domain.Dienst;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DienstRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DienstRepository diensten;

    //private User norbertSiegmund = new User("Norbert", "Siegmund");
    //private User jonasHecht = new User("Jonas", "Hecht");

    private Dienst dienst_1 = new Dienst("Dienst1", "Key", "Studenten");
    private Dienst dienst_2 = new Dienst("Dienst2", "Key", "Docenten,OP");

    @Before
    public void fillSomeDataIntoOurDb() {
        // Add new Users to Database
        entityManager.persist(dienst_1);
        entityManager.persist(dienst_2);
    }

    @Test
    public void testFindByLastName() throws Exception {
        // Search for specific User in Database according to lastname
        List<Dienst> dienstenWithLastNameSiegmund = diensten.findByLastName("Dienst1");

        assertThat(dienstenWithLastNameSiegmund, contains(dienst_1));
    }


    @Test
    public void testFindByFirstName() throws Exception {
        // Search for specific User in Database according to firstname
        List<Dienst> dienstenWithFirstNameJonas = diensten.findByFirstName("Dienst2");

        assertThat(dienstenWithFirstNameJonas, contains(dienst_2));
    }

}