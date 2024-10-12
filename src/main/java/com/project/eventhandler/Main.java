package com.project.eventhandler;

import configuration.HibernateConfig;
import entity.Event;
import persistence.EventDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Event Management App is running!");

        /*// Cargar propiedades desde el archivo .env
        ConfigLoader.loadProperties();

        // Crear el EntityManagerFactory usando persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPersistenceUnit");

        // Aca meto CUANDO ANDE ESTO mi código

        Thread.currentThread().join();
*/

        System.out.println("Event Management App is running!");

        // Configura el EntityManagerFactory
        EntityManagerFactory emf = HibernateConfig.setup();

        // Aquí puedes utilizar el EntityManagerFactory según lo necesites
        EntityManager em = emf.createEntityManager();

        // Mantén el hilo en espera (si es necesario)

        EventDAO eventDAO = new EventDAO();
        //Event event = new Event(null,"create", "create", "create", new Date(2024,9,9), new Date(2024,9,10), 100.0);
        //Event event2 = new Event(1, "update3", "update3", "update3", new Date(2024, 10, 0), new Date(2024, 10, 10), 300.0);
        //eventDAO.createEvent(event);
        //System.out.println("Create:  " + event.getName());
        //Event eventResponseUpdate = eventDAO.updateEvent(event2);
        //System.out.println("Update:" + eventResponseUpdate.getName());
        //Event eventFindById = eventDAO.getEventById(1);
        //System.out.println("GetById:  " + eventFindById.getName());
        List<Event> getAllEvents = eventDAO.getAllEvents();
        System.out.println("GetAllEvents:  " + getAllEvents.size());
        System.out.println(eventDAO.deleteEvent(1));


        Thread.currentThread().join();


    }
}
