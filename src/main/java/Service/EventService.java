package Service;
import DB.DatabaseConnection;
import Entity.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventService {

    public Event save(Event event) {
        String query = "INSERT INTO events (start_date, end_date, place, price, is_paid) VALUES (?, ?, ?, ?, ?)";
        Event eventResponse = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) { // Solicita las llaves generadas

            stmt.setDate(1, new java.sql.Date(event.getStartDate().getTime()));
            stmt.setDate(2, new java.sql.Date(event.getEndDate().getTime()));
            stmt.setString(3, event.getPlace());
            stmt.setBigDecimal(4, event.getPrice());
            stmt.setBoolean(5, event.isPaid());

            int affectedRows = stmt.executeUpdate(); // Ejecuta la inserción

            if (affectedRows > 0) {
                // Obtén el ID generado
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1); // Ajusta el tipo según tu definición
                        eventResponse = getById(generatedId); // Obtén el evento con el ID generado
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventResponse;
    }


    public Event getById(int id) {
        String query = "SELECT * FROM events WHERE id = ?";
        Event event = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    event = new Event();
                    event.setId(rs.getInt("id"));
                    event.setStartDate(rs.getDate("start_date"));
                    event.setEndDate(rs.getDate("end_date"));
                    event.setPlace(rs.getString("place"));
                    event.setPrice(rs.getBigDecimal("price"));
                    event.setPaid(rs.getBoolean("is_paid"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return event;
    }


    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String query = "SELECT * FROM events";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Event event = new Event();
                event.setStartDate(rs.getDate("start_date"));
                event.setEndDate(rs.getDate("end_date"));
                event.setPlace(rs.getString("place"));
                event.setPrice(rs.getBigDecimal("price"));
                event.setPaid(rs.getBoolean("is_paid"));

                events.add(event);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
    }

}
