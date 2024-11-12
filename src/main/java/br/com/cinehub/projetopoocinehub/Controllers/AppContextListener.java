package br.com.cinehub.projetopoocinehub.Controllers;

import br.com.cinehub.projetopoocinehub.Models.User.CadastroModel;
import br.com.cinehub.projetopoocinehub.Models.Filmes.FilmesModel;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        CadastroModel cadastroModel = new CadastroModel();
        context.setAttribute("cadastroModel", cadastroModel);

        FilmesModel filmesModel = new FilmesModel(context);
        context.setAttribute("filmesModel", filmesModel);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Limpeza de recursos, se necessário
    }
}

