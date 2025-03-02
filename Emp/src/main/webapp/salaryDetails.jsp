<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.emp.dao.EmpRegisterDao"%>
<!DOCTYPE html>
<html>
<head>
    <title>Salary Details</title>
    <link rel="stylesheet" type="text/css" href="index2.css">
</head>
<body>

	<h1>Salary Details</h1>

	<%
	List<EmpRegisterDao> salaryList = (List<EmpRegisterDao>) request.getAttribute("salaryList");
	if (salaryList != null && !salaryList.isEmpty()) {
	%>
	<div class="container1">
		<table border="1">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Work Hours</th>
				<th>Leaves</th>
				<th>Salary</th>
			</tr>
			<%
			for (EmpRegisterDao emp : salaryList) {
			%>
			<tr>
				<td><%=emp.getId()%></td>
				<td><%=emp.getName()%></td>
				<td><%=emp.getWorkHours()%></td>
				<td><%=emp.getLeaves()%></td>
				<td><%=emp.getSalary()%></td>
			</tr>
			<%
			}
			%>
		</table>
	</div>
	<%
	} else {
	%>
	<p>No salary records found.</p>
	<%
	}
	%>

	<!-- Back Button -->
	<div class="container1">
		<a href="adminOperation.jsp">Back to Admin Panel</a>
	</div>
</body>
</html>
