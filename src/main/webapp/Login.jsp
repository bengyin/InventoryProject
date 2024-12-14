<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Inventory Management Page</h1>
	<!-- Create a form with the action attribute to specific where to send the form-data when the form is submitted, method attribute to specific the method used (GET, POST, PUT, DELETE, Etc.) -->
	<form action="LoginServlet" method="post">
		Item: <input type="text" name="Item" size="20">
		Quantity: <input type="text" name="Quantity" size="20">
		<!-- Implement submit button with type = submit to perform the request when clicked -->
		<input type="submit" value="Submit" />
	</form>
</body>
</html>