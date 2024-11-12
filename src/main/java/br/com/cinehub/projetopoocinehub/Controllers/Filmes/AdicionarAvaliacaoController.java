package br.com.cinehub.projetopoocinehub.Controllers.Filmes;

import br.com.cinehub.projetopoocinehub.Models.Filmes.Filme;
import br.com.cinehub.projetopoocinehub.Models.Filmes.FilmesModel;
import br.com.cinehub.projetopoocinehub.Models.Filmes.Avaliacao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/adicionarAvaliacao")
public class AdicionarAvaliacaoController extends HttpServlet {
    private FilmesModel filmesModel;

    @Override
    public void init() throws ServletException {
        filmesModel = (FilmesModel) getServletContext().getAttribute("filmesModel");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filmeId = request.getParameter("filmeId");
        double nota = Double.parseDouble(request.getParameter("nota"));
        HttpSession session = request.getSession();
        String usuarioId = (String) session.getAttribute("usuarioId");

        Avaliacao avaliacao = new Avaliacao(usuarioId, filmeId, nota);

        Filme filme = filmesModel.buscarFilmePorId(filmeId);
        filme.adicionarAvaliacao(avaliacao);

        // Redirecionar de volta para a página de detalhes do filme
        response.sendRedirect(request.getContextPath() + "/home");
    }
}
