package br.com.cinehub.projetopoocinehub.Controllers.Home;

import br.com.cinehub.projetopoocinehub.Models.Filmes.Filme;
import br.com.cinehub.projetopoocinehub.Models.Filmes.FilmesModel;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
/**
 * Controlador responsável pela exibição da página inicial da aplicação.
 * 
 * A classe {@link HomeController} gerencia a lógica de carregamento e exibição dos filmes
 * na página inicial do sistema. Ela recupera a lista de filmes do modelo {@link FilmesModel}
 * e os envia para a visualização JSP.
 */
@WebServlet(name = "home", value = "/home")
public class HomeController extends HttpServlet {
    /**
     * Recupera a lista de filmes disponíveis no sistema.
     * 
     * Este método acessa o modelo {@link FilmesModel} para obter todos os filmes cadastrados.
     * 
     * @return Lista de filmes disponíveis.
     */
    public static List<Filme> carregarFilmes() {
        return FilmesModel.getListaFilmes();
    }

    /**
     * Método chamado para tratar requisições HTTP do tipo GET.
     * Este método carrega a lista de filmes do sistema e encaminha a requisição
     * para o arquivo JSP de exibição da página inicial.
     * 
     * @param request O objeto {@link HttpServletRequest} que contém as informações da requisição.
     * @param response O objeto {@link HttpServletResponse} usado para enviar a resposta ao cliente.
     * @throws IOException Se ocorrer um erro de entrada/saída ao processar a requisição.
     * @throws ServletException Se ocorrer um erro ao encaminhar a requisição para o arquivo JSP.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        // Carrega a lista de filmes de Models
        List<Filme> filmes = HomeController.carregarFilmes();

        // Define um atributo para ser usado no JSP
        if (filmes != null) {
            request.setAttribute("filmes", filmes);
        } else {
            System.out.println("Erro em carregar os filmes");
        }

        // Encaminha a requisição para o arquivo JSP
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/home.jsp");
        dispatcher.forward(request, response);
    }
}
