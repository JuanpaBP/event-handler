package testsNuevos.persistence;

import entity.Event;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import persistence.EventDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.SQLException;

import static org.mockito.Mockito.doNothing;
import static org.testng.AssertJUnit.assertNotNull;

public class EventDaoTest {

    private EventDAO eventDAO;
    @Mock
    EntityManagerFactory emf;
    @Mock
    EntityManager em;

    Event myEvent;
    java.sql.Date startDate = new java.sql.Date(2024, 11, 10);
    java.sql.Date endDate = new java.sql.Date(2024, 12, 12);

    @BeforeMethod
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        eventDAO = new EventDAO();

        myEvent = new Event(1, "Recital las pastillas", "Córdoba", "Recital", startDate, endDate, 10000.0);

    }

    @Test
    public void shouldCreateEventSuccessfully() {

        doNothing().when(em).getTransaction().begin();
        doNothing().when(em).persist(myEvent);
        doNothing().when(em).getTransaction().commit();
        myEvent = eventDAO.createEvent(myEvent);
        assertNotNull(myEvent);
        //assertEquals(myEvent, eventDAO.createEvent(myEvent));

    }

}
