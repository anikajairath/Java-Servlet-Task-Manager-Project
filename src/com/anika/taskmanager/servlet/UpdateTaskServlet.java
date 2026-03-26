package com.anika.taskmanager.servlet;

import com.anika.taskmanager.model.Task;
import com.anika.taskmanager.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/UpdateTaskServlet")
public class UpdateTaskServlet extends HttpServlet {

    // Show the edit form (prefilled)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        int userId = (Integer) session.getAttribute("userId");

        String taskIdStr = request.getParameter("taskId");
        if (taskIdStr == null) {
            response.sendRedirect("TaskListServlet");
            return;
        }
        
        int taskId;
        try {
            taskId = Integer.parseInt(taskIdStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("TaskListServlet");
            return;
        }
        try (Connection conn = DBUtil.getConnection()) {
            // Only select the task if it belongs to this user
            String sql = "SELECT task_id, title, description, status, priority, due_date " +
                         "FROM task WHERE task_id = ? AND user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, taskId);
            ps.setInt(2, userId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Task task = new Task();
                task.setTaskId(rs.getInt("task_id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setStatus(rs.getString("status"));
                task.setPriority(rs.getString("priority"));
                task.setDueDate(rs.getDate("due_date")); // may be null

                request.setAttribute("task", task);
                request.getRequestDispatcher("updateTask.jsp").forward(request, response);
            } else {
                // task not found or doesn't belong to user
                response.sendRedirect("TaskListServlet");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("TaskListServlet");
        }
    }

    // Handle the update form submission
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        int userId = (Integer) session.getAttribute("userId");

        String taskIdStr = request.getParameter("taskId");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        String priority = request.getParameter("priority");
        String dueDate = request.getParameter("due_date");

        if (taskIdStr == null) {
            response.sendRedirect("TaskListServlet");
            return;
        }

        int taskId;
        try {
            taskId = Integer.parseInt(taskIdStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("TaskListServlet");
            return;
        }

        // Basic validation: require title (you can expand)
        if (title == null || title.trim().isEmpty()) {
            // send back to edit page with an error message (simple approach)
            request.setAttribute("error", "Title is required.");
            // reload task for display
            request.setAttribute("task", new Task() {{
                setTaskId(taskId);
                setTitle(title);
                setDescription(description);
                setStatus(status);
                setPriority(priority);
                // don't set dueDate here; the JSP will read request param if needed
            }});
            request.getRequestDispatcher("updateTask.jsp").forward(request, response);
            return;
        }

        try (Connection conn = DBUtil.getConnection()) {
            // Update only if the task belongs to this user (protects from editing others' tasks)
            String sql = "UPDATE task SET title = ?, description = ?, status = ?, priority = ?, due_date = ? " +
                         "WHERE task_id = ? AND user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, status);
            ps.setString(4, priority);

            if (dueDate == null || dueDate.isEmpty()) {
                ps.setNull(5, Types.DATE);
            } else {
                ps.setDate(5, Date.valueOf(dueDate)); // expects YYYY-MM-DD from input type=date
            }

            ps.setInt(6, taskId);
            ps.setInt(7, userId);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                // success
                response.sendRedirect("TaskListServlet");
            } else {
                // either task doesn't exist or didn't belong to user
                response.sendRedirect("TaskListServlet");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // on error show the edit form with message (simple fallback)
            request.setAttribute("error", "Database error while updating.");
            request.getRequestDispatcher("updateTask.jsp").forward(request, response);
        }
    }
}
