package dao;

import model.Request;
import model.RequestDetailsDTO;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO {
    public void addRequest(Request request) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO requests (user_id, software_id, access_type, reason, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, request.getUserId());
            stmt.setInt(2, request.getSoftwareId());
            stmt.setString(3, request.getAccessType());
            stmt.setString(4, request.getReason());
            stmt.setString(5, request.getStatus());
            stmt.executeUpdate();
        }
    }

    public List<RequestDetailsDTO> getPendingRequestDetails() throws SQLException, ClassNotFoundException {
        List<RequestDetailsDTO> pendingRequests = new ArrayList<>();
        String query = "SELECT r.id AS request_id, u.username AS employee_name, s.name AS software_name, " +
                "r.access_type, r.reason, r.status " +
                "FROM requests r " +
                "JOIN users u ON r.user_id = u.id " +
                "JOIN software s ON r.software_id = s.id " +
                "WHERE r.status = 'Pending'";

        try (Connection conn = DatabaseConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                RequestDetailsDTO requestDetails = new RequestDetailsDTO();
                requestDetails.setRequestId(rs.getInt("request_id"));
                requestDetails.setEmployeeName(rs.getString("employee_name"));
                requestDetails.setSoftwareName(rs.getString("software_name"));
                requestDetails.setAccessType(rs.getString("access_type"));
                requestDetails.setReason(rs.getString("reason"));
                requestDetails.setStatus(rs.getString("status"));
                pendingRequests.add(requestDetails);
            }
        }
        return pendingRequests;
    }


    // Method to update request status
    public void updateRequestStatus(int requestId, String status) throws SQLException, ClassNotFoundException {
        String query = "UPDATE requests SET status = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setInt(2, requestId);
            stmt.executeUpdate();
        }
    }
}
