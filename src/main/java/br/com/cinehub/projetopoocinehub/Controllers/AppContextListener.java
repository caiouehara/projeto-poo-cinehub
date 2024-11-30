package br.com.cinehub.projetopoocinehub.Controllers;

import br.com.cinehub.projetopoocinehub.Models.Aluguel.AluguelModel;
import br.com.cinehub.projetopoocinehub.Models.Compras.CompraModel;
import br.com.cinehub.projetopoocinehub.Models.User.CadastroModel;
import br.com.cinehub.projetopoocinehub.Models.Filmes.FilmesModel;
import br.com.cinehub.projetopoocinehub.Models.Estatistica.Estatistica;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        CadastroModel cadastroModel = new CadastroModel(context);
        context.setAttribute("cadastroModel", cadastroModel);

        FilmesModel filmesModel = new FilmesModel(context);
        context.setAttribute("filmesModel", filmesModel);

        CompraModel comprasModel = new CompraModel(context);
        context.setAttribute("comprasModel", comprasModel);

        AluguelModel aluguelModel = new AluguelModel(context);
        context.setAttribute("aluguelModel", aluguelModel);

        // Inicializa o modelo de Estatísticas, passando os modelos
        Estatistica estatistica = new Estatistica(filmesModel, cadastroModel, aluguelModel, comprasModel);
        context.setAttribute("estatistica", estatistica);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Limpeza de recursos, se necessário
    }
}

