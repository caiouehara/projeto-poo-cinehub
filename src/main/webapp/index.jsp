<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Lista de Filmes</title>
	<link rel="stylesheet" href="styles/styles.css" />
</head>
<body>
	<%@ include file="globais/header.html" %>

	<header>
        <div class="container">
            <img src="assets/logo_netflix.png" alt="Logo Netflix" class="logo">
            <nav>
                <a href="#">In�cio</a>
                <a href="#">S�ries</a>
                <a href="#">Filmes</a>
                <a href="#">Document�rios</a>
            </nav>
        </div>
    </header>

	<main>
        <div class="main-movie">
            <div class="container">
                <h3 class="title-movie">HOUSE OF CARDS</h3>
                <p class="description">Nada pode impedir o pol�tico sem escr�pulos Frank Underwood de conquistar Washington. Assista agora a nova temporada de House of Cards que est� imperd�vel.</p>
                <div class="buttons">
                    <button role="button" class="button">
                        <i class="fas fa-play"></i>
                        ASSISTIR AGORA
                    </button>
                    <button role="button" class="button">
                        <i class="fas fa-info-circle"></i>
                        MAIS INFORMA��ES
                    </button>
                </div>
            </div>
        </div>
    </main>

	<table>
		<tr>
			<th>T�tulo</th>
			<th>Diretor</th>
			<th>Ano</th>
		</tr>

		<c:forEach var="filme" items="${filmes}">
			<tr class="item">
				<img class="box-movie" src="/assets/films/${filme.imagem}" alt="${filme.titulo}">
				<td>${filme.titulo}</td>
				<td>${filme.diretor}</td>
				<td>${filme.ano}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>