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
/**
 * Classe que implementa o {@link ServletContextListener} e é responsável 
 * por inicializar e configurar os modelos necessários para a aplicação 
 * quando o contexto da aplicação é inicializado.
 * 
 * Essa classe é configurada como um ouvinte de contexto, que é invocado
 * quando o contexto do servlet (a aplicação web) é iniciado ou destruído.
 */
@WebListener
public class AppContextListener implements ServletContextListener {

    /**
     * Método chamado quando o contexto da aplicação é inicializado.
     * Esse método cria e configura os modelos necessários para o funcionamento
     * da aplicação, como o modelo de cadastro, filmes, compras, aluguel e estatísticas,
     * e os adiciona ao contexto do servlet para que possam ser acessados em toda a aplicação.
     * 
     * @param sce O evento de inicialização do contexto da aplicação.
     */
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

    /**
     * Método chamado quando o contexto da aplicação é destruído.
     * Esse método pode ser utilizado para liberar recursos, caso necessário.
     * 
     * @param sce O evento de destruição do contexto da aplicação.
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Limpeza de recursos, se necessário
    }
}

