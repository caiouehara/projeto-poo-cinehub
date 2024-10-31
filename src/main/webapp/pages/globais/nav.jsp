<html>
    <style>
        .banner {
            background-color: red;
            color:white;
            font-family: arial, serif;
            font-size:24pt;
            font-weight : bold;
            letter-spacing:5pt;
        }

        .logo {
            background-color: #f0f0f0;
            margin: 0;
            padding: 10px;
        }

        .menu-nav {
            background-color: #dddddd;
            font-weight : bold;
        }
    </style>
    <div>
       <div class="banner">Catálogo de filmes</div>
           <header>
               <div>
                   <img src="${pageContext.request.contextPath}/img/logo_netflix.png" alt="Logo Netflix" class="logo">
                   <nav class="menu-nav">
                       <a href="#">Início</a>
                       <a href="#">Séries</a>
                       <a href="#">Filmes</a>
                       <a href="#">Documentários</a>
                   </nav>
               </div>
           </header>
     </div>
</html>
