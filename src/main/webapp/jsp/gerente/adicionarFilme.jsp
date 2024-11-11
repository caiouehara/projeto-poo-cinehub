<!-- Seção para Adicionar Novos Filmes -->
<h2>Adicionar Novo Filme</h2>
<form action="${pageContext.request.contextPath}/gerente/adicionarFilme" method="post" enctype="multipart/form-data">
    <label for="titulo">Título:</label>
    <input type="text" id="titulo" name="titulo" required>

    <label for="ano">Ano:</label>
    <input type="number" id="ano" name="ano" required>

    <label for="sinopse">Sinopse:</label>
    <textarea id="sinopse" name="sinopse" required></textarea>

    <label for="avaliacao">Avaliação:</label>
    <input type="number" step="0.1" id="avaliacao" name="avaliacao" required>

    <label for="duracao">Duração (minutos):</label>
    <input type="number" id="duracao" name="duracao" required>

    <label for="precoCompra">Preço de Compra:</label>
    <input type="number" step="0.01" id="precoCompra" name="precoCompra" required>

    <label for="precoAluguel">Preço de Aluguel:</label>
    <input type="number" step="0.01" id="precoAluguel" name="precoAluguel" required>

    <label for="diasAluguel">Dias de Aluguel:</label>
    <input type="number" id="diasAluguel" name="diasAluguel" required>

    <label for="imagem">Imagem:</label>
    <input type="file" id="imagem" name="imagem" accept="image/*" required>

    <input type="submit" value="Adicionar Filme">
</form>
