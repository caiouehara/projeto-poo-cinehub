<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nav.css">
    </head>

    <div class="nav">
        <!-- Header com navegação -->
        <header>
            <div class="header-container">
                <a href="#">
                    <img src="${pageContext.request.contextPath}/img/logo_netflix.png" alt="Logo Netflix" class="logo">
                </a>
                <i class="fas fa-bars menu-toggle"></i>
                <nav class="menu-nav">
                    <a href="#">Home</a>
                    <a href="#">Meus filmes</a>
                </nav>
            </div>
        </header>

        <!-- Script para o menu responsivo -->
        <script>
            const menuToggle = document.querySelector('.menu-toggle');
            const menuNav = document.querySelector('.menu-nav');

            menuToggle.addEventListener('click', () => {
                menuNav.classList.toggle('active');
            });
        </script>
    </div>
</html>
