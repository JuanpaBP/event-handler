import DBManager.DBManager;
import entity.Event;
import service.EventService;

import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        //DBManager.initializeDatabase();
        DBManager dbManager = new DBManager();
        dbManager.initializeDatabase();
        EventService eventService = new EventService(dbManager);
        //Event event = new Event(2,"update", "update", "update", new Date(2024,9,9), new Date(2024,9,10), 100.0);
        //eventService.update(event);
        //System.out.println(eventService.getById(2).getName());
        //List<Event> eventList = eventService.findAll();
        //for(Event x : eventList){
        //    System.out.println(x.getName());
        //}

        Thread.currentThread().join();

    }
}
