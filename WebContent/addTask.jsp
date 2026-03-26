<%@ page session="true" %>

<html>
<head>
    <title>Add Task</title>
</head>
<body>
<h2>Add New Task</h2>
<a href="TaskListServlet">Back to Task List</a>
<hr>

<form action="AddTaskServlet" method="post">

    Title: <input type="text" name="title" required><br><br>

    Description: <textarea name="description"></textarea><br><br>

    Status:
    <select name="status">
        <option value="Pending">Pending</option>
        <option value="Completed">Completed</option>
    </select><br><br>

    Priority:
    <select name="priority">
        <option value="Low">Low</option>
        <option value="Medium">Medium</option>
        <option value="High">High</option>
    </select><br><br>

    Due Date:
    <input type="date" name="due_date"><br><br>

    <button type="submit">Add Task</button>

</form>

</body>
</html>
