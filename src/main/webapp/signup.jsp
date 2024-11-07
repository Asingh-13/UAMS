<!-- signup.jsp -->
<html>
<head>
    <title>Sign Up</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
    <h2>Sign Up</h2>
    <form action="SignUpServlet" method="post">
        <!-- Input fields for username and password -->
        Username: <input type="text" name="username" required><br>
        Password: <input type="password" name="password" required><br>
        <input type="submit" value="Sign Up">
    </form>
    <%-- Display error message if signup fails --%>
    <%
        String error = request.getParameter("error");
        if (error != null) {
            out.println("<p style='color:red'>" + error + "</p>");
        }
    %>
    </div>
</body>
</html>
