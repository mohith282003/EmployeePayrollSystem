package com.emp.servlet;

import java.io.IOException;
import java.sql.SQLException;
import com.emp.dao.EmpDao;
import com.emp.dao.EmpRegisterDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/EmpRegisterServlet")
public class EmpRegisterServlet extends HttpServlet {
	EmpDao empDao = new EmpDao(); 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Getting the id, name, password, email, department from the form
		int id= Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String department = request.getParameter("dept");
		
		EmpRegisterDao reg = new EmpRegisterDao();
		// setting the id,name,password,email,department in to the reg object
		reg.setId(id);
		reg.setName(name);
		reg.setPassword(password);
		reg.setEmail(email);
		reg.setDepartment(department);
		
		try {
			
			if(empDao.addEmployee(reg))
			{
				response.sendRedirect("empLogin.jsp");
			}
			else
			{
				response.sendRedirect("empRegister.jsp");
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
