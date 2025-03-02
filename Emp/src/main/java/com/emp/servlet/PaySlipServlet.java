package com.emp.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.emp.connection.DbConnection;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet("/PaySlipServlet")
public class PaySlipServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	// Getting the name from the form
        String name = request.getParameter("name");

        if (name == null || name.trim().isEmpty()) {
            response.getWriter().println("Error: Employee name is missing.");
            return;
        }

        try (Connection connection = DbConnection.getConnection()) {
   
        	// query to select id,name,mail,department,work hours and leaves
            String query = "SELECT e.id, e.name, e.email, e.department, " +
                    "IFNULL(a.workHours, 0) AS workHours, IFNULL(a.leaves, 0) AS leaves " +
                    "FROM Employee e " +
                    "LEFT JOIN EmployeeAttendance a ON e.id = a.emp_id " +  
                    "WHERE e.name = ?";

            // creating the prepared statement objct
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int empId = rs.getInt("id");
                String email = rs.getString("email");
                String department = rs.getString("department");
                int workHours = rs.getInt("workHours");
                int leaves = rs.getInt("leaves");

                // Salary Calculation
                double hourlyRate = 100; 
                double deductionPerLeave = 500;
                double salary = (workHours * hourlyRate) - (leaves * deductionPerLeave);
                
                // Generate PDF
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=payslip_" + name + ".pdf");

                OutputStream out = response.getOutputStream();
                Document document = new Document();
                PdfWriter.getInstance(document, out);

                document.open();
                // attributes inside the pay slip
                document.add(new Paragraph("Employee Payslip", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20)));
                document.add(new Paragraph("------------------------------------------------------"));
                document.add(new Paragraph("Employee ID: " + empId));
                document.add(new Paragraph("Name: " + name));
                document.add(new Paragraph("Email: " + email));
                document.add(new Paragraph("Department: " + department));
                document.add(new Paragraph("Work Hours: " + workHours));
                document.add(new Paragraph("Leaves: " + leaves));
                document.add(new Paragraph("Salary: ₹" + salary));
                document.add(new Paragraph("------------------------------------------------------"));

                document.close();
                out.flush();
                out.close();
            } else {
                response.getWriter().println("Error: Employee not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error generating payslip: " + e.getMessage()); // ✅ FIX: Show actual error
        }
    }
}
