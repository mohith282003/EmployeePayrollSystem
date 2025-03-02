<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<h1>Admin Login</h1>
	<div class="container">
		<form action="AdminLoginServlet" method="post">
			<label for="name">Name:</label> <input type="text" id="name"
				name="name"> <label for="password">Password:</label> <input
				type="password" id="password" name="password">
			<button type="submit">Login</button>
		</form>
		<p>
			<a href="index.html">Back to home</a>
		</p>

	</div>
</body>
</html>