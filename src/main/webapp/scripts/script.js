document.addEventListener("DOMContentLoaded", function() {
    // Selecionando todos os botões de "Ver mais"
    const buttons = document.querySelectorAll('a[filme-id]');

    // Adicionando o evento de clique
    buttons.forEach(button => {
        button.addEventListener('click', function(event) {
            event.preventDefault(); // Impede o comportamento padrão do link

            // Captura os dados do filme a partir dos atributos do botão clicado
            const imageSrc = this.getAttribute('filme-imagem');
            const title = this.getAttribute('filme-titulo');
            const description = this.getAttribute('filme-sinopse');
            const year = this.getAttribute('filme-ano');
            const rating = this.getAttribute('filme-nota');
            const price = this.getAttribute('valor-compra');
            const rent = this.getAttribute('valor-aluguel');
            const notafinal = this.getAttribute('nota-final');

            // Log para depuração
            console.log('Imagem:', imageSrc);
            console.log('Título:', title);
            console.log('Descrição:', description);
            console.log('Ano:', year);
            console.log('Avaliação:', rating);
            console.log('Preço de compra:', price);
            console.log('Preço de aluguel:', rent);

            // Preenche os campos do modal com as informações do filme
            document.getElementById('modal-image').src = imageSrc;
            document.getElementById('modal-title').textContent = title;
            document.getElementById('modal-description').textContent = description;
            document.getElementById('modal-year').textContent = `Ano: ${year}`;
            document.getElementById('nota-final').textContent = notafinal;
            document.getElementById('modal-price').textContent = price;
            document.getElementById('modal-rent').textContent = rent;

            // Preenche a avaliação (se necessário)
            const stars = document.querySelectorAll('.star-rating input');
            stars.forEach(star => {
                if (parseInt(star.value) <= parseInt(rating)) {
                    star.checked = true;
                } else {
                    star.checked = false;
                }
            });

            // Exibe o modal ao definir display como 'flex'
            document.getElementById('modal').style.display = 'flex'; // Usa 'flex' para centralizar
        });
    });

    // Fecha o modal quando o botão de fechar for clicado
    document.getElementById('close-modal').addEventListener('click', function() {
        document.getElementById('modal').style.display = 'none'; // Fecha o modal
    });

    // Fecha o modal quando clicar fora do conteúdo
    window.addEventListener('click', function(event) {
        const modal = document.getElementById('modal');
        if (event.target === modal) {
            modal.style.display = 'none'; // Fecha o modal ao clicar fora dele
        }
    });
});
