<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="pt_BR"/>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cliente.css">
    <script defer src="${pageContext.request.contextPath}/scripts/scriptModal.js"></script>
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
    <div class="movies-section-alugados">
        <div class="container">
            <h3 class="title-movie">Filmes Alugados</h3>
            <p class="description">Veja todos os filmes alugados por você</p>

            <!-- Número de filmes alugados -->
            <p>Número de filmes alugados: ${fn:length(alugueisFilmes)}</p>

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
                    <th>Data de Devolução</th>
                </tr>
                </thead>

                <tbody>
                <c:choose>
                    <c:when test="${not empty alugueisFilmes}">
                        <c:forEach var="item" items="${alugueisFilmes}">
                            <tr class="item">
                                <td>
                                    <img class="box-movie"
                                         src="${pageContext.request.contextPath}/img/films/${item.filme.imagem}"
                                         alt="${item.filme.tituloFilme}">
                                </td>
                                <td>${item.filme.tituloFilme}</td>
                                <td class="sinopse">${item.filme.sinopseFilme}</td>
                                <td>${item.filme.anoFilme}</td>
                                <td>${item.filme.avaliacaoFilme}</td>

                                <!-- Fazendo a formatação das datas -->
                                <td>
                                    <fmt:formatDate value="${item.aluguel.dataAluguel}" pattern="dd/MM/yyyy"/>
                                </td>
                                <td>
                                    <fmt:formatDate value="${item.aluguel.dataDevolucao}" pattern="dd/MM/yyyy"/>
                                </td>
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

    <!-- Seção de Filmes Comprados -->
    <div class="movies-section-comprados">
        <div class="container">
            <h3 class="title-movie">Filmes Comprados</h3>
            <p class="description">Veja todos os filmes comprados por você</p>

            <!-- Número de filmes comprados -->
            <p>Número de filmes comprados: ${fn:length(comprasFilmes)}</p>

            <!-- Tabela de filmes comprados -->
            <table>
                <thead>
                <tr>
                    <th>Imagem</th>
                    <th>Título</th>
                    <th>Sinopse</th>
                    <th>Ano</th>
                    <th>Avaliação</th>
                    <th>Data de Compra</th>
                </tr>
                </thead>

                <tbody>
                <c:choose>
                    <c:when test="${not empty comprasFilmes}">
                        <c:forEach var="item" items="${comprasFilmes}">
                            <tr class="item">
                                <td>
                                    <img class="box-movie"
                                         src="${pageContext.request.contextPath}/img/films/${item.filme.imagem}"
                                         alt="${item.filme.tituloFilme}">
                                </td>
                                <td>${item.filme.tituloFilme}</td>
                                <td class="sinopse">${item.filme.sinopseFilme}</td>
                                <td>${item.filme.anoFilme}</td>
                                <td>${item.filme.avaliacaoFilme}</td>

                                <!-- Formatação da data de compra -->
                                <td>
                                    <fmt:formatDate value="${item.compra.dataCompra}" pattern="dd/MM/yyyy"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="6" style="text-align:center;">Você não comprou nenhum filme ainda.</td>
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
