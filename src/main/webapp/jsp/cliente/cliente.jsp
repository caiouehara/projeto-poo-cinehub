<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cliente.css">
    <script defer src="${pageContext.request.contextPath}/scripts/script.js"></script>
    <title>Perfil do Cliente</title>
</head>

<%@ include file="/jsp/globais/header.jsp" %>
<%@ include file="/jsp/globais/nav.jsp" %>

<body>
    <main>
        <!-- Seção de Perfil do Cliente -->
        <div class="profile-section">
            <div class="container">
                <h2>Perfil do Cliente</h2>
                <div class="client-info">
                    <div class="details">
                        <p><strong>Nome:</strong> ${cliente.nome}</p>
                        <p><strong>Email:</strong> ${cliente.email}</p>
                    </div>
                </div>
            </div>
        </div>

        <!-- Seção de Filmes Alugados -->
        <div class="rented-movies-section">
            <div class="container">
                <h3 class="title-movie">Filmes Alugados</h3>
                <p class="description">Veja todos os filmes que você já alugou conosco.</p>

                <!-- Número de filmes alugados -->
                <p>Número de filmes alugados: ${fn:length(filmesAlugados)}</p>

                <!-- Tabela de filmes alugados -->
                <table>
                    <thead>
                        <tr>
                            <th>Imagem</th>
                            <th>Título</th>
                            <th>Sinopse</th>
                            <th>Ano</th>
                            <th>Avaliação</th>
                            <th>Data de Aluguel</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${not empty filmesAlugados}">
                                <c:forEach var="filme" items="${filmesAlugados}">
                                    <tr class="item">
                                        <td>
                                            <img class="box-movie"
                                                 src="${pageContext.request.contextPath}/img/films/${filme.imagem}"
                                                 alt="${filme.tituloFilme}">
                                        </td>
                                        <td>${filme.tituloFilme}</td>
                                        <td>${filme.sinopseFilme}</td>
                                        <td>${filme.anoFilme}</td>
                                        <td>${filme.avaliacaoFilme}</td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="7" style="text-align:center;">Você não alugou nenhum filme ainda.</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
        </div>
    </main>
</body>
</html>
