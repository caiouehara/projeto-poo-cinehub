document.addEventListener('DOMContentLoaded', function () {
    const modalGerente = document.getElementById('modalGerente');
    const closeModalBtn = document.getElementById('close-modal-Gerente');
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
            const rent = this.getAttribute('data-rent');
            const price = this.getAttribute('data-price');
            const rating = this.getAttribute('data-rating');

            // Populate the modal fields
            document.getElementById('modal-image-Gerente').src = imageSrc;
            document.getElementById('modal-title-Gerente').textContent = title;
            document.getElementById('modal-description-Gerente').textContent = description;
            document.getElementById('modal-year-Gerente').textContent = 'Ano: ' + year;
            document.getElementById('modal-rent-Gerente').textContent = 'Aluguel: R$' + rent;
            document.getElementById('modal-price-Gerente').textContent = 'Compra: R$' + price;
            document.getElementById('nota-final-Gerente').textContent = rating;

            // Show the modal
            modalGerente.style.display = 'flex'; // Show modal

            // You can add additional logic here to populate ratings or other fields
        });
    });

    // Close modal when 'X' button is clicked
    closeModalBtn.addEventListener('click', function () {
        modalGerente.style.display = 'none'; // Hide modal
    });

    // Close modal when clicking outside the modal content
    window.addEventListener('click', function (event) {
        if (event.target === modalGerente) {
            modalGerente.style.display = 'none'; // Hide modal when clicking outside
        }
    });
});
