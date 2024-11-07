package controller;

import dao.RequestDAO;
import model.Request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/RequestServlet")
public class RequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user session information
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        // Get form data
        int softwareId = Integer.parseInt(request.getParameter("softwareId"));
        String accessType = request.getParameter("accessType");
        String reason = request.getParameter("reason");

        // Create a new request object
        Request accessRequest = new Request();
        accessRequest.setUserId(userId);
        accessRequest.setSoftwareId(softwareId);
        accessRequest.setAccessType(accessType);
        accessRequest.setReason(reason);
        accessRequest.setStatus("Pending");

        // Use RequestDAO to insert the request into the database
        RequestDAO requestDAO = new RequestDAO();
        try {
            requestDAO.addRequest(accessRequest);
            response.sendRedirect("requestAccess.jsp?success=Request submitted successfully");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("requestAccess.jsp?error=Failed to submit request");
        }
    }
}
