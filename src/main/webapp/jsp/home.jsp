<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<html>
    <%@ include file="/jsp/globais/header.jsp" %>
    <%@ include file="/jsp/globais/nav.jsp" %>

    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
        <title>Homepage</title>
    </head>

    <body>
        <main>
            <div class="hero">
                <div class="container">
                    <h3 class="title-movie">Lista de Filmes</h3>
                    <p class="description">Explore nossa coleção de filmes incríveis. Assista agora ou descubra mais informações sobre seus favoritos.</p>
                    <div class="buttons">
                        <a href="#" role="button" class="button">
                            <i class="fas fa-play"></i>
                            Assistir Agora
                        </a>
                        <a href="#" role="button" class="button">
                            <i class="fas fa-info-circle"></i>
                            Mais Informações
                        </a>
                    </div>
                </div>
            </div>

            <div class="container">
                <!-- Exibe o número de filmes recebidos -->
                <p>Número de filmes disponíveis: ${fn:length(filmes)}</p>

                <!-- Tabela para exibir os filmes -->
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
                        <!-- Verifica se a lista 'filmes' não está vazia -->
                        <c:choose>
                            <c:when test="${not empty filmes}">
                                <c:forEach var="filme" items="${filmes}">
                                    <tr class="item">
                                        <td>
                                            <img class="box-movie" src="${pageContext.request.contextPath}/img/films/${filme.getImagem()}" alt="${filme.getTituloFilme()}">
                                        </td>
                                        <td>${filme.getTituloFilme()}</td>
                                        <td>${filme.getSinopseFilme()}</td>
                                        <td>${filme.getAnoFilme()}</td>
                                        <td>${filme.getAvaliacaoFilme()}</td>
                                        <td>
                                            <div class="buttons">
                                                <a href="#" role="button" class="button"
                                                   data-id="${filme.tituloFilme}"
                                                   data-image="${pageContext.request.contextPath}/img/films/${filme.getImagem()}"
                                                   data-title="${filme.getTituloFilme()}"
                                                   data-description="${filme.getSinopseFilme()}"
                                                   data-year="${filme.getAnoFilme()}"
                                                   data-rating="${filme.getAvaliacaoFilme()}">
                                                    <i class="fas fa-play"></i>
                                                    Ver mais
                                                </a>
                                            </div>
                                        </td>
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
            </div>
        </main>

        <div id="modal" class="modal">
            <div class="modal-content">
                <button class="close-btn" id="close-modal">X</button>
                <img id="modal-image" src="" alt="Imagem do Filme">
                <div id="box-2">
                    <h2 id="modal-title"></h2>
                    <p id="modal-description"></p>
                    <p id="modal-year"></p>
                    <div class="star-rating">
                        <!-- Avaliação em Estrelas -->
                        <input type="radio" name="rating" id="star1" value="1"><label for="star1">1</label>
                        <input type="radio" name="rating" id="star2" value="2"><label for="star2">2</label>
                        <input type="radio" name="rating" id="star3" value="3"><label for="star3">3</label>
                        <input type="radio" name="rating" id="star4" value="4"><label for="star4">4</label>
                        <input type="radio" name="rating" id="star5" value="5"><label for="star5">5</label>
                    </div>
                    <h3>Comentários:</h3>
                    <textarea class="comment-box" id="comment-box" placeholder="Deixe seu comentário aqui..."></textarea>
                    <button id="submit-comment">Enviar Comentário</button>
                </div>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/scripts/script.js"></script>
    </body>
</html>
