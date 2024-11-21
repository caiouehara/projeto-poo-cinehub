document.addEventListener('DOMContentLoaded', function () {
    const modal = document.getElementById('modal');
    const closeModalBtn = document.getElementById('close-modal');
    const verMaisButtons = document.querySelectorAll('.ver-mais-btn');

    verMaisButtons.forEach(button => {
        button.addEventListener('click', function (event) {
            event.preventDefault();

            // Get data attributes from the clicked button
            const filmeId = this.getAttribute('data-id');
            const imageSrc = this.getAttribute('data-image');
            const title = this.getAttribute('data-title');
            const description = this.getAttribute('data-description');
            const year = this.getAttribute('data-year');
            const rating = this.getAttribute('data-rating');
            const rent = this.getAttribute('data-rent');
            const price = this.getAttribute('data-price');

            // Populate the modal fields
            document.getElementById('modal-image').src = imageSrc;
            document.getElementById('modal-title').textContent = title;
            document.getElementById('modal-description').textContent = description;
            document.getElementById('modal-year').textContent = 'Ano: ' + year;
            document.getElementById('modal-rent').textContent = rent;
            document.getElementById('modal-price').textContent = price;
            // If you have a rating display, set it here

            // Set the filmeId in the hidden inputs
            document.getElementById('avaliacao-filmeId').value = filmeId;
            document.getElementById('comentario-filmeId').value = filmeId;

            // Show the modal
            modal.style.display = 'flex';

            // Load comments and ratings via AJAX if needed
            // Example: fetch comments from server using filmeId

            // Inside the click event listener for 'Ver mais' buttons
            fetch(`${contextPath}/getFilmeDetalhes?filmeId=${filmeId}`)
                .then(response => response.json())
                .then(data => {
                    console.log(data)
                    // Populate comments
                    const comentariosDiv = document.getElementById('comentarios');
                    comentariosDiv.innerHTML = ''; // Clear previous comments

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

                    // Similarly, populate ratings if needed
                });

        });
    });

    // Close modal when 'X' button is clicked
    closeModalBtn.addEventListener('click', function () {
        modal.style.display = 'none';
    });

    // Close modal when clicking outside the modal content
    window.addEventListener('click', function (event) {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    });
});
