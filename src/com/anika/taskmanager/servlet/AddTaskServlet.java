package com.anika.taskmanager.servlet;

import com.anika.taskmanager.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/AddTaskServlet")
public class AddTaskServlet extends HttpServlet {
	
	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        HttpSession session = request.getSession(false);

	        if (session == null || session.getAttribute("userId") == null) {
	            response.sendRedirect("login.jsp");
	            return;
	        }
	        int userId = (Integer) session.getAttribute("userId");

	        String title = request.getParameter("title");
	        String description = request.getParameter("description");
	        String status = request.getParameter("status");
	        String priority = request.getParameter("priority");
	        String dueDate = request.getParameter("due_date");
	        
	        try (Connection conn = DBUtil.getConnection()) {

	            String sql = "INSERT INTO task (title, description, status, priority, due_date, user_id) " +
	                         "VALUES (?, ?, ?, ?, ?, ?)";

	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setString(1, title);
	            ps.setString(2, description);
	            ps.setString(3, status);
	            ps.setString(4, priority);
	            
	            if (dueDate == null || dueDate.isEmpty()) {
	                ps.setNull(5, Types.DATE);
	            } else {
	                ps.setDate(5, Date.valueOf(dueDate));
	            }

	            ps.setInt(6, userId);

	            ps.executeUpdate();

	            response.sendRedirect("TaskListServlet");

	        } catch (SQLException e) {
	            e.printStackTrace();
	            response.sendRedirect("addTask.jsp?error=Database error");
	        }
	    }
	}

