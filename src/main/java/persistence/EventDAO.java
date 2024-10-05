package persistence;

import configuration.HibernateConfig;
import entity.Event;
import jakarta.ws.rs.ProcessingException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class EventDAO {
    EntityManagerFactory emf = HibernateConfig.setup();
    EntityManager em = emf.createEntityManager();

    public void createEvent(Event event) {
        em.getTransaction().begin();
        em.persist(event);
        em.getTransaction().commit();
        em.close();
    }

    public Event getEventById(Integer id) {
        try {
            String query = "Select e from Event e where e.id = :id";
            TypedQuery<Event> typedQuery = em.createQuery(query, Event.class);
            typedQuery.setParameter("id", id);
            return typedQuery.getSingleResult(); //La query tiene la capacidad de auto ejecutarse.
        } catch (ProcessingException e) {
            throw e;
        } finally {
            em.close();
        }

    }

    public List<Event> getAllEvents() {
        try {
            String query = "Select e from Event e";
            TypedQuery<Event> typedQuery = em.createQuery(query, Event.class);
            return typedQuery.getResultList();
        } catch (ProcessingException e) {
            throw e;
        } finally {
            em.close();
        }

    }

}
