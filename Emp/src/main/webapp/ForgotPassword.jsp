<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<h1>Forgot Password</h1>
	<div class="container">

		<form action="ForgotPasswordServlet" method="post">
			<label for="id">Id:</label> <input type="number" id="id" name="id">
			<label for="name">Name:</label> <input type="text" id="name"
				name="name"> <label for="dept">Department:</label> <input
				type="text" id="dept" name="dept"> <label for="email">Email:</label>
			<input type="text" id="email" name="email">

			<button type="submit">Authenticate</button>
		</form>
		<p>
			<a href="index.html">Back to home</a>
		</p>
	</div>
</body>
</html>