import configuration.DBManager;
import service.EventService;

public class Main {
    public static void main(String[] args) throws Exception {
        //configuration.initializeDatabase();
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
