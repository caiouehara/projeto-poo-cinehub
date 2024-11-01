<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nav.css">
    </head>

    <body>
        <!-- Header com navegação -->
        <header>
            <div class="header-container">
                <a href="#">
                    <img src="${pageContext.request.contextPath}/img/logo_netflix.png" alt="Logo Netflix" class="logo">
                </a>
                <i class="fas fa-bars menu-toggle"></i>
                <nav class="menu-nav">
                    <a href="#">Início</a>
                    <a href="#">Séries</a>
                    <a href="#">Filmes</a>
                    <a href="#">Documentários</a>
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
    </body>
</html>
