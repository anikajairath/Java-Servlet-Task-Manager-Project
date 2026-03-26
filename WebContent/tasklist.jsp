<%@ page import="java.util.*, com.anika.taskmanager.model.Task" %>
<%@ page session="true" %>

<html>
<head>
  <title>My Tasks</title>
</head>
<body>
<h2>Welcome, <%= session.getAttribute("username") %>!</h2>
<a href="LogoutServlet">Logout</a> | <a href="addTask.jsp">Add Task</a>
<hr>

<table border="1" cellpadding="5">
  <caption style="font-size: 20px; font-family: Bell Mt;"> Current Tasklist</caption>
  <tr>
    <th style="font-size: 19px;">Title</th>
    <th style="font-size: 19px;">Description</th>
    <th style="font-size: 19px;">Status</th>
    <th style="font-size: 19px;">Priority</th>
    <th style="font-size: 19px;">Due Date</th>
    <th style="font-size: 19px;">Actions</th>
  </tr>
  
  <%
    // Assume the servlet sets a List<Task> in request attribute "taskList"
    List<Task> tasks = (List<Task>) request.getAttribute("tasklist");
    if (tasks != null) {
        for(Task t : tasks){
  %>
    <tr>
      <td style="text-align:center; font-family: Bell MT;"><b><%= t.getTitle() %></b></td>
      <td style="color:green; font-family: Bell MT;"><%= t.getDescription() %></td>
      <td style= "font-family: Bell MT;"><i><%= t.getStatus() %></i></td>
      <td style= "font-family: Bell MT;"><%= t.getPriority() %></td>
      <td style= "font-family: Bell MT;"><%= t.getDueDate() %></td>
      <td>
        <a href="UpdateTaskServlet?taskId=<%= t.getTaskId() %>">Edit</a> |
        <a href="DeleteTaskServlet?taskId=<%= t.getTaskId() %>">Delete</a>
      </td>
    </tr>
  <%
        }
    } else {
  %>
    <tr><td colspan="6">No tasks at the moment!</td></tr>
  <%
    }
  %>
</table>
</body>
</html>