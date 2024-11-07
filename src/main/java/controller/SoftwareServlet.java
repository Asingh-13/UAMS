package controller;

import dao.SoftwareDAO;
import model.Software;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/SoftwareServlet")
public class SoftwareServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ensure the user is an Admin
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        if (!"Admin".equals(role)) {
            response.sendRedirect("login.jsp?error=Unauthorized Access");
            return;
        }

        // Get form data
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String accessLevels = request.getParameter("accessLevels");

        // Create a new Software object
        Software software = new Software();
        software.setName(name);
        software.setDescription(description);
        software.setAccessLevel(accessLevels);

        // Use SoftwareDAO to add the software to the database
        SoftwareDAO softwareDAO = new SoftwareDAO();
        try {
            softwareDAO.addSoftware(software);
            response.sendRedirect("adminDashboard.jsp?success=Software added successfully");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("adminDashboard.jsp?error=Failed to add software");
        }
    }
}
