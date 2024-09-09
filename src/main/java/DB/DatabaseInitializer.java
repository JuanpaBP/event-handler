package DB;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

@WebListener
public class DatabaseInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DatabaseConnection.initializeDatabase();

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /*try {
            DatabaseConnection.closeConnections();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }
}

