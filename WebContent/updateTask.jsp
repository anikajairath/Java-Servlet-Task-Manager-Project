<%@ page import="com.anika.taskmanager.model.Task" %>
<%@ page session="true" %>
<%
    Task task = (Task) request.getAttribute("task");
    String error = (String) request.getAttribute("error");
%>
<html>
<head>
    <title>Edit Task</title>
</head>
<body>
<h2>Edit Task</h2>
<a href="TaskListServlet">Back to Task List</a>
<hr>

<% if (error != null) { %>
    <div style="color: red;"><%= error %></div>
<% } %>

<form action="UpdateTaskServlet" method="post">
    <input type="hidden" name="taskId" value="<%= (task != null ? task.getTaskId() : "") %>">

    Title: <input type="text" name="title"
                  value="<%= (task != null && task.getTitle() != null ? task.getTitle() : "") %>" required><br><br>

    Description:<br>
    <textarea name="description" rows="4" cols="50"><%= (task != null && task.getDescription() != null ? task.getDescription() : "") %></textarea><br><br>

    Status:
    <select name="status">
        <option value="Pending" <%= (task != null && "Pending".equals(task.getStatus()) ? "selected" : "") %>>Pending</option>
        <option value="Completed" <%= (task != null && "Completed".equals(task.getStatus()) ? "selected" : "") %>>Completed</option>
    </select><br><br>

    Priority:
    <select name="priority">
        <option value="Low" <%= (task != null && "Low".equals(task.getPriority()) ? "selected" : "") %>>Low</option>
        <option value="Medium" <%= (task != null && "Medium".equals(task.getPriority()) ? "selected" : "") %>>Medium</option>
        <option value="High" <%= (task != null && "High".equals(task.getPriority()) ? "selected" : "") %>>High</option>
    </select><br><br>

    Due Date:
    <input type="date" name="due_date"
           value="<%= (task != null && task.getDueDate() != null ? task.getDueDate().toString() : "") %>"><br><br>

    <button type="submit">Update Task</button>
</form>

</body>
</html>
