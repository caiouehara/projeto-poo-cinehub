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

    <!-- Seção para as estatisticas -->
    <%@include file="estatisticas.jsp"%>
    <!-- Seção para Adicionar Novos Filmes -->
    <%@ include file="/jsp/gerente/adicionarFilme.jsp" %>
</div>
</body>
</html>
