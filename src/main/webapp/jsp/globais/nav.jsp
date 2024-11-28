<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nav.css">
    <title>Cinehub</title>
</head>
<body>
<div class="nav">
    <!-- Header com navegação -->
    <header>
        <div class="header-container">
            <a href="#">
                <img src="${pageContext.request.contextPath}/img/logo.png" alt="Logo CineHub" class="logo">
            </a>

            <div class="user-info">
                <span>Bem-vindo, ${sessionScope.nome}!</span>
                <a href="${pageContext.request.contextPath}/logout" class="logout-button">Sair</a>
            </div>

            <i class="fas fa-bars menu-toggle"></i>
            <nav class="menu-nav">
                <a href="${pageContext.request.contextPath}/home">Home</a>
                <c:if test="${sessionScope.usuario == 'Cliente'}">
                    <a href="${pageContext.request.contextPath}/cliente">Meus filmes</a>
                </c:if>
                <c:if test="${sessionScope.usuario == 'Gerente'}">
                    <a href="${pageContext.request.contextPath}/gerente">Gerente</a>
                </c:if>
            </nav>
        </div>
    </header>

    <!-- Script para o menu responsivo -->
    <script>
        const menuToggle = document.querySelector('.menu-toggle');
        const menuNav = document.querySelector('.menu-nav');

        menuToggle.addEventListener('click', () => {
            menuNav.classList.toggle('active');
        });
    </script>
</div>
</body>
</html>
