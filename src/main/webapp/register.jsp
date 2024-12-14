<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="RegisterServlet" method="Post">
		Item : <input type="text" name="item"><br>
		Quantity : <input type="text" name="quantity"><br>
		<input type="submit" value="Call Servlet" />
	</form>
</body>
</html>