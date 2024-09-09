import DB.DatabaseConnection;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {
        // Inicializar la base de datos
        DatabaseConnection.initializeDatabase();

        // Crear y configurar el servidor
        Server server = new Server(8081); // Cambia el puerto a 8081 u otro puerto disponible

        // Crear y configurar el contexto de la aplicación web
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        // Registrar el servlet con su URL de mapeo
        ServletHolder eventServlet = new ServletHolder("eventController", Controller.EventController.class);
        context.addServlet(eventServlet, "/api/events/*");

        ServletHolder userServlet = new ServletHolder("userController", Controller.UserController.class);
        context.addServlet(userServlet, "/api/users/*");

        // Configurar el servidor con el contexto
        server.setHandler(context);

        // Iniciar el servidor
        server.start();
        server.join();
    }
}
