package Controller;

import Utils.DateAdapter;
import Entity.User;
import Service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@WebServlet("/api/users/*")
public class UserController extends HttpServlet {

    private UserService userService = new UserService();
    private Gson gson;

    @Override
    public void init() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(java.sql.Date.class, new DateAdapter());
        gson = gsonBuilder.create();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Entramos al post de Users.");
        BufferedReader reader = req.getReader();
        User user = gson.fromJson(reader, User.class);

        if (user == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user data");
            return;
        }

        User createdUser = userService.save(user);
        if (createdUser != null) {
            resp.setStatus(HttpServletResponse.SC_OK);
            writeResponse(resp, createdUser);
        } else {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to create user");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();

        if (path == null || "/".equals(path)) {
            handleGetAllUsers(resp);
        } else {
            handleGetUserById(req, resp);
        }
    }

    private void handleGetUserById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] parts = req.getPathInfo().split("/");
        if (parts.length < 2) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "User ID is missing");
            return;
        }

        Integer userId = Integer.valueOf(parts[1]);
        User user = userService.findById(userId);
        if (user != null) {
            writeResponse(resp, user);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
        }
    }

    private void handleGetAllUsers(HttpServletResponse resp) throws IOException {
        List<User> users = userService.getAllUsers();
        writeResponse(resp, users);
    }

    private void writeResponse(HttpServletResponse resp, Object data) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(data));
        out.flush();
    }
}
