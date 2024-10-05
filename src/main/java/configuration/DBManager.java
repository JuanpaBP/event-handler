package configuration;

import java.sql.*;

public class DBManager {
    private String URL = "jdbc:mysql://localhost:3306/EventHandler";
    private String USER = "root";  // Asegúrate de completar con tus credenciales
    private String PASSWORD = "Root21";  // Asegúrate de completar con tus credenciales
    private Connection connection;

    public DBManager(String url, String user, String password) {
        this.URL = url;
        this.USER = user;
        this.PASSWORD = password;
    }
    public DBManager() {
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            System.out.println("Connecting to database at " + URL);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    public void initializeDatabase() {
        try (Connection conn = getConnection()) {
            System.out.println("Entrando a Initialize Database");
            if (!tableExists(conn, "event")) {
                createEventsTable(conn);
            }
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
        String createTableSQL = "CREATE TABLE event (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "location VARCHAR(255) NOT NULL," +
                "description TEXT," +
                "start_date DATE NOT NULL," +
                "end_date DATE NOT NULL," +
                "price DECIMAL(10, 2) NOT NULL," +

                ")";
        System.out.println("Creando tabla event, vamos que se puede papaaaa");
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Exitos totales creando tabla event.");
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
        System.out.println("Creando tabla Users, vamos que se puede papaaaa");
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Exitos totales creando tabla Users.");
        }
    }

    public  void closeConnections() {
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
