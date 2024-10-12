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

    public Event createEvent(Event event) {
        try {
            em.getTransaction().begin();
            System.out.println("Event id: + " + String.valueOf(event.getId()));
            em.persist(event);
            //Event managedEvent = em.merge(event);
            em.getTransaction().commit();
            return event;
        } catch (ProcessingException e) {
            throw e;
        } finally {
            em.close();
        }
    }

    public Event getEventById(Integer id) {
        EntityManager em = emf.createEntityManager(); // TODO: Crea un nuevo EntityManager Esto esta hecho así por que sino la transaccion termina??? y no la puedo usar en el update y delete.
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

    public Event updateEvent(Event event) {
        EntityManager em = emf.createEntityManager();//TODO: Este lo pude hacer andar sin esto, a diferencia del delete. Lo dejo por uniformidad. ¿Por que tengo que hacer esto?
        //Si el EntityManager es recomendable crear uno nuevo cada vez que se llama a un método, por que lo creamos global entonces?
        try {
            Event eventInBD = getEventById(event.getId());
            eventInBD.setName(event.getName());
            eventInBD.setDescription(event.getDescription());
            eventInBD.setLocation(event.getLocation());
            eventInBD.setStartDate(event.getStartDate());
            eventInBD.setEndDate(event.getEndDate());
            eventInBD.setPrice(event.getPrice());


            em.getTransaction().begin();
            em.merge(eventInBD);
            em.getTransaction().commit();
            return eventInBD;

        } catch (ProcessingException e) {
            throw e;
        } finally {
            em.close();
        }

    }

    public String deleteEvent(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {

            em.getTransaction().begin();
            Event eventToDelete = em.find(Event.class, id); // Busca el evento directamente
            if (eventToDelete != null) {
                em.remove(eventToDelete);
            } else {
                return "Evento no encontrado"; // Manejo si el evento no existe
            }

            em.getTransaction().commit();
            return "Evento borrado con éxito";
        } catch (ProcessingException e) {
            throw e;
        } finally {
            em.close();
        }
    }

}
