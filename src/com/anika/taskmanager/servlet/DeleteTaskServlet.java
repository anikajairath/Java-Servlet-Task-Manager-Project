package com.anika.taskmanager.servlet;

import com.anika.taskmanager.util.DBUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/DeleteTaskServlet")
public class DeleteTaskServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = (Integer) session.getAttribute("userId");
        String taskIdParam = request.getParameter("taskId");

        if (taskIdParam != null) {
            int taskId = Integer.parseInt(taskIdParam);
            
        try (Connection conn = DBUtil.getConnection()) {
        	String sql = "DELETE FROM Task WHERE task_id = ? and user_id = ?";
        	PreparedStatement ps = conn.prepareStatement(sql);
        	
        	ps.setInt(1, taskId);
        	ps.setInt(2, userId);
        	
        	ps.executeUpdate();
        }catch(SQLException e) {
        	e.printStackTrace();
        	}
        response.sendRedirect("TaskListServlet");
        }
       }
}