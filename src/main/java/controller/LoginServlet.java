package controller;

import dao.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get login form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        try {
            // Validate user credentials
            User user = userDAO.validateUser(username, password);
            if (user != null) {
                // Create a session and set user information
                HttpSession session = request.getSession();
                session.setAttribute("userId", user.getId());
                session.setAttribute("username", user.getUsername());
                session.setAttribute("role", user.getRole());

                // Redirect based on user role
                if ("Employee".equals(user.getRole())) {
                    response.sendRedirect("requestAccess.jsp");
                } else if ("Manager".equals(user.getRole())) {
                    response.sendRedirect("pendingRequests.jsp");
                } else if ("Admin".equals(user.getRole())) {
                    response.sendRedirect("adminDashboard.jsp");
                }
            } else {
                // Redirect to login page with error if credentials are invalid
                response.sendRedirect("login.jsp?error=Invalid credentials");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=An error occurred");
        }
    }
}
