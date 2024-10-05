import configuration.DBManager;
import entity.Event;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.EventService;

import java.sql.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class EventServiceTest {

    @Mock
    private DBManager dbManager;

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    private EventService eventService;

    @BeforeMethod
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        eventService = new EventService(dbManager);

        // Simular la conexión
        when(dbManager.getConnection()).thenReturn(mockConnection);
    }

    @Test
    public void testGetById() throws SQLException {
        int eventId = 1;

        Event event = new Event();
        event.setId(eventId);
        event.setName("Loolapaloza");
        event.setLocation("Córdoba");
        event.setDescription("evento random");
        event.setStartDate(Date.valueOf("2024-08-08"));
        event.setEndDate(Date.valueOf("2024-08-09"));
        event.setPrice(100.00);

        // Simular el PreparedStatement
        when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement);

        // Simular el ResultSet
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id")).thenReturn(eventId);
        when(mockResultSet.getString("name")).thenReturn(event.getName());
        when(mockResultSet.getString("location")).thenReturn(event.getLocation());
        when(mockResultSet.getString("description")).thenReturn(event.getDescription());
        when(mockResultSet.getDate("start_date")).thenReturn(event.getStartDate());
        when(mockResultSet.getDate("end_date")).thenReturn(event.getEndDate());
        when(mockResultSet.getDouble("price")).thenReturn(event.getPrice());

        // Ejecutar el método
        Event actualEvent = eventService.getById(eventId);

        // Validar
        assertNotNull(actualEvent);
        assertEquals(actualEvent.getId(), eventId);
        assertEquals(actualEvent.getName(), event.getName());
        assertEquals(actualEvent.getLocation(), event.getLocation());
        assertEquals(actualEvent.getDescription(), event.getDescription());
        assertEquals(actualEvent.getStartDate(), event.getStartDate());
        assertEquals(actualEvent.getEndDate(), event.getEndDate());
        assertEquals(actualEvent.getPrice(), event.getPrice());
    }

   /* @Test
    public void testFindAll() throws SQLException {

        //when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement);Con esta linea le test se clava????
        //Sin la linea, se rompe.
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false); // Dos filas, luego fin

        // Primer evento
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("name")).thenReturn("Loolapaloza");
        when(mockResultSet.getString("location")).thenReturn("Córdoba");
        when(mockResultSet.getString("description")).thenReturn("evento random");
        when(mockResultSet.getDate("start_date")).thenReturn(Date.valueOf("2024-08-08"));
        when(mockResultSet.getDate("end_date")).thenReturn(Date.valueOf("2024-08-09"));
        when(mockResultSet.getDouble("price")).thenReturn(100.00);

        // Segundo evento
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id")).thenReturn(2);
        when(mockResultSet.getString("name")).thenReturn("Cosquín Rock");
        when(mockResultSet.getString("location")).thenReturn("Córdoba");
        when(mockResultSet.getString("description")).thenReturn("otro evento random");
        when(mockResultSet.getDate("start_date")).thenReturn(Date.valueOf("2024-09-01"));
        when(mockResultSet.getDate("end_date")).thenReturn(Date.valueOf("2024-09-02"));
        when(mockResultSet.getDouble("price")).thenReturn(150.00);

        // Ejecutar el método que obtiene la lista de eventos
        List<Event> events = eventService.findAll();

        assertNotNull(events);
        assertEquals(events.size(), 2);

        Event firstEvent = events.get(0);
        assertEquals(firstEvent.getId(), 1);
        assertEquals(firstEvent.getName(), "Loolapaloza");

        Event secondEvent = events.get(1);
        assertEquals(secondEvent.getId(), 2);
        assertEquals(secondEvent.getName(), "Cosquín Rock");


    }*/





    /*@Test
    public void testSave() throws SQLException {
        // Preparar el evento que se va a guardar
        Event eventToSave = new Event();
        eventToSave.setName("Loolapaloza");
        eventToSave.setLocation("Córdoba");
        eventToSave.setDescription("evento random");
        eventToSave.setStartDate(Date.valueOf("2024-08-08"));
        eventToSave.setEndDate(Date.valueOf("2024-08-09"));
        eventToSave.setPrice(100.00);

        // Simular el PreparedStatement para la inserción
        when(mockConnection.prepareStatement(any(String.class), any(int.class))).thenReturn(mockPreparedStatement);

        // Simular la ejecución del PreparedStatement
        when(mockPreparedStatement.executeUpdate()).thenReturn(1); // Simula que se insertó 1 fila
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt(1)).thenReturn(1); // Simula que el ID generado es 1

        // Simular la configuración de los parámetros en el PreparedStatement
        mockPreparedStatement.setString(1, eventToSave.getName());
        mockPreparedStatement.setString(2, eventToSave.getLocation());
        mockPreparedStatement.setString(3, eventToSave.getDescription());
        mockPreparedStatement.setDate(4, eventToSave.getStartDate());
        mockPreparedStatement.setDate(5, eventToSave.getEndDate());
        mockPreparedStatement.setDouble(6, eventToSave.getPrice());

        // Ejecutar el método
        Event savedEvent = eventService.save(eventToSave);

        // Validar
        assertNotNull(savedEvent);
        assertEquals(savedEvent.getId(), 1);
        assertEquals(savedEvent.getName(), eventToSave.getName());
        assertEquals(savedEvent.getLocation(), eventToSave.getLocation());
        assertEquals(savedEvent.getDescription(), eventToSave.getDescription());
        assertEquals(savedEvent.getStartDate(), eventToSave.getStartDate());
        assertEquals(savedEvent.getEndDate(), eventToSave.getEndDate());
        assertEquals(savedEvent.getPrice(), eventToSave.getPrice());
    }*/
    //TODO: No puedo hacer andar el test de save.


}
