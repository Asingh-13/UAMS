package controller;

import dao.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data from signup.jsp
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = "Employee"; // Default role for self-registration

        // Create a new User object
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);

        // Use UserDAO to add the user to the database
        UserDAO userDAO = new UserDAO();
        try {
            userDAO.addUser(user);
            response.sendRedirect("login.jsp"); // Redirect to login page after successful signup
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("signup.jsp?error=Signup failed"); // Redirect back to signup page if there's an error
        }
    }
}
