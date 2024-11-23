package br.com.cinehub.projetopoocinehub.Controllers.Gerente;

import br.com.cinehub.projetopoocinehub.Models.Aluguel.AluguelModel;
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

@WebServlet(name = "gerente", urlPatterns = {"/gerente", "/gerente/adicionarFilme", "/gerente/editarFilme"})
@MultipartConfig
public class GerenteController extends HttpServlet {
    private FilmesModel filmesModel;
    private CadastroModel cadastroModel;
    private AluguelModel aluguelModel;
    private ServletContext context;

    @Override
    public void init() throws ServletException {
        context = getServletContext();
        filmesModel = (FilmesModel) context.getAttribute("filmesModel");
        cadastroModel = (CadastroModel) context.getAttribute("cadastroModel");
        aluguelModel = (AluguelModel) context.getAttribute("aluguelModel");

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
        } else if ("/gerente/editarFilme".equals(action)) {
            editarFilme(request, response);
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

    private void removerFilme(HttpServletRequest request, HttpServletResponse response) {

    }

    private void editarFilme(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obter dados do formulário
        String id = request.getParameter("filmeId");
        if (id == null || id.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID do filme é obrigatório.");
            return;
        }

        // Buscar o filme existente
        Filme filmeExistente = FilmesModel.buscarFilmePorId(id);
        if (filmeExistente == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Filme não encontrado.");
            return;
        }

        // Processar parâmetros, mantendo os valores existentes se estiverem vazios
        String titulo = request.getParameter("tituloFilme");
        if (titulo == null || titulo.trim().isEmpty()) {
            titulo = filmeExistente.getTituloFilme();
        }

        String sinopse = request.getParameter("sinopseFilme");
        if (sinopse == null || sinopse.trim().isEmpty()) {
            sinopse = filmeExistente.getSinopseFilme();
        }

        Integer ano = null;
        String anoParam = request.getParameter("anoFilme");
        if (anoParam == null || anoParam.trim().isEmpty()) {
            ano = filmeExistente.getAnoFilme();
        } else {
            try {
                ano = Integer.parseInt(anoParam);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ano inválido.");
                return;
            }
        }

        Double duracao = null;
        String duracaoParam = request.getParameter("duracaoFilme");
        if (duracaoParam == null || duracaoParam.trim().isEmpty()) {
            duracao = filmeExistente.getDuracaoFilme();
        } else {
            try {
                duracao = Double.parseDouble(duracaoParam);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Duração inválida.");
                return;
            }
        }

        Double precoCompra = null;
        String precoCompraParam = request.getParameter("precoCompra");
        if (precoCompraParam == null || precoCompraParam.trim().isEmpty()) {
            precoCompra = filmeExistente.getPrecoFilmeCompra();
        } else {
            try {
                precoCompra = Double.parseDouble(precoCompraParam);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Preço de compra inválido.");
                return;
            }
        }

        Double precoAluguel = null;
        String precoAluguelParam = request.getParameter("precoAluguel");
        if (precoAluguelParam == null || precoAluguelParam.trim().isEmpty()) {
            precoAluguel = filmeExistente.getPrecoFilmeAluguel();
        } else {
            try {
                precoAluguel = Double.parseDouble(precoAluguelParam);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Preço de aluguel inválido.");
                return;
            }
        }

        // Lidar com upload de nova imagem, se enviada
        String novaImagem = null;
        Part imagemPart = request.getPart("imagem");
        if (imagemPart != null && imagemPart.getSize() > 0) {
            String imagemFileName = Paths.get(imagemPart.getSubmittedFileName()).getFileName().toString();
            String uploadPath = context.getRealPath("/img/films/");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();
            String imagemPath = uploadPath + File.separator + imagemFileName;
            imagemPart.write(imagemPath);

            novaImagem = imagemFileName;
        } else {
            // Mantém a imagem existente se nenhuma nova imagem for enviada
            novaImagem = filmeExistente.getImagem();
        }

        // Atualizar o modelo com os novos valores ou valores existentes
        try {
            filmesModel.editarFilme(id, titulo, ano, sinopse, duracao, precoCompra, precoAluguel, novaImagem);
        } catch (IllegalArgumentException | IllegalStateException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            return;
        }

        // Redirecionar de volta a home
        response.sendRedirect(request.getContextPath() + "/home");
    }
}
