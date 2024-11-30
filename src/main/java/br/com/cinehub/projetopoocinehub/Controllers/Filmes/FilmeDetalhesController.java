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
/**
 * Controlador para a exibição de detalhes de filmes, adição de comentários e avaliações.
 * 
 * Este servlet é responsável por lidar com as requisições relacionadas aos detalhes de um filme, incluindo a exibição
 * de informações do filme, bem como a adição de comentários e avaliações. Ele oferece os seguintes endpoints:
 * - /getFilmeDetalhes: Para obter os detalhes de um filme.
 * - /adicionarComentario: Para adicionar um comentário a um filme.
 * - /adicionarAvaliacao: Para adicionar uma avaliação a um filme.
 */
@WebServlet(name = "filmeDetalhes", urlPatterns = {"/getFilmeDetalhes", "/adicionarComentario", "/adicionarAvaliacao" })
@MultipartConfig
public class FilmeDetalhesController extends HttpServlet {
    private FilmesModel filmesModel;

    /**
     * Inicializa o controlador e obtém o modelo de filmes do contexto do servlet.
     * 
     * @throws ServletException Se ocorrer um erro durante a inicialização do controlador.
     */
    @Override
    public void init() throws ServletException {
        filmesModel = (FilmesModel) getServletContext().getAttribute("filmesModel");
    }

    /**
     * Processa requisições GET para obter os detalhes de um filme.
     * 
     * @param request O objeto {@link HttpServletRequest} que contém a requisição do cliente.
     * @param response O objeto {@link HttpServletResponse} que será enviado ao cliente.
     * @throws ServletException Se ocorrer um erro ao processar a requisição.
     * @throws IOException Se ocorrer um erro ao escrever a resposta.
     */
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

    /**
     * Processa requisições POST para adicionar comentários ou avaliações a um filme.
     * 
     * @param request O objeto {@link HttpServletRequest} que contém a requisição do cliente.
     * @param response O objeto {@link HttpServletResponse} que será enviado ao cliente.
     * @throws ServletException Se ocorrer um erro ao processar a requisição.
     * @throws IOException Se ocorrer um erro ao escrever a resposta.
     */
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

    /**
     * Adiciona um comentário a um filme.
     * 
     * @param request O objeto {@link HttpServletRequest} que contém a requisição do cliente.
     * @param response O objeto {@link HttpServletResponse} que será enviado ao cliente.
     * @throws ServletException Se ocorrer um erro durante o processamento do comentário.
     * @throws IOException Se ocorrer um erro ao escrever a resposta.
     */
    protected void adicionarComentario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filmeId = request.getParameter("filmeId");
        String texto = request.getParameter("texto");
        HttpSession session = request.getSession();
        String nome = (String) session.getAttribute("nome");

        Comentario comentario = new Comentario(nome, filmeId, texto);

        Filme filme = filmesModel.buscarFilmePorId(filmeId);
        assert filme != null;
        filme.adicionarComentario(comentario);
        filmesModel.salvarFilmes();

        response.sendRedirect(request.getContextPath() + "/home");
    }

     /**
     * Adiciona uma avaliação a um filme.
     * 
     * @param request O objeto {@link HttpServletRequest} que contém a requisição do cliente.
     * @param response O objeto {@link HttpServletResponse} que será enviado ao cliente.
     * @throws ServletException Se ocorrer um erro durante o processamento da avaliação.
     * @throws IOException Se ocorrer um erro ao escrever a resposta.
     */
    protected void adicionarAvaliacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filmeId = request.getParameter("filmeId");
        double nota = Double.parseDouble(request.getParameter("nota"));
        HttpSession session = request.getSession();
        String nome = (String) session.getAttribute("nome");

        Avaliacao avaliacao = new Avaliacao(nome, filmeId, nota);

        Filme filme = filmesModel.buscarFilmePorId(filmeId);
        assert filme != null;
        filme.adicionarAvaliacao(avaliacao);
        filmesModel.salvarFilmes();

        // Redirecionar de volta para a página de detalhes do filme
        response.sendRedirect(request.getContextPath() + "/home");
    }
}
