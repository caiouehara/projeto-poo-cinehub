<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Estatísticas do Gerente</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gerente.css">
</head>
<body>
<div>
    <h1>Estatísticas do Gerente</h1>

    <div class="stats">
        <!-- Quantidade de Filmes -->
        <div class="stat-item">
            <p><strong>Quantidade de Filmes:</strong> ${quantidadeFilmes}</p>
        </div>

        <!-- Quantidade de Clientes -->
        <div class="stat-item">
            <p><strong>Quantidade de Clientes:</strong> ${quantidadeClientes}</p>
        </div>

        <!-- Lucro de Aluguéis -->
        <div class="stat-item">
            <p><strong>Lucro de Aluguéis:</strong> ${lucroAluguel}</p>
        </div>

        <!-- Lucro de Compras -->
        <div class="stat-item">
            <p><strong>Lucro de Compras:</strong> ${lucroCompra}</p>
        </div>

        <!-- Lucro Total -->
        <div class="stat-item">
            <p><strong>Lucro Total:</strong> ${lucroTotal}</p>
        </div>
    </div>
</div>
</body>
</html>
