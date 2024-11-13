<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
    <script>
        const contextPath = '${pageContext.request.contextPath}';
    </script>
    <script src="${pageContext.request.contextPath}/scripts/script.js" defer></script>
    <title>Homepage</title>
</head>

    <%@ include file="/jsp/globais/header.jsp" %>
    <%@ include file="/jsp/globais/nav.jsp" %>

<body>
    <main>
        <!-- Hero Section -->
        <div class="hero">
            <div class="container">
                <h3 class="title-movie">Lista de Filmes</h3>
                <p class="description">Explore nossa coleção de filmes incríveis. Assista agora ou descubra mais informações sobre seus favoritos.</p>
                <!-- Buttons can be removed if not needed -->
            </div>
        </div>

        <!-- Movies List -->
        <div class="container">
            <!-- Display the number of available movies -->
            <p>Número de filmes disponíveis: ${fn:length(filmes)}</p>

            <!-- Table to display the movies -->
            <table>
                <thead>
                <tr>
                    <th>Imagem</th>
                    <th>Título</th>
                    <th>Sinopse</th>
                    <th>Ano</th>
                    <th>Avaliação</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <!-- Check if 'filmes' list is not empty -->
                <c:choose>
                    <c:when test="${not empty filmes}">
                        <c:forEach var="filme" items="${filmes}">
                            <tr class="item">
                                <td>
                                    <img class="box-movie"
                                         src="${pageContext.request.contextPath}/img/films/${filme.imagem}"
                                         alt="${filme.tituloFilme}">
                                </td>
                                <td>${filme.tituloFilme}</td>
                                <td>${filme.sinopseFilme}</td>
                                <td>${filme.anoFilme}</td>
                                <td>${filme.avaliacaoFilme}</td>
                                <td>
                                    <div class="buttons">
                                        <a href="#" role="button" class="button ver-mais-btn"
                                           data-id="${filme.id}"
                                           data-image="${pageContext.request.contextPath}/img/films/${filme.imagem}"
                                           data-title="${filme.tituloFilme}"
                                           data-description="${filme.sinopseFilme}"
                                           data-year="${filme.anoFilme}"
                                           data-rating="${filme.avaliacaoFilme}">
                                            <i class="fas fa-info-circle"></i>
                                            Ver mais
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="6" style="text-align:center;">Nenhum filme disponível.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
    </main>

            <!-- Modal de Detalhes dos filmes-->
            <div id="modal" class="modal">
                <div class="modal-content">
                    <button class="close-btn" id="close-modal">X</button>
                    <img id="modal-image" src="" alt="Imagem do Filme">
                    <div id="box-2">
                        <h2 id="modal-title"></h2>
                        <p id="modal-description"></p>
                        <p id="modal-year"></p>
                        <h3>Nota média:</h3>
                        <p id="nota-final"></p>

                        <!-- Avaliação do filme -->
                        <div class="star-rating">
                            <h3>Avalie este filme:</h3>
                            <form action="${pageContext.request.contextPath}/adicionarAvaliacao" method="post">
                                <%--@declare id="nota"--%><input type="hidden" id="avaliacao-filmeId" name="filmeId" value=""/>
                                <label for="nota">Nota (0.0 - 10.0):</label>
                                <input type="number" step="0.1" min="0" max="10" name="nota" required/>
                                <input type="submit" value="Enviar Avaliação"/>
                            </form>
                        </div>

                        <!-- Botões de Compra e Aluguel -->
                        <button id="buy-button">Comprar - R$ <span id="modal-price"></span></button>
                        <button id="rent-button">Alugar - R$ <span id="modal-rent"></span></button>

                        <!-- Comentários -->
                        <h3>Comentários:</h3>
                        <form action="${pageContext.request.contextPath}/adicionarComentario" method="post">
                            <input type="hidden" id="comentario-filmeId" name="filmeId" value=""/>
                            <textarea class="comment-box" id="comment-box" name="texto" required></textarea>
                            <input type="submit" value="Enviar Comentário"/>
                        </form>

                        <!-- Seção de comentários -->
                        <div id="comentarios">
                            <!-- Comentários serão carregados aqui via Javascript -->
                        </div>
                    </div>
                </div>
            </div>
        </main>

        <script src="${pageContext.request.contextPath}/scripts/script.js"></script>
    </body>
</html>
