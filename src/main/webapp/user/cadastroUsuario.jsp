<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cadastro de Usuário</title>
    <style>
        /* Estilos simples para o formulário */
        body {
            font-family: Arial, sans-serif;
        }
        .container {
            width: 400px;
            margin: auto;
        }
        form {
            border: 1px solid #ccc;
            padding: 20px;
        }
        label {
            display: block;
            margin-top: 10px;
        }
        input[type="text"], input[type="email"], input[type="password"] {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
        }
        input[type="submit"] {
            margin-top: 20px;
            padding: 10px;
            width: 100%;
        }
        .error {
            color: red;
        }
    </style>
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
    <div class="container">
        <h2>Cadastro de Usuário</h2>

        <form action="cadastro" method="post" onsubmit="return validateForm();">
            <label for="nome">Nome Completo:</label>
            <input type="text" id="nome" name="nome" required>

            <label for="email">E-mail:</label>
            <input type="email" id="email" name="email" required>

            <label for="senha">Senha:</label>
            <input type="password" id="senha" name="senha" required>

            <label for="confirmarSenha">Confirmar Senha:</label>
            <input type="password" id="confirmarSenha" name="confirmarSenha" required>

            <input type="submit" value="Cadastrar">
        </form>
    </div>
</body>
</html>