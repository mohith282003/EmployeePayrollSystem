<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.emp.dao.EmpRegisterDao"%>
<!DOCTYPE html>
<html>
<head>
   <title>Employee Details</title>
   <link rel="stylesheet" type="text/css" href="index2.css">
</head>
<body>

	<h1>Employee Details</h1>

	<%
	List<EmpRegisterDao> employees = (List<EmpRegisterDao>) request.getAttribute("employeeList");
	if (employees != null && !employees.isEmpty()) {
	%>
	<div class="container1">
		<table border="1">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>Department</th>
			</tr>
			<%
			for (EmpRegisterDao emp : employees) {
			%>
			<tr>
				<td><%=emp.getId()%></td>
				<td><%=emp.getName()%></td>
				<td><%=emp.getEmail()%></td>
				<td><%=emp.getDepartment()%></td>
			</tr>
			<%
			}
			%>
		</table>
	</div>

	<%
	} else {
	%>
	<p>No employees found.</p>
	<%
	}
	%>


	<!-- Back Button -->
	<div class="container1">
		<a href="adminOperation.jsp">Back to Admin Panel</a>
	</div>

</body>
</html>
