<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="jakarta.servlet.http.HttpSession, java.util.List, com.emp.dao.EmpRegisterDao"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Dashboard</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

	<%
	HttpSession session1 = request.getSession(false);
	String name = "Mohsin";

	if (session1 != null && session1.getAttribute("name") != null) {
		name = (String) session1.getAttribute("name");
	}

	// Retrieve employees list and selected department
	List<EmpRegisterDao> employees = (List<EmpRegisterDao>) request.getAttribute("employeeList");
	String selectedDepartment = (String) request.getAttribute("selectedDepartment"); // Ensure department is retrieved
	boolean showTable = (employees != null && !employees.isEmpty());
	%>

	<div class="container">
		<h1>
			Welcome,
			<%=name%></h1>

		<!-- Download Payslip Button -->
		<form action="PaySlipServlet" method="get">
			<input type="hidden" name="name" value="<%=name%>">
			<button type="submit">Download Payslip</button>
		</form>

		<!-- Department Selection Dropdown -->
		<form action="DepartmentServlet" method="post">
			<label for="department">Select Department:</label> <select
				name="department" id="department" required>
				<option value="">-- Select --</option>
				<option value="HR"
					<%="HR".equals(selectedDepartment) ? "selected" : ""%>>HR</option>
				<option value="sre"
					<%="sre".equals(selectedDepartment) ? "selected" : ""%>>SRE</option>
				<option value="devops"
					<%="devops".equals(selectedDepartment) ? "selected" : ""%>>DevOps</option>
				<option value="Marketing"
					<%="Marketing".equals(selectedDepartment) ? "selected" : ""%>>Marketing</option>
			</select>
			<button type="submit">Get Employees</button>
		</form>
		<p>
			<a href="index.html">Logout</a>
		</p>

		<!-- Display Employees Table -->
		<%
		if (showTable) {
		%>
		<div>
			<h2>Employee List</h2>
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
		<p>
			<b>.</b>
		</p>
		<%
		}
		%>
	</div>

</body>
</html>
