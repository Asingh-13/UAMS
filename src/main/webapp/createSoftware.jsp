<!-- SoftwareServlet.jsp -->
<html>
<head>
    <title>Create Software</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
    <h2>Create Software</h2>

    <!-- Verify if the user is an Admin -->
    <%
        String role = (String) session.getAttribute("role");
        if (!"Admin".equals(role)) {
            response.sendRedirect("login.jsp?error=Unauthorized Access");
            return;
        }
    %>
<form action="SoftwareServlet" method="post">
    Software Name: <input type="text" name="name"><br>
    Description: <textarea name="description"></textarea><br>
    Access Levels: <input type="checkbox" name="access_levels" value="Read"> Read
    <input type="checkbox" name="access_levels" value="Write"> Write
    <input type="checkbox" name="access_levels" value="Admin"> Admin
    <input type="submit" value="Add Software">
</form>
</div>
</body>
</html>
