package br.com.cinehub.projetopoocinehub.Controllers.Filmes;

import br.com.cinehub.projetopoocinehub.Models.Filmes.Comentario;
import br.com.cinehub.projetopoocinehub.Models.Filmes.Filme;
import br.com.cinehub.projetopoocinehub.Models.Filmes.FilmesModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/adicionarComentario")
public class AdicionarComentariosController extends HttpServlet {
    private FilmesModel filmesModel;

    @Override
    public void init() throws ServletException {
        filmesModel = (FilmesModel) getServletContext().getAttribute("filmesModel");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filmeId = request.getParameter("filmeId");
        String texto = request.getParameter("texto");
        HttpSession session = request.getSession();
        String usuarioId = (String) session.getAttribute("usuarioId");

        Comentario comentario = new Comentario(usuarioId, filmeId, texto);

        Filme filme = filmesModel.buscarFilmePorId(filmeId);
        filme.adicionarComentario(comentario);

        response.sendRedirect(request.getContextPath() + "/home");
    }
}