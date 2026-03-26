<html>
<head><title>Register</title></head>
<body>
<h2>User Registration</h2>
<form action="/TaskManager/RegisterServlet" method="post">
    Username: <input type="text" name="username" required><br><br>
    Password: <input type="password" name="password" required><br><br>
    <input type="submit" value="Register">
</form>

<% if(request.getParameter("error") != null) { %>
<p style="color:red;"><%= request.getParameter("error") %></p>
<% } %>
</body>
</html>