<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="pt-BR">
    <%@ include file="/jsp/globais/header.jsp" %>

    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user.css">
        <title>User Page</title>
    </head>

    <div class="login-page">
        <div class="container">
            <h2>Login de Usuário</h2>

            <h2>Login</h2>

             <!-- Mensagens de Feedback -->
            <c:if test="${not empty param.erro}">
                    <c:choose>
                        <c:when test="${param.erro == '1'}">
                            <p style="color:red;">Email ou senha inválidos.</p>
                        </c:when>
                    </c:choose>
            </c:if>

            <form action="login" method="post">
                <label for="email">E-mail:</label>
                <input type="email" id="email" name="email" required><br>

                <label for="senha">Senha:</label>
                <input type="password" id="senha" name="senha" required><br>
                <input type="submit" value="Entrar">
            </form>
        </div>

        <button class="button">
            <a href="${pageContext.request.contextPath}/cadastro">Cadastre-se</a>
        </button>
    </div>
</html>
