<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<html>
    <%@ include file="/pages/globais/header.jsp" %>
    <%@ include file="/pages/globais/nav.jsp" %>

    <style>
        /* Seus estilos CSS */
        .main-movie .description {
            margin-bottom: 40px;
        }
        .main-movie .title-movie {
            margin-top: 5%;
            font-size: 40px;
        }
        .main-movie .container {
            width: 70%;
        }
        .button {
            background-color: rgba(0,0,0,0.50);
            border: none;
            color: black;
            padding: 15px 30px;
            margin-right: 15px;
            font-size: 12px;
            cursor: pointer;
            transition: 0.3s ease all;
        }
        .button:hover {
            background-color: black;
            color: black;
        }
        .button i {
            margin-right: 8px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
            text-align: left;
        }
        .box-movie {
            width: 100px;
            height: auto;
        }
    </style>

    <body>
        <main>
            <div class="main-movie">
                <div class="container">
                    <h3 class="title-movie">HOUSE OF CARDS</h3>
                    <p class="description">Nada pode impedir o político sem escrúpulos Frank Underwood de conquistar Washington. Assista agora a nova temporada de House of Cards que está imperdível.</p>
                    <div class="buttons">
                        <button role="button" class="button">
                            <i class="fas fa-play"></i>
                            ASSISTIR AGORA
                        </button>
                        <button role="button" class="button">
                            <i class="fas fa-info-circle"></i>
                            MAIS INFORMAÇÕES
                        </button>
                    </div>
                </div>
            </div>

            <!-- Exibe o número de filmes recebidos -->
            <p>Número de filmes recebidos: ${fn:length(filmes)}</p>

            <!-- Tabela para exibir os filmes -->
            <table>
                <thead>
                    <tr>
                        <th>Imagem</th>
                        <th>Título</th>
                        <th>Sinopse</th>
                        <th>Ano</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Verifica se a lista 'filmes' não está vazia -->
                    <c:choose>
                        <c:when test="${not empty filmes}">
                            <c:forEach var="filme" items="${filmes}">
                                <tr class="item">
                                    <td>
                                        <img class="box-movie" src="${pageContext.request.contextPath}/img/films/${filme.imagem}" alt="${filme.tituloFilme}">
                                    </td>
                                    <td>${filme.tituloFilme}</td>
                                    <td>${filme.sinopseFilme}</td>
                                    <td>${filme.anoFilme}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="4" style="text-align:center;">Nenhum filme disponível.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </main>
    </body>
</html>
