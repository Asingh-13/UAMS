<%@ page import="dao.RequestDAO, model.RequestDetailsDTO, java.util.List" %>

<html>
<head>
    <title>Pending Requests</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
    <h2>Pending Access Requests</h2>

    <%
        String role = (String) session.getAttribute("role");
        if (role == null) {
            // No user is logged in; redirect to login page
            response.sendRedirect("login.jsp?error=Please login first");
            return;
        } else if (!"Manager".equals(role) && !"Admin".equals(role)) {
            // If user is not a Manager or Admin, deny access
            response.sendRedirect("requestAccess.jsp?error=Unauthorized Access");
            return;
         }
    %>

    <nav>
        <a href="requestAccess.jsp">Request Access</a>
        | <a href="pendingRequests.jsp">Pending Requests</a>
        <% if ("Admin".equals(role)) { %>
            | <a href="adminDashboard.jsp">Admin Dashboard</a>
        <% } %>
    </nav>

    <!-- Fetch pending requests with additional details -->
    <%
        RequestDAO requestDAO = new RequestDAO();
        List<RequestDetailsDTO> pendingRequests = requestDAO.getPendingRequestDetails();
    %>

    <table border="1">
        <tr>
            <th>Employee Name</th>
            <th>Software</th>
            <th>Access Type</th>
            <th>Reason</th>
            <th>Actions</th>
        </tr>
        <% for (RequestDetailsDTO req : pendingRequests) { %>
            <tr>
                <td><%= req.getEmployeeName() %></td>
                <td><%= req.getSoftwareName() %></td>
                <td><%= req.getAccessType() %></td>
                <td><%= req.getReason() %></td>
                <td>
                    <form action="ApprovalServlet" method="post" style="display:inline;">
                        <input type="hidden" name="requestId" value="<%= req.getRequestId() %>">
                        <button type="submit" name="action" value="approve">Approve</button>
                        <button type="submit" name="action" value="reject">Reject</button>
                    </form>
                </td>
            </tr>
        <% } %>
    </table>
    </div>
</body>
</html>
