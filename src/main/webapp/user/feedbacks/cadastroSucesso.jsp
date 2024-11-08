<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <title>Cadastro Bem-sucedido</title>
</head>
<body>
    <div class="login-page">
        <div class="container">
            <% String tipo = request.getParameter("tipo"); %>
            <h2><%= tipo != null && tipo.equals("gerente") ? "Gerente" : "Cliente" %> cadastrado com sucesso!</h2>
            <a href="login.jsp">Ir para a página de login</a>
        </div>
    </div>
</body>
</html>
