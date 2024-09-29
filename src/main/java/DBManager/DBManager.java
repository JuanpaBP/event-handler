package DBManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
    public void ExecuteQuery(String query, String url) throws SQLException {
        Connection connection = DriverManager.getConnection(url, "username", "Root21");
        Statement statement = connection.createStatement();
        statement.execute(query);
        connection.close();
    }
}
