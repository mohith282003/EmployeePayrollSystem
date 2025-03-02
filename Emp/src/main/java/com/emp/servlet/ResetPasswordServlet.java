package com.emp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.emp.connection.DbConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // getting the id of the employee who want to reset the password
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("id");

        if (id == null) {
            response.sendRedirect("index.html");
            return;
        }

        // getting the password from the user
        String newPassword = request.getParameter("newPassword");

        // try with resources
        try (Connection connection = DbConnection.getConnection()) {
        	// query to update 
            String query = "UPDATE Employee SET password = ? WHERE id = ?";
            // creating the prepared statement object
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, newPassword); 
            stmt.setInt(2, id);
            
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                session.invalidate();  
                response.sendRedirect("adminLogin.jsp");
            } else {
                response.sendRedirect("ResetPassword.jsp");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("ResetPassword.jsp");
        }
    }
}
