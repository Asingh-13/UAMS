<!-- login.jsp -->
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
    <h2>Login</h2>
    <form action="LoginServlet" method="post">
        Username: <input type="text" name="username" required><br>
        Password: <input type="password" name="password" required><br>
        <input type="submit" value="Login">
    </form>

    <%-- Display error message if login fails --%>
    <%
        String error = request.getParameter("error");
        if (error != null) {
            out.println("<p style='color:red'>" + error + "</p>");
        }
    %>
 </div>
</body>
</html>
