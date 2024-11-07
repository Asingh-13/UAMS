package dao;

import model.Software;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SoftwareDAO {
    // Method to retrieve all software
    public List<Software> getAllSoftware() throws SQLException, ClassNotFoundException {
        List<Software> softwareList = new ArrayList<>();
        String query = "SELECT * FROM software";

        try (Connection conn = DatabaseConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Software software = new Software();
                software.setId(rs.getInt("id"));
                software.setName(rs.getString("name"));
                software.setDescription(rs.getString("description"));
                software.setAccessLevel(rs.getString("access_levels"));
                softwareList.add(software);
            }
        }
        return softwareList;
    }

    // Method to add new software
    public void addSoftware(Software software) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO software (name, description, access_levels) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, software.getName());
            stmt.setString(2, software.getDescription());
            stmt.setString(3, software.getAccessLevel());
            stmt.executeUpdate();
        }
    }
}
