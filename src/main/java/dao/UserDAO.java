package dao;

import model.User;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    // Method to add a new user
    public void addUser(User user) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.executeUpdate();
        }
    }

    // Method to retrieve a user by username
    public User getUserByUsername(String username) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM users WHERE username = ?";
        User user = null;

        try (Connection conn = DatabaseConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            }
        }
        return user;
    }

    public User validateUser(String username, String password) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        User user = null;

        try (Connection conn = DatabaseConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            }
        }
        return user;
    }

}
