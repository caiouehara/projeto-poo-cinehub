<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
    <script>
        var contextPath = '<%= request.getContextPath() %>';
    </script>
    <script src="${pageContext.request.contextPath}/scripts/scriptModal.js" defer></script>
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
            <p class="description">Explore nossa coleção de filmes incríveis. Assista agora ou descubra mais informações
                sobre seus favoritos.</p>
            <!-- Buttons can be removed if not needed -->
        </div>
    </div>

    <!-- Tabela de filmes -->
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
                                       data-rating="${filme.avaliacaoFilme}"
                                       data-rent="${filme.getPrecoFilmeAluguel()}"
                                       data-price="${filme.getPrecoFilmeCompra()}"
                                       data-duration="${filme.getDuracaoFilme()}">
                                        <c:choose>
                                            <c:when test="${sessionScope.usuario == 'Cliente'}">
                                                <i class="fas fa-info-circle"></i>
                                                Ver mais
                                            </c:when>
                                            <c:otherwise>
                                                <i class="fas fa-info-circle"></i>
                                                Editar
                                            </c:otherwise>
                                        </c:choose>
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

    <!-- Modal de Detalhes dos filmes-->
    <c:choose>
        <c:when test="${sessionScope.usuario == 'Cliente'}">
            <!--Vizualização cliente-->
            <div id="modalCliente" class="modal">
                <div class="modal-content">
                    <button class="close-btn" id="close-modal">X</button>
                    <img id="modal-image" src="" alt="Imagem do Filme">
                    <div id="box-2">
                        <h2 id="modal-title"></h2>
                        <p id="modal-description"></p>
                        <div class="modal-info-row">
                            <p id="modal-year"></p>
                            <p id="modal-duration"></p>
                        </div>
                        <h3>Nota média:</h3>
                        <p id="nota-final"></p>

                        <!-- Avaliação do filme -->
                        <div class="star-rating">
                            <h3>Avalie este filme:</h3>
                            <form action="${pageContext.request.contextPath}/adicionarAvaliacao" method="post">
                                    <%--@declare id="nota"--%><input type="hidden" id="avaliacao-filmeId" name="filmeId"
                                                                     value=""/>
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
                        <div id="comentarios" class="comentarios-lista">
                            <!-- Comentários serão carregados aqui via Javascript -->
                        </div>
                    </div>
                </div>
            </div>
        </c:when>


        <c:otherwise>
            <!-- Vizualização para o gerente -->
            <div id="modalGerente" class="modal">
                <div class="modal-content">
                    <button class="close-btn" id="close-modal-Gerente">X</button>
                    <img id="modal-image-Gerente" src="" alt="Imagem do Filme">
                    <div id="box-3">
                        <h2 id="modal-title-Gerente"></h2>
                        <p id="modal-description-Gerente"></p>
                        <div class="modal-info-row">
                            <p id="modal-year-Gerente"></p>
                            <p id="modal-duration-Gerente"></p>
                        </div>
                        <p id="modal-price-Gerente"></p>
                        <p id="modal-rent-Gerente"></p>
                        <h3>Nota média:</h3>
                        <p id="nota-final-Gerente"></p>
                    </div>
                    <div id="modal-forms">
                        <!-- Formulários de edição para o gerente -->
                        <h2>Editar Informações do Filme</h2>
                        <form action="${pageContext.request.contextPath}/gerente/editarFilme" method="post"
                              enctype="multipart/form-data">
                            <input type="hidden" id="modal-filmeId" name="filmeId" value=""/>

                            <!-- Campo para mudar título -->
                            <label for="tituloFilme">Título:</label>
                            <input type="text" id="tituloFilme" name="tituloFilme" value=""/>

                            <!-- Campo para mudar ano -->
                            <label for="anoFilme">Ano:</label>
                            <input type="number" id="anoFilme" name="anoFilme" value=""/>

                            <!-- Campo para mudar duração -->
                            <label for="duracaoFilme">Duração (minutos):</label>
                            <input type="number" id="duracaoFilme" name="duracaoFilme" value=""/>

                            <!-- Campo para mudar sinopse -->
                            <label for="sinopseFilme">Sinopse:</label>
                            <textarea id="sinopseFilme" name="sinopseFilme"></textarea>

                            <!-- Campo para mudar imagem -->
                            <label for="imagemFilme">Imagem:</label>
                            <input type="file" id="imagemFilme" name="imagemFilme"/>

                            <!-- Campo para mudar preço de aluguel -->
                            <label for="precoAluguel">Preço de Aluguel:</label>
                            <input type="number" id="precoAluguel" name="precoAluguel" step="0.01" value=""/>

                            <!-- Campo para mudar preço de compra -->
                            <label for="precoCompra">Preço de Compra:</label>
                            <input type="number" id="precoCompra" name="precoCompra" step="0.01" value=""/>

                            <input type="submit" class="save-button" value="Salvar Alterações"/>
                        </form>

                        <!-- Verifica se há uma mensagem de erro na sessão -->
                        <c:if test="${not empty sessionScope.mensagemErro}">
                            <%
                                String mensagemErro = (String) session.getAttribute("mensagemErro");
                                if (mensagemErro == null) mensagemErro = "";
                                mensagemErro = mensagemErro.replace("\\", "\\\\").replace("'", "\\'");
                            %>
                            <script type="text/javascript">
                                alert('<%= mensagemErro %>');
                            </script>
                            <c:set var="mensagemErro" scope="session" value=""/>
                        </c:if>

                        <!-- Botões para excluir filme -->
                        <form action="${pageContext.request.contextPath}/gerente/excluirFilme" method="post">
                            <input type="hidden" id="modal-filmeId-excluir" name="filmeId" value=""/>
                            <input type="submit" name="filmeId" class="delete-button" value="Excluir Filme"
                                   onclick="return confirm('Tem certeza que deseja excluir este filme?')"/>
                        </form>

                        <!-- Comentários com possibilidade de exclusão -->
                        <h3>Comentários:</h3>
                        <div id="comentarios-gerente" class="comentarios-lista">
                            <!-- Os comentários serão carregados via Javascript -->7
                        </div>
                    </div>
                </div>
            </div>
        </c:otherwise>

    </c:choose>

</main>
</body>
</html>
