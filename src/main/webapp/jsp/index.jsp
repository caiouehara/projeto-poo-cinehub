<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cinehub</title>
</head>
<body>
    <style><%@include file="/css/styles.css"%></style>
    <%@ include file="globais/header.html" %>

    <header>
        <div class="container">
            <img src="${pageContext.request.contextPath}/img/logo_netflix.png" alt="Logo Netflix" class="logo">
            <nav>
                <a href="#">Início</a>
                <a href="#">Séries</a>
                <a href="#">Filmes</a>
                <a href="#">Documentários</a>
            </nav>
        </div>
    </header>

    <main>
        <div class="main-movie">
            <div class="container">
                <h3 class="title-movie">HOUSE OF CARDS</h3>
                <p class="description">Nada pode impedir o político sem escrúpulos Frank Underwood de conquistar Washington. Assista agora a nova temporada de House of Cards que está imperdível.</p>
                <div class="buttons">
                    <button role="button" class="button">
                        <i class="fas fa-play"></i>
                        ASSISTIR AGORA
                    </button>
                    <button role="button" class="button">
                        <i class="fas fa-info-circle"></i>
                        MAIS INFORMAÇÕES
                    </button>
                </div>
            </div>
        </div>
    </main>

    <table>
        <!-- Itera sobre o atributo 'filmes' e exibe cada filme -->
        <c:forEach var="filme" items="${filmes}">
            <tr class="item">
                <td><img class="box-movie" src="/assets/films/${filme.imagem}" alt="${filme.titulo}"></td>
                <td>${filme.titulo}</td>
                <td>${filme.diretor}</td>
                <td>${filme.ano}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
