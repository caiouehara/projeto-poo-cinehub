<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<%@ include file="/jsp/globais/header.jsp" %>
<%@ include file="/jsp/globais/nav.jsp" %>

<html>
<head>
    <title>Dashboard do Gerente</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gerente.css">
</head>
<body>
<div class="container">
    <h1>Bem-vindo, ${sessionScope.nome}!</h1>

    <!-- Seção de Estatísticas -->
    <h2>Estatísticas</h2>
    <div class="charts">
        <!-- Gráfico de Registros de Usuários -->
        <div class="chart">
            <h3>Novos Usuários por Mês</h3>
            <img src="${pageContext.request.contextPath}/chart/userRegistrations" alt="Novos Usuários por Mês"/>
        </div>
        <!-- Gráfico de Aluguéis de Filmes -->
        <div class="chart">
            <h3>Aluguéis de Filmes por Gênero</h3>
            <img src="${pageContext.request.contextPath}/chart/movieRentals" alt="Aluguéis de Filmes por Gênero"/>
        </div>
    </div>

    <!-- Seção para Adicionar Novos Filmes -->
    <%@ include file="/jsp/gerente/adicionarFilme.jsp" %>
</div>
</body>
</html>
