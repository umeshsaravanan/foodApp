<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Orders</title>
<style>
.emptyText {
	width: 100%;
	height: calc(100vh - 80px);
	display: flex;
	flex-direction: column;
	gap: 12px;
	align-items: center;
	justify-content: center;
}

.emptyText a {
	text-decoration: none;
	color: black;
	font-size: 20px;
}

.emptyText a:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="emptyText">
		<h3>Oops it seems you haven't Ordered Yet</h3>
		<a href="home.jsp">Start Ordering</a>
	</div>
</body>
</html>