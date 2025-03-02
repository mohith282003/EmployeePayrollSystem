<!DOCTYPE html>
<html>
<head>
    <title>Admin Panel</title>
    <link rel="stylesheet" type="text/css" href="index1.css">
</head>
<body>
	<div class="container-wrapper"></div>
	<h1>Admin Panel</h1>
	<!-- Button to View Employee Details -->
	<div class="container3">
		<p>
			<a href="empRegistration.jsp">Add New Employee</a>
		</p>
	</div>
	<div class="container1">
		<form action="AdminEmployeeServlet" method="get">
			<button type="submit">View Employee Details</button>
		</form>
	</div>
	<!-- Button to Calculate Salary -->
	<div class="container2">
		<form action="SalaryServlet" method="get">
			<button type="submit">Calculate Salary</button>
		</form>
	</div>
	<p>
		<a href="index.html">Back to home</a>
	</p>
</body>
</html>
