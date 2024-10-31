<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Book</title>
<link rel="stylesheet" href="../styles/styles.css" />
</head>
<body>
	<%@ include file="../globais/header.html"%>
	<h2>Update Book</h2>
	<form action="updatebook.jsp" method="post">
	    <input type="hidden" value="${param.id}" name="id" />
		Title <br /> <input type="text" name="title"
		   value="${result.rows[0].title}" />
		<p />
		Author <br /> <input type="text" name="author"
			value="${result.rows[0].author}" />
		<p />
		Price <br /> <input type="number" name="price"
		    value="${result.rows[0].price}" />
		<p />
		<input type="submit" value="Update Book" />
	</form>

</body>
</html>