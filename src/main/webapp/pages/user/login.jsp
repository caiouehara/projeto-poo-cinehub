<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pt-BR">
    <%@ include file="/pages/globais/header.jsp" %>
    <%@ include file="/pages/globais/nav.jsp" %>

    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user.css">
        <title>User Page</title>
    </head>

    <div class="login-page">
        <div class="container">
            <h2>Login de Usuário</h2>

            <h2>Login</h2>
            <% if (request.getParameter("erro") != null) { %>
                <p style="color:red;">Email ou senha inválidos.</p>
            <% } %>
            <form action="login" method="post">
                <label for="email">E-mail:</label>
                <input type="email" id="email" name="email" required><br>

                <label for="senha">Senha:</label>
                <input type="password" id="senha" name="senha" required><br>

                <input type="submit" value="Entrar">
            </form>
        </div>
    </div>
</html>
