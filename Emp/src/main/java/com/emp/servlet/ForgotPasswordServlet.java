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

@WebServlet("/ForgotPasswordServlet")
public class ForgotPasswordServlet extends HttpServlet {

	EmpDao empDao = new EmpDao(); 
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		// Getting the id, name ,department,email from the form
		int id = Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String dept=request.getParameter("dept");
		String email = request.getParameter("email");
		
		try {
			// if authentication is successful then redirect to reset password page
			if(empDao.authenticate(id, name, dept, email))
			{
				HttpSession session = request.getSession();
				session.setAttribute("name",name);
				session.setAttribute("id",id);
				response.sendRedirect("ResetPassword.jsp");
			}
			else
			{
				response.sendRedirect("index.html");
				System.out.println("Error");
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

}
