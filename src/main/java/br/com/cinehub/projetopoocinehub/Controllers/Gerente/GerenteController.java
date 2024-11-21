package br.com.cinehub.projetopoocinehub.Controllers.Gerente;

import br.com.cinehub.projetopoocinehub.Models.Filmes.Filme;
import br.com.cinehub.projetopoocinehub.Models.Filmes.FilmesModel;
import br.com.cinehub.projetopoocinehub.Models.User.CadastroModel;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet(name = "gerente", urlPatterns = {"/gerente", "/gerente/adicionarFilme"})
@MultipartConfig
public class GerenteController extends HttpServlet {
    private FilmesModel filmesModel;
    private CadastroModel cadastroModel;
    private ServletContext context;

    @Override
    public void init() throws ServletException {
        context = getServletContext();
        filmesModel = (FilmesModel) context.getAttribute("filmesModel");
        cadastroModel = (CadastroModel) context.getAttribute("cadastroModel");

        if (filmesModel == null || cadastroModel == null) {
            throw new ServletException("Models not initialized!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Encaminhar para o JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/gerente/gerenteHome.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lidar com a adição de um novo filme
        String action = request.getServletPath();
        if ("/gerente/adicionarFilme".equals(action)) {
            adicionarFilme(request, response);
        }
    }

    private void adicionarFilme(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obter dados do formulário
        String titulo = request.getParameter("titulo");
        int ano = Integer.parseInt(request.getParameter("ano"));
        String sinopse = request.getParameter("sinopse");
        double avaliacao = Double.parseDouble(request.getParameter("avaliacao"));
        double duracao = Double.parseDouble(request.getParameter("duracao"));
        double precoCompra = Double.parseDouble(request.getParameter("precoCompra"));
        double precoAluguel = Double.parseDouble(request.getParameter("precoAluguel"));
        int diasAluguel = Integer.parseInt(request.getParameter("diasAluguel"));

        // Lidar com o upload da imagem
        Part imagemPart = request.getPart("imagem");
        String imagemFileName = Paths.get(imagemPart.getSubmittedFileName()).getFileName().toString();
        String uploadPath = context.getRealPath("/img/films/");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();
        String imagemPath = uploadPath + File.separator + imagemFileName;
        imagemPart.write(imagemPath);

        // Criar um novo filme
        Filme novoFilme = new Filme();
        novoFilme.setTituloFilme(titulo);
        novoFilme.setAnoFilme(ano);
        novoFilme.setSinopseFilme(sinopse);
        novoFilme.setAvaliacaoFilme(avaliacao);
        novoFilme.setDuracaoFilme(duracao);
        novoFilme.setPrecoFilmeCompra(precoCompra);
        novoFilme.setPrecoFilmeAluguel(precoAluguel);
        novoFilme.setDiasAluguel(diasAluguel);
        novoFilme.setImagem(imagemFileName);

        // Adicionar o filme ao modelo
        filmesModel.adicionarFilme(novoFilme);

        // Redirecionar de volta para o dashboard do gerente
        response.sendRedirect(request.getContextPath() + "/gerente");
    }

    private void editarFilme(HttpServletRequest request, HttpServletResponse response){

    }
}
