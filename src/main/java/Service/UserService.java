package Service;

import DB.DatabaseConnection;
import Entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    public User save(User user) {
        String query = "INSERT INTO users (first_name, last_name, date_of_birth, id_type, can_travel) VALUES (?, ?, ?, ?, ?)";
        User userResponse = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setDate(3, new java.sql.Date(user.getDateOfBirth().getTime()));
            stmt.setString(4, user.getIdType());
            stmt.setBoolean(5, user.isCanTravel());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                // Recupera el ID generado automáticamente
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        Integer generatedId = generatedKeys.getInt(1); // Ajustado para Integer
                        userResponse = findById(generatedId);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userResponse;
    }


    public User findById(Integer id) {
        String selectQuery = "SELECT * FROM users WHERE id = ?";
        User user = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(selectQuery)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setDateOfBirth(rs.getDate("date_of_birth"));
                    user.setIdType(rs.getString("id_type"));
                    user.setCanTravel(rs.getBoolean("can_travel"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setDateOfBirth(rs.getDate("date_of_birth"));
                user.setIdType(rs.getString("id_type"));
                user.setCanTravel(rs.getBoolean("can_travel"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
