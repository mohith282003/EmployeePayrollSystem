package com.emp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.emp.connection.DbConnection;
import com.emp.dao.EmpRegisterDao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/SalaryServlet")
public class SalaryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	// creating the salary list of type EmpRegisterDao
        List<EmpRegisterDao> salaryList = new ArrayList<>();

        // try with resources
        try (Connection connection = DbConnection.getConnection()) {
        	// query
        	String query = "SELECT e.id, e.name, IFNULL(a.workHours, 0) AS workHours, IFNULL(a.leaves, 0) AS leaves " +
                    "FROM Employee e " +
                    "LEFT JOIN EmployeeAttendance a ON e.id = a.emp_id"; 
        	// creating prepared statement object
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                EmpRegisterDao emp = new EmpRegisterDao();
                // setting the emp object with id , name , work hours , leaves and salary
                emp.setId(rs.getInt("id"));
                emp.setName(rs.getString("name"));

                int workHours = rs.getInt("workHours");
                int leaves = rs.getInt("leaves");
                double salary = calculateSalary(workHours, leaves);

                emp.setWorkHours(workHours);
                emp.setLeaves(leaves);
                emp.setSalary(salary);

                salaryList.add(emp);
            }

            request.setAttribute("salaryList", salaryList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("salaryDetails.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error fetching salary details.");
        }
    }

    // method to calculate salary
    private double calculateSalary(int workHours, int leaves) {
        double hourlyRate = 100;  // ₹100 per hour
        double deductionPerLeave = 500; // ₹500 per leave deduction
        return (workHours * hourlyRate) - (leaves * deductionPerLeave);
    }
}

