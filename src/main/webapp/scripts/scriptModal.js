document.addEventListener('DOMContentLoaded', function () {
    // Seleciona todos os botões 'Ver Mais'
    const verMaisButtons = document.querySelectorAll('.ver-mais-btn');

    // Adiciona um evento de clique a cada botão 'Ver Mais'
    verMaisButtons.forEach(button => {
        button.addEventListener('click', function (event) {
            event.preventDefault();

            // Obtém atributos de dados do botão clicado
            const filmeId = this.getAttribute('data-id');
            const imageSrc = this.getAttribute('data-image');
            const title = this.getAttribute('data-title');
            const description = this.getAttribute('data-description');
            const year = this.getAttribute('data-year');
            const rent = this.getAttribute('data-rent');
            const price = this.getAttribute('data-price');
            const rating = this.getAttribute('data-rating');
            const duration = this.getAttribute('data-duration');

            // Verifica se o modal do gerente existe no DOM
            const modalGerente = document.getElementById('modalGerente');
            const modalCliente = document.getElementById('modalCliente');

            if (modalGerente) {
                // *** Lógica para o Gerente ***

                // Seleciona elementos do modal do gerente
                const closeModalBtn = document.getElementById('close-modal-Gerente');

                // Preenche os campos do modal do gerente
                document.getElementById('modal-filmeId').value = filmeId;
                document.getElementById('modal-image-Gerente').src = imageSrc;
                document.getElementById('modal-title-Gerente').textContent = title;
                document.getElementById('modal-description-Gerente').textContent = description;
                document.getElementById('modal-year-Gerente').textContent = 'Ano: ' + year;
                document.getElementById('modal-rent-Gerente').textContent = 'Aluguel: R$' + rent;
                document.getElementById('modal-price-Gerente').textContent = 'Compra: R$' + price;
                document.getElementById('nota-final-Gerente').textContent = rating;
                document.getElementById('modal-duration-Gerente').textContent = 'Duração(min): ' + duration;

                // Define o ID do filme nos campos ocultos para exclusão
                document.getElementById('modal-filmeId-excluir').value = filmeId;

                // Exibe o modal do gerente
                modalGerente.style.display = 'flex';

                // Evento para fechar o modal do gerente
                closeModalBtn.addEventListener('click', function () {
                    modalGerente.style.display = 'none';
                });

                // Fecha o modal ao clicar fora dele
                window.addEventListener('click', function (event) {
                    if (event.target === modalGerente) {
                        modalGerente.style.display = 'none';
                    }
                });

            } else if (modalCliente) {
                // *** Lógica para o Cliente ***

                // Seleciona elementos do modal do cliente
                const closeModalBtn = document.getElementById('close-modal');

                // Preenche os campos do modal do cliente
                document.getElementById('modal-image').src = imageSrc;
                document.getElementById('modal-title').textContent = title;
                document.getElementById('modal-description').textContent = description;
                document.getElementById('modal-year').textContent = 'Ano: ' + year;
                document.getElementById('nota-final').textContent = rating;
                document.getElementById('modal-rent').textContent = rent;
                document.getElementById('modal-price').textContent = price;
                document.getElementById('modal-duration').textContent = 'Duração(min): ' + duration;

                // Define o ID do filme nos campos ocultos
                document.getElementById('avaliacao-filmeId').value = filmeId;
                document.getElementById('comentario-filmeId').value = filmeId;

                // Exibe o modal do cliente
                modalCliente.style.display = 'flex';

                // Carrega comentários e avaliações via AJAX, se necessário
                fetch(`${contextPath}/getFilmeDetalhes?filmeId=${filmeId}`)
                    .then(response => response.json())
                    .then(data => {
                        console.log(data);
                        // Popula os comentários
                        const comentariosDiv = document.getElementById('comentarios');
                        comentariosDiv.innerHTML = ''; // Limpa comentários anteriores

                        if (data.comentarios && data.comentarios.length > 0) {
                            const ul = document.createElement('ul');
                            data.comentarios.forEach(comentario => {
                                const li = document.createElement('li');
                                li.innerHTML = `<strong>${comentario.usuarioId}</strong> em ${comentario.data}:<p>${comentario.texto}</p>`;
                                ul.appendChild(li);
                            });
                            comentariosDiv.appendChild(ul);
                        } else {
                            comentariosDiv.innerHTML = '<p>Não há comentários para este filme.</p>';
                        }

                        // Similarmente, pode-se popular avaliações, se necessário
                    });

                // Evento para fechar o modal do cliente
                closeModalBtn.addEventListener('click', function () {
                    modalCliente.style.display = 'none';
                });

                // Fecha o modal ao clicar fora dele
                window.addEventListener('click', function (event) {
                    if (event.target === modalCliente) {
                        modalCliente.style.display = 'none';
                    }
                });

            } else {
                // Caso nenhum modal seja encontrado, pode-se exibir uma mensagem de erro ou tomar outra ação
                console.error('Nenhum modal encontrado no DOM.');
            }
        });
    });
});
