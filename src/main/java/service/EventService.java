package service;

import configuration.DBManager;
import entity.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventService {

    private final DBManager dbManager;

    // Constructor que recibe el configuration
    public EventService(DBManager dbManager) {//TODO: ESTE CONSTRUCTOR EXISTE ASÍ POR QUE ERA LA UNICA FORMA QUE TENIA DE HACER ANDAR LOS TESTS.
        //Si logro hacer andar los tests sin necesidad de pasarle un configuration, hay que hacerlo.
        this.dbManager = dbManager;
    }

    public Event save(Event event) {
        String query = "INSERT INTO event (name, location, description, start_date, end_date, price) VALUES (?, ?, ?, ?, ?, ?)";
        Event eventResponse = null;

        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, event.getName());
            stmt.setString(2, event.getLocation());
            stmt.setString(3, event.getDescription());
            stmt.setDate(4, new java.sql.Date(event.getStartDate().getTime()));
            stmt.setDate(5, new java.sql.Date(event.getEndDate().getTime()));
            stmt.setDouble(6, event.getPrice());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        eventResponse = getById(generatedId);
                    }
                }
            }
            //dbManager.closeConnections();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventResponse;
    }

    public Event getById(int id) {
        String query = "SELECT * FROM event WHERE id = ?";
        Event event = null;

        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    event = new Event();
                    event.setId(rs.getInt("id"));
                    event.setName(rs.getString("name"));
                    event.setLocation(rs.getString("location"));
                    event.setDescription(rs.getString("description"));
                    event.setStartDate(rs.getDate("start_date"));
                    event.setEndDate(rs.getDate("end_date"));
                    event.setPrice(rs.getDouble("price"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return event;
    }

    public List<Event> findAll() throws SQLException {
        String query = "SELECT * FROM event";
        List<Event> eventList = new ArrayList<>();

        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Event event = new Event();
                    event.setId(rs.getInt("id"));
                    event.setName(rs.getString("name"));
                    event.setLocation(rs.getString("location"));
                    event.setDescription(rs.getString("description"));
                    event.setStartDate(rs.getDate("start_date"));
                    event.setEndDate(rs.getDate("end_date"));
                    event.setPrice(rs.getDouble("price"));
                    eventList.add(event); //Es como que me devuelve una bolsa llena de datos. Agarro los datos. Como que no sabe diferenciar si son de un objeto u otro.
                    //Así que como yo SI se cuantos campos son, cuando llego al ultimo campo, agrego el evento y fabrico otro.
                    //Fabrico un event. Los meto en la lista.
                    //Y sigo iterando hasta que no halla mas nada.
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventList;
    }

    public Event update(Event event) {
        String query = "UPDATE event SET name = ?, location = ?, description = ?, start_date = ?, end_date = ?, price = ? WHERE id = ?";
        Event eventResponse = null;

        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Establecer los parámetros de la consulta
            stmt.setString(1, event.getName());
            stmt.setString(2, event.getLocation());
            stmt.setString(3, event.getDescription());
            stmt.setDate(4, new java.sql.Date(event.getStartDate().getTime()));
            stmt.setDate(5, new java.sql.Date(event.getEndDate().getTime()));
            stmt.setDouble(6, event.getPrice());
            stmt.setInt(7, event.getId());

            //ESTO ES BASICAMENTE LO MISMO QUE EL SAVE, NADA MAS QUE LA QUERY ES DIFERENTE, Y LE AGREGO EL ID AL FINAL (por que no quiero pisar
            //todos los datos, usando el where).
            //La posición de parameterIndex es la posición del parametro de la query.

            int rowsUpdated = stmt.executeUpdate();
            //Mismo problema que con el create, el executeUpdate no devuelve el objeto. Así que tengo que buscarlo con el getById.
            if (rowsUpdated > 0) {
                eventResponse = getById(event.getId()); // Recuperar el objeto actualizado
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventResponse;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM event WHERE id = ?";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);//Aca le digo: De la consulta, el primer parametro, es la variable id.
            int rowsDeleted = stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true; //Si se ejecuto y no rompío (no me importa si existía o no el valor) devuelvo true. Por que se me canta.
    }

}
