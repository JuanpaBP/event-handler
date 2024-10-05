package persistence;

import configuration.HibernateConfig;
import entity.Event;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EventDAO {
    EntityManagerFactory emf = HibernateConfig.setup();
    EntityManager em = emf.createEntityManager();

    public void createEvent(Event event) {
        em.getTransaction().begin();
        em.persist(event);
        em.getTransaction().commit();
        em.close();
    }

}
