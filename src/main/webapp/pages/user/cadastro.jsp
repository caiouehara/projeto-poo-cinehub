<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user.css">
        <title>Cadastro</title>
        <script type="text/javascript">
            function validateForm() {
                var senha = document.getElementById("senha").value;
                var confirmarSenha = document.getElementById("confirmarSenha").value;
                if (senha !== confirmarSenha) {
                    alert("As senhas não coincidem. Por favor, verifique.");
                    return false;
                }
                return true;
            }
        </script>
    </head>

    <body>
        <div class="login-page">
            <div class="container">
                <h2>Cadastro de Usuário</h2>
                <% if (request.getParameter("erro") != null) { %>
                    <% if ("1".equals(request.getParameter("erro"))) { %>
                        <p style="color:red;">E-mail já cadastrado ou permissão insuficiente.</p>
                    <% } %>
                <% } %>
                <form action="cadastro" method="post" onsubmit="return validateForm();">
                    <label for="nome">Nome Completo:</label>
                    <input type="text" id="nome" name="nome" required>

                    <label for="email">E-mail:</label>
                    <input type="email" id="email" name="email" required>

                    <label for="senha">Senha:</label>
                    <input type="password" id="senha" name="senha" required>

                    <label for="confirmarSenha">Confirmar Senha:</label>
                    <input type="password" id="confirmarSenha" name="confirmarSenha" required>

                    <label for="tipoUsuario">Tipo de Usuário:</label>
                    <select id="tipoUsuario" name="tipoUsuario" required>
                        <option value="Cliente">Cliente</option>
                        <option value="Gerente">Gerente</option>
                    </select>

                    <input type="submit" value="Cadastrar">
                </form>
            </div>
        </div>
    </body>
</html>
