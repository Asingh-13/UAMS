<%@ page import="dao.SoftwareDAO, model.Software" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>Request Access</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
    <h2>Request Access to Software</h2>

    <!-- Retrieve the software list using SoftwareDAO -->
    <%
        SoftwareDAO softwareDAO = new SoftwareDAO();
        List<Software> softwareList = softwareDAO.getAllSoftware();
    %>

    <form action="RequestServlet" method="post">
        <!-- Dropdown for selecting software -->
        Software:
        <select name="softwareId" required>
            <option value="">Select Software</option>
            <% for (Software software : softwareList) { %>
                <option value="<%= software.getId() %>"><%= software.getName() %> - <%= software.getDescription() %></option>
            <% } %>
        </select><br>

        <!-- Dropdown for selecting access level -->
        Access Type:
        <select name="accessType" required>
            <option value="Read">Read</option>
            <option value="Write">Write</option>
            <option value="Admin">Admin</option>
        </select><br>

        <!-- Text area for request reason -->
        Reason for Request:
        <textarea name="reason" required></textarea><br>

        <input type="submit" value="Submit Request">
    </form>
    </div>
</body>
</html>
