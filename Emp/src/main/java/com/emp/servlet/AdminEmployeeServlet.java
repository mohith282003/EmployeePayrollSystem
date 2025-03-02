package com.emp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

import com.emp.connection.DbConnection;
import com.emp.dao.EmpRegisterDao;

@WebServlet("/AdminEmployeeServlet")
public class AdminEmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // creating the list employees of type EmpRegisterDao
        List<EmpRegisterDao> employees = new ArrayList<>();
        //try with resources
        try (Connection connection = DbConnection.getConnection()) {
        	// query to select id, name, email, department
            String query = "SELECT id, name, email, department FROM Employee";
            // Creating the prepared statement object
            PreparedStatement stmt = connection.prepareStatement(query);
            // result set for storing the result obtained against the query
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                EmpRegisterDao emp = new EmpRegisterDao();
                emp.setId(rs.getInt("id"));
                emp.setName(rs.getString("name"));
                emp.setEmail(rs.getString("email"));
                emp.setDepartment(rs.getString("department"));

                employees.add(emp);
            }

            request.setAttribute("employeeList", employees);
            // to forward a request to another resource
            RequestDispatcher dispatcher = request.getRequestDispatcher("employeeDetails.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error fetching employees.");
        }
    }
}


