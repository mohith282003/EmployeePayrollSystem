package com.emp.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.emp.dao.EmpDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/EmpLoginServlet")
public class EmpLoginServlet extends HttpServlet {

	EmpDao empDao = new EmpDao(); 
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		// Getting the name and password from the form
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		
		try {
			// Checking the credentials entered are correct
			if(empDao.isValid(name,password))
			{
				HttpSession session = request.getSession();
				session.setAttribute("name",name);		
				response.sendRedirect("empOperation.jsp");
			}
			else
			{
				response.sendRedirect("empLogin.jsp");
				System.out.println("Error");
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

}
