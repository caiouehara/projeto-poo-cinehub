<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<html>
    <%@ include file="/pages/globais/header.jsp" %>
    <%@ include file="/pages/globais/nav.jsp" %>

    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
        <title>Homepage</title>
    </head>

    <body>
        <main>
            <div class="hero">
                <div class="container">
                    <h3 class="title-movie">Lista de Filmes</h3>
                    <p class="description">Explore nossa coleção de filmes incríveis. Assista agora ou descubra mais informações sobre seus favoritos.</p>
                    <div class="buttons">
                        <a href="#" role="button" class="button">
                            <i class="fas fa-play"></i>
                            Assistir Agora
                        </a>
                        <a href="#" role="button" class="button">
                            <i class="fas fa-info-circle"></i>
                            Mais Informações
                        </a>
                    </div>
                </div>
            </div>

            <div class="container">
                <!-- Exibe o número de filmes recebidos -->
                <p>Número de filmes disponíveis: ${fn:length(filmes)}</p>

                <!-- Tabela para exibir os filmes -->
                <table>
                    <thead>
                        <tr>
                            <th>Imagem</th>
                            <th>Título</th>
                            <th>Sinopse</th>
                            <th>Ano</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Verifica se a lista 'filmes' não está vazia -->
                        <c:choose>
                            <c:when test="${not empty filmes}">
                                <c:forEach var="filme" items="${filmes}">
                                    <tr class="item">
                                        <td>
                                            <img class="box-movie" src="${pageContext.request.contextPath}/img/films/${filme.imagem}" alt="${filme.tituloFilme}">
                                        </td>
                                        <td>${filme.tituloFilme}</td>
                                        <td>${filme.sinopseFilme}</td>
                                        <td>${filme.anoFilme}</td>
                                        <td>
                                            <div class="buttons">
                                                <a href="#" role="button" class="button">
                                                    <i class="fas fa-play"></i>
                                                    Alugar
                                                </a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="4" style="text-align:center;">Nenhum filme disponível.</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
        </main>
    </body>
</html>
