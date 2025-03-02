<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="jakarta.servlet.http.HttpSession, java.util.List, com.emp.dao.EmpRegisterDao"%>
<!DOCTYPE html>
<html>
<head>
     <meta charset="UTF-8">
     <title>Insert title here</title>
     <link rel="stylesheet" type="text/css" href="new.css">
</head>
<body>
	<%
	HttpSession session1 = request.getSession(false);
	String name = "Mohsin";

	if (session1 != null && session1.getAttribute("name") != null) {
		name = (String) session1.getAttribute("name");
	}
	Integer id = (Integer) session1.getAttribute("id");
	if (id == null) {
		response.sendRedirect("index.html"); // Redirect if session expired
	}
	%>
	<div class="conatiner1">
		<h1> Welcome , <%=name%></h1>
		<form action="ResetPasswordServlet" method="post">
			<input type="hidden" name="id" value="<%=id%>"> <label>New
				Password:</label> <input type="password" name="newPassword" required>
			<button type="submit">Reset Password</button>
		</form>
	</div>
</body>
</html>