package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection initializeDatabase() throws SQLException, ClassNotFoundException {

        String dbURL = "jdbc:postgresql://localhost:5432/uams_db";
        String dbUsername = "postgres";
        String dbPassword = "root";

        // Load the PostgreSQL driver
        Class.forName("org.postgresql.Driver");

        // Establish and return a connection
        return DriverManager.getConnection(dbURL, dbUsername, dbPassword);
    }
}

