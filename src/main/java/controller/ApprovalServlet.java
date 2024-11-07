package controller;

import dao.RequestDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ApprovalServlet")
public class ApprovalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Verify that only Managers or Admins are making this request
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        if (!"Manager".equals(role) && !"Admin".equals(role)) {
            response.sendRedirect("login.jsp?error=Unauthorized Access");
            return;
        }

        // Get request parameters
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        String action = request.getParameter("action");

        // Use RequestDAO to update the request status
        RequestDAO requestDAO = new RequestDAO();
        try {
            if ("approve".equals(action)) {
                requestDAO.updateRequestStatus(requestId, "Approved");
            } else if ("reject".equals(action)) {
                requestDAO.updateRequestStatus(requestId, "Rejected");
            }
            response.sendRedirect("pendingRequests.jsp?success=Request updated successfully");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("pendingRequests.jsp?error=Failed to update request");
        }
    }
}
