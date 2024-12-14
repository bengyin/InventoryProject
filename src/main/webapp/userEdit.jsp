<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Inventory Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-light">
		<div>
			<a class="navbar-brand"> Inventory Management Application </a>
		</div>
		<ul class="navbar-nav">
			<li><a
				href="<%=request.getContextPath()%>/UserServlet/dashboard"
				class="nav-link">Back to Dashboard</a></li>
		</ul>
	</nav>
	<div class="container col-md-6">
		<div class="card">
			<div class="card-body">
				<c:if test="${!empty InventoryItem.item}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${empty InventoryItem.item}">
					<form action="insert" method="post">
				</c:if>
				<caption>
					<h2>
						<c:if test="${!empty InventoryItem.item}">
Edit Item
</c:if>
						<c:if test="${empty InventoryItem.item}">
Add New Item
</c:if>
					</h2>
				</caption>
				<c:if test="${InventoryItem != null}">
					<input type="hidden" name="oriItem"
						value="<c:out
value='${InventoryItem.item}' />" />
				</c:if>
				<fieldset class="form-group">
					<label>Item</label> <input type="text"
						value="<c:out
value='${InventoryItem.item}' />"
						class="form-control" name="itemName" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Quantity</label> <input type="text"
						value="<c:out
value='${InventoryItem.quantity}' />"
						class="form-control" name="quantity">
				</fieldset>
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>