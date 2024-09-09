package DB;

import java.sql.*;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/EventHandler";
    private static final String USER = "root";  // Asegúrate de completar con tus credenciales
    private static final String PASSWORD = "Root21";  // Asegúrate de completar con tus credenciales

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            System.out.println("Connecting to database at " + URL);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    public static void initializeDatabase() {
        try (Connection conn = getConnection()) {
            System.out.println("Entrando a Initialize Database");
            if (!tableExists(conn, "events")) {
                createEventsTable(conn);
            }
            //if (!tableExists(conn, "users")) {
            //    System.out.println("Creando tabla users");
            //    createUsersTable(conn);
            //}
            createUsersTable(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean tableExists(Connection conn, String tableName) throws SQLException {
        System.out.println("Checking if table exists: " + tableName);
        DatabaseMetaData metaData = conn.getMetaData();
        try (var rs = metaData.getTables(null, null, tableName, null)) {
            boolean exists = rs.next();
            System.out.println("Table exists: " + exists);
            return exists;
        }
    }

    private static void createEventsTable(Connection conn) throws SQLException {
        String createTableSQL = "CREATE TABLE events (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "start_date DATE NOT NULL," +
                "end_date DATE NOT NULL," +
                "place VARCHAR(255) NOT NULL," +
                "price DECIMAL(10, 2) NOT NULL," +
                "is_paid BOOLEAN NOT NULL" +
                ")";
        System.out.println("Creating 'events' table...");
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("'events' table created.");
        }
    }

    public static void createUsersTable(Connection conn) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "first_name VARCHAR(255) NOT NULL," +
                "last_name VARCHAR(255) NOT NULL," +
                "date_of_birth DATE NOT NULL," +
                "id_type VARCHAR(255) NOT NULL," +
                "can_travel BOOLEAN NOT NULL" +
                ")";
        System.out.println("Creating 'users' table...");
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("'users' table created or already exists.");
        }
    }

    public static void closeConnections() {
        try {
            if (connection != null && !connection.isClosed()) {
                System.out.println("Closing database connection...");
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing database connection: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
