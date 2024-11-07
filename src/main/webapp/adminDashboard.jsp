<%@ page import="dao.SoftwareDAO, model.Software, java.util.List" %>

<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
    <h2>Admin Dashboard</h2>

    <!-- Verify if the user is an Admin -->
    <%
        String role = (String) session.getAttribute("role");
        if (!"Admin".equals(role)) {
            response.sendRedirect("login.jsp?error=Unauthorized Access");
            return;
        }
    %>

    <!-- Navigation Links -->
    <nav>
        <a href="requestAccess.jsp">Request Access</a>
        | <a href="pendingRequests.jsp">Pending Requests</a>
        | <a href="adminDashboard.jsp">Admin Dashboard</a>
    </nav>

    <!-- Form to Add New Software -->
    <h3>Add New Software</h3>
    <form action="SoftwareServlet" method="post">
        Software Name: <input type="text" name="name" required><br>
        Description: <textarea name="description" required></textarea><br>
        Access Levels:
        <select name="accessLevels" required>
            <option value="Read">Read</option>
            <option value="Write">Write</option>
            <option value="Admin">Admin</option>
        </select><br>
        <input type="submit" value="Add Software">
    </form>

    <!-- List Existing Software -->
    <h3>Existing Software</h3>
    <%
        SoftwareDAO softwareDAO = new SoftwareDAO();
        List<Software> softwareList = softwareDAO.getAllSoftware();
    %>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Access Levels</th>
        </tr>
        <% for (Software software : softwareList) { %>
            <tr>
                <td><%= software.getName() %></td>
                <td><%= software.getDescription() %></td>
                <td><%= software.getAccessLevel() %></td>
            </tr>
        <% } %>
    </table>
    </div>
</body>
</html>
