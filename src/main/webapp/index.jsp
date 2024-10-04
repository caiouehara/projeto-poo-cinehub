<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Lista de Filmes</title>
	<link rel="stylesheet" href="styles/styles.css" />
	<style>
		body {
			font-family: Arial, sans-serif;
			background-color: #f0f0f0;
			margin: 0;
			padding: 20px;
		}
		h2 {
			color: #333;
			text-align: center;
			margin-bottom: 20px;
		}
		table {
			width: 80%;
			margin: 0 auto;
			border-collapse: collapse;
			background-color: #fff;
			box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
		}
		th, td {
			padding: 10px;
			text-align: left;
			border-bottom: 1px solid #ddd;
		}
		th {
			background-color: #4CAF50;
			color: white;
		}
		tr:nth-child(even) {
			background-color: #f9f9f9;
		}
		tr:hover {
			background-color: #f1f1f1;
		}
	</style>
</head>
<body>
	<%@ include file="globais/header.html" %>

	<h2>Lista de Filmes</h2>

	<table>
		<tr>
			<th>Título</th>
			<th>Diretor</th>
			<th>Ano</th>
		</tr>

		<jsp:useBean id="filmes" scope="request" type="java.util.List"/>
		<c:forEach var="filme" items="${filmes}">
			<tr>
				<td>${filme.titulo}</td>
				<td>${filme.diretor}</td>
				<td>${filme.ano}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>