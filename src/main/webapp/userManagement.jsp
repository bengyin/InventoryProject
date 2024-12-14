<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inventory System</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
<img src="/InventoryProject/ACME-Logo.jpg" width="248" height="131" /><p><b>Address:</b> 123 Tuas Ave 1 Singapore 123456</p>
	<div class="row">
		<div class="container">
			<h3 class="text-center">List of Inventory Items</h3>
			<hr>
			<div class="container text-left">
				<!-- Add new user button redirects to the register.jsp page -->
				<!--  <a href="<%=request.getContextPath()%>/register.jsp" class="btn btn-success">Add New Item</a> -->
				<a href="<%=request.getContextPath()%>/UserServlet/insert" class="btn btn-success">Add New Item</a>				
			</div>
			<br>
			<!-- Create a table to list out all current users information -->
			<table class="table">
				<thead>
					<tr>
						<th>Item</th>
						<th>Quantity</th>
						<th>Actions</th>
					</tr>
				</thead>
				<!-- Pass in the list of users receive via the Servlet response to a loop -->
				<tbody>
					<c:forEach var="InventoryItem" items="${listItems}">
						<!-- For each user in the database, display their information accordingly -->
						<tr>
							<td><c:out value="${InventoryItem.item}" /></td>
							<td><c:out value="${InventoryItem.quantity}" /></td>
							<!-- For each user in the database, Edit/Delete buttons which invokes the edit/delete functions -->
							<td>
								<a href="edit?item=<c:out value='${InventoryItem.item}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp;
							    <a href="delete?item=<c:out value='${InventoryItem.item}' />">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>