package br.com.cinehub.projetopoocinehub.Controllers;

import br.com.cinehub.projetopoocinehub.Models.Filme;
import br.com.cinehub.projetopoocinehub.Models.FilmesModel;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "home", value = "/home")
public class FilmesController extends HttpServlet {
    public static List<Filme> carregarFilmes() {
        return FilmesModel.getFilmes();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Carrega a lista de filmes de Models
        List<Filme> filmes = FilmesController.carregarFilmes();

        // Define um atributo para ser usado no JSP
        if (filmes != null) {
            request.setAttribute("filmes", filmes);
        } else {
            request.setAttribute("message", "Erro ao carregar os filmes.");
        }

        // Encaminha a requisição para o arquivo JSP
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/home.jsp");
        dispatcher.forward(request, response);
    }
}