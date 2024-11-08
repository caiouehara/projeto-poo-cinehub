<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Usuário - CineHub</title>
    <!-- Link para o CSS externo -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/WEB-INF/css/styles.css">
</head>
<body>
    <div class="login-page">
        <div class="container">

            <h2>Cadastro de Usuário</h2>

            <!-- Mensagens de Feedback -->
            <c:if test="${not empty param.erro}">
                <p style="color:red;">
                    <c:choose>
                        <c:when test="${param.erro == '1'}">
                            Erro ao cadastrar usuário. Email já está em uso ou dados inválidos.
                        </c:when>
                        <c:when test="${param.erro == '2'}">
                            Tentativa de cadastrar Gerente sem autorização.
                        </c:when>
                        <c:otherwise>
                            Erro no cadastro.
                        </c:otherwise>
                    </c:choose>
                </p>
            </c:if>
            <c:if test="${not empty param.sucesso}">
                <p style="color:green;">
                    <c:choose>
                        <c:when test="${param.sucesso == '1'}">
                            Cadastro realizado com sucesso! Faça login para continuar.
                        </c:when>
                        <c:when test="${param.sucesso == '2'}">
                            Gerente cadastrado com sucesso!
                        </c:when>
                        <c:otherwise>
                            Cadastro realizado com sucesso!
                        </c:otherwise>
                    </c:choose>
                </p>
            </c:if>

            <!-- Formulário de Cadastro -->
            <form action="${pageContext.request.contextPath}/cadastro" method="post">
                <label for="nome">Nome:</label><br/>
                <input type="text" id="nome" name="nome" required><br/><br/>

                <label for="email">Email:</label><br/>
                <input type="email" id="email" name="email" required><br/><br/>

                <label for="senha">Senha:</label><br/>
                <input type="password" id="senha" name="senha" required><br/><br/>

                <!-- Campo Tipo de Usuário, visível apenas para Gerentes logados -->
                <c:if test="${sessionScope.usuario == 'Gerente'}">
                    <label for="tipo">Tipo de Usuário:</label><br/>
                    <select id="tipo" name="tipo" required>
                        <option value="Cliente">Cliente</option>
                        <option value="Gerente">Gerente</option>
                    </select><br/><br/>
                </c:if>

                <!-- Campo Tipo de Usuário para Clientes (campo oculto) -->
                <c:if test="${empty sessionScope.usuario}">
                    <input type="hidden" name="tipo" value="Cliente">
                </c:if>

                <input type="submit" value="Cadastrar">
            </form>
        </div>
    </div>
</body>
</html>
