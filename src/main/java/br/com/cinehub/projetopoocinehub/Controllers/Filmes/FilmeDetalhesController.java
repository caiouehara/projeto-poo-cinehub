package br.com.cinehub.projetopoocinehub.Controllers.Filmes;

import br.com.cinehub.projetopoocinehub.Models.Filmes.Avaliacao;
import br.com.cinehub.projetopoocinehub.Models.Filmes.Comentario;
import br.com.cinehub.projetopoocinehub.Models.Filmes.Filme;
import br.com.cinehub.projetopoocinehub.Models.Filmes.FilmesModel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "filmeDetalhes", urlPatterns = {"/getFilmeDetalhes", "/adicionarComentario", "/adicionarAvaliacao" })
@MultipartConfig
public class FilmeDetalhesController extends HttpServlet {
    private FilmesModel filmesModel;

    @Override
    public void init() throws ServletException {
        filmesModel = (FilmesModel) getServletContext().getAttribute("filmesModel");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String filmeId = request.getParameter("filmeId");
        Filme filme = filmesModel.buscarFilmePorId(filmeId);

        if (filme != null) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

            String jsonResponse = mapper.writeValueAsString(filme);
            response.setContentType("application/json");
            response.getWriter().write(jsonResponse);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lidar com a adição de um novo filme
        String action = request.getServletPath();

        switch (action) {
            case "/adicionarComentario":
                adicionarComentario(request, response);
                break;
            case "/adicionarAvaliacao":
                adicionarAvaliacao(request, response);
                break;
        }
    }

    protected void adicionarComentario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filmeId = request.getParameter("filmeId");
        String texto = request.getParameter("texto");
        HttpSession session = request.getSession();
        String usuarioId = (String) session.getAttribute("usuarioId");

        Comentario comentario = new Comentario(usuarioId, filmeId, texto);

        Filme filme = filmesModel.buscarFilmePorId(filmeId);
        filme.adicionarComentario(comentario);

        response.sendRedirect(request.getContextPath() + "/home");
    }

    protected void adicionarAvaliacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
