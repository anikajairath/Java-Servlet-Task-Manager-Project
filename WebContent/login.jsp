<html>
<head><title>Login</title></head>
<body>
<h2>Task Manager Login</h2>
<form action="/TaskManager/LoginServlet" method="post">
    <i>Username:</i> <input type="text" name="username" required><br><br>
    <i>Password:</i> <input type="password" name="password" required><br><br>
    <input type="submit" value="Login">
</form>

<% if(request.getParameter("msg") != null) { %>
<p style="color:green;"><%= request.getParameter("msg") %></p>
<% } %>
<% if(request.getParameter("error") != null) { %>
<p style="color:red;"><%= request.getParameter("error") %></p>
<% } %>
</body>
</html>