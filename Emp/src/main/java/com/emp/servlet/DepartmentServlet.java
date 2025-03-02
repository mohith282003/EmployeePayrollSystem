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

@WebServlet("/DepartmentServlet")
public class DepartmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	// Get department from form
        String department = request.getParameter("department"); 
        // create list of employee of type EmpRegisterDao
        List<EmpRegisterDao> employees = new ArrayList<>();

        // using try with resources
        try (Connection connection = DbConnection.getConnection()) {
        	// query to select the employee on basis of department
            String query = "SELECT id, name, email, department FROM Employee WHERE department = ?";
            // create prepared statement object
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, department);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                EmpRegisterDao emp = new EmpRegisterDao();
                emp.setId(rs.getInt("id"));
                emp.setName(rs.getString("name"));
                emp.setEmail(rs.getString("email"));
                emp.setDepartment(rs.getString("department")); // Ensure department is set
                employees.add(emp);
            }

            System.out.println("Selected Department: " + department);
            System.out.println("Employees found: " + employees.size());

            // Setting the attributes correctly
            request.setAttribute("employeeList", employees);
            request.setAttribute("selectedDepartment", department); // This ensures JSP gets it

            RequestDispatcher dispatcher = request.getRequestDispatcher("empOperation.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
