package com.emp.servlet;

import java.io.IOException;
import java.sql.SQLException;
import com.emp.dao.AdminDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	AdminDao adminDao = new AdminDao();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		// Getting the name and password from the form
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		System.out.println(name);
		System.out.println(password);
		try {
			// Checking the credentials entered are correct
			if(adminDao.isValid(name,password))
			{
				HttpSession session = request.getSession();
				session.setAttribute("name",name);		
				response.sendRedirect("adminOperation.jsp");
			}
			else
			{
				response.sendRedirect("adminLogin.jsp");
				System.out.println("Error");
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
}



