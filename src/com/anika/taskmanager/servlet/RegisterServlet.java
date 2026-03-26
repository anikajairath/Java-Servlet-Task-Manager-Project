package com.anika.taskmanager.servlet;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.anika.taskmanager.util.DBUtil;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "INSERT INTO users(username, password) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            System.out.println("\n"+username);
            System.out.println("\n"+password);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                resp.sendRedirect("login.jsp?msg=Registered Successfully");
            } else {
                resp.sendRedirect("register.jsp?error=Try again");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("register.jsp?error=Username already exists");
        }
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect("register.jsp");
    }
}