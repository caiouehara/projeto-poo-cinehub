document.addEventListener("DOMContentLoaded", function() {
    // Selecionando todos os botões de "Ver mais"
    const buttons = document.querySelectorAll('a[data-id]');

    // Adicionando o evento de clique
    buttons.forEach(button => {
        button.addEventListener('click', function(event) {
            event.preventDefault(); // Impede o comportamento padrão do link

            // Captura os dados do filme a partir dos atributos data-* do botão clicado
            const imageSrc = this.getAttribute('data-image');
            const title = this.getAttribute('data-title');
            const description = this.getAttribute('data-description');
            const year = this.getAttribute('data-year');
            const rating = this.getAttribute('data-rating');

            // Preenche os campos do modal com as informações do filme
            document.getElementById('modal-image').src = imageSrc;
            document.getElementById('modal-title').textContent = title;
            document.getElementById('modal-description').textContent = description;
            document.getElementById('modal-year').textContent = `Ano: ${year}`;

            // Preenche a avaliação (se necessário)
            const stars = document.querySelectorAll('.star-rating input');
            stars.forEach(star => {
                if (parseInt(star.value) <= parseInt(rating)) {
                    star.checked = true;
                } else {
                    star.checked = false;
                }
            });

            // Exibe o modal ao definir display como 'block'
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
