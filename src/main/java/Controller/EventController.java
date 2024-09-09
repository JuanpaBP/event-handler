package Controller;

import Utils.DateAdapter;
import Entity.Event;
import Service.EventService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;


@WebServlet("/api/events/*")  // Anotación para mapear la URL a este servlet
public class EventController extends HttpServlet {

    private EventService eventService = new EventService();
    private Gson gson;

    @Override
    public void init() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(java.sql.Date.class, new DateAdapter());
        gson = gsonBuilder.create();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("Entrando a eventController");
        BufferedReader reader = req.getReader();
        Event event = gson.fromJson(reader, Event.class);

        Event createdEvent = eventService.save(event);
        if (createdEvent != null) {
            resp.setStatus(HttpServletResponse.SC_OK);
            writeResponse(resp, createdEvent);
        } else {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to create event");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = req.getPathInfo();
        if (path == null || "/".equals(path)) {
            handleGetAllEvents(resp);
        } else {
            handleGetEventById(req, resp);
        }
    }

    private void handleGetEventById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] parts = req.getPathInfo().split("/");
        if (parts.length < 2) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Event ID is missing");
            return;
        }

        Integer eventId = Integer.parseInt(parts[1]);
        Event event = eventService.getById(eventId);
        if (event != null) {
            writeResponse(resp, event);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Event not found");
        }
    }

    private void handleGetAllEvents(HttpServletResponse resp) throws IOException {
        List<Event> events = eventService.getAllEvents();
        writeResponse(resp, events);
    }

    private void writeResponse(HttpServletResponse resp, Object data) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(data));
        out.flush();
    }
}

