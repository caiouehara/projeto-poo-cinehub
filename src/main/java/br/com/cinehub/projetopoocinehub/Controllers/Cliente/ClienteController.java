package br.com.cinehub.projetopoocinehub.Controllers.Cliente;

import br.com.cinehub.projetopoocinehub.Models.Aluguel.Aluguel;
import br.com.cinehub.projetopoocinehub.Models.Aluguel.AluguelFilmeData;
import br.com.cinehub.projetopoocinehub.Models.Aluguel.AluguelModel;
import br.com.cinehub.projetopoocinehub.Models.Compras.Compra;
import br.com.cinehub.projetopoocinehub.Models.Compras.CompraFilmeData;
import br.com.cinehub.projetopoocinehub.Models.Compras.CompraModel;
import br.com.cinehub.projetopoocinehub.Models.User.CadastroModel;

import br.com.cinehub.projetopoocinehub.Models.Filmes.Filme;
import br.com.cinehub.projetopoocinehub.Models.Filmes.FilmesModel;
import br.com.cinehub.projetopoocinehub.Models.User.Tipos.Cliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Servlet responsável por gerenciar as operações relacionadas ao cliente.
 * Este servlet lida com a exibição das informações do cliente, incluindo seus aluguéis e filmes alugados.
 */
@WebServlet(name = "cliente", value = {"/cliente","/cliente/comprar", "/cliente/alugar"})
public class ClienteController extends HttpServlet {

    private static final int DIAS_ALUGUEL = 7;

    /**
     * Modelo para manipulação dos dados de aluguéis.
     */
    private AluguelModel aluguelModel;

    /**
     * Modelo para manipulação dos dados dos filmes.
     */
    private FilmesModel filmesModel;

    /**
     * Modelo para manipulação dos dados de compras.
     */
    private CompraModel compraModel;
    /**
     * Modelo para manipulação dos dados de cadastro dos clientes.
     * Supondo que CadastroModel gerencia clientes.
     */
    private CadastroModel cadastroModel;

    /**
     * Método de inicialização do servlet.
     * Inicializa os modelos necessários utilizando o contexto do servlet.
     *
     * @throws ServletException se ocorrer um erro durante a inicialização.
     */
    @Override
    public void init() throws ServletException {
        super.init();
        // Inicializa os modelos com o contexto do servlet
        aluguelModel = new AluguelModel(getServletContext());
        filmesModel = new FilmesModel(getServletContext());
        cadastroModel = new CadastroModel(getServletContext());
        compraModel = new CompraModel(getServletContext());
    }

    /**
     * Trata as requisições GET para o servlet.
     * Verifica se o cliente está autenticado, busca suas informações,
     * seus aluguéis e os filmes correspondentes, e encaminha para a página JSP.
     *
     * @param request  O objeto HttpServletRequest que contém a requisição do cliente.
     * @param response O objeto HttpServletResponse que contém a resposta do servlet.
     * @throws ServletException se ocorrer um erro no processamento do servlet.
     * @throws IOException      se ocorrer um erro de entrada/saída.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém a sessão e verifica se o cliente está logado
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            // Redireciona para a página de login
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        String email = (String) session.getAttribute("email");

        // Busca os detalhes do cliente usando o email
        Cliente cliente = (Cliente) cadastroModel.buscarClientePorEmail(email);
        if (cliente == null) {
            // Redireciona para uma página de erro se o cliente não for encontrado
            response.sendRedirect(request.getContextPath() + "/error");
            return;
        }

        // Busca os aluguéis e compras do cliente
        List<Aluguel> alugueisDoCliente = AluguelModel.buscarAlugueisPorCliente(email);
        List<Compra> comprasDoCliente = CompraModel.buscarComprasPorCliente(email);

        // Prepara a lista de AluguelFilmeData combinando aluguéis e filmes
        List<AluguelFilmeData> alugueisFilmes = new ArrayList<>();
        for (Aluguel aluguel : alugueisDoCliente) {
            Filme filme = FilmesModel.buscarFilmePorId(aluguel.getFilmeId());
            if (filme != null) {
                alugueisFilmes.add(new AluguelFilmeData(aluguel, filme));
            }
        }

        // Prepara a lista de CompraFilmeData combinando compras e filmes
        List<CompraFilmeData> comprasFilmes = new ArrayList<>();
        for (Compra compra : comprasDoCliente) {
            Filme filme = FilmesModel.buscarFilmePorId(compra.getFilmeId());
            if (filme != null) {
                comprasFilmes.add(new CompraFilmeData(compra, filme));
            }
        }

        // Define os atributos para a JSP
        request.setAttribute("cliente", cliente);
        request.setAttribute("alugueisFilmes", alugueisFilmes);
        request.setAttribute("comprasFilmes", comprasFilmes);

        // Encaminha a requisição para a página JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/cliente/cliente.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/cliente/comprar".equals(path)) {
            processarCompra(request, response);
        }
        else if ("/cliente/alugar".equals(path)) {
            processarAluguel(request,response);
        }
    }

    private void processarCompra(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"success\": false}");
            return;
        }

        String email = (String) session.getAttribute("email");
        Cliente cliente = (Cliente) cadastroModel.buscarClientePorEmail(email);
        if (cliente == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"success\": false}");
            return;
        }

        String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = (ObjectNode) mapper.readTree(requestBody);
        String filmeId = json.get("filmeId").asText();

        // Verificar se o filme já foi comprado ou alugado pelo cliente
        List<Compra> comprasDoCliente = compraModel.buscarComprasPorCliente(email);
        for (Compra compra : comprasDoCliente) {
            if (compra.getFilmeId().equals(filmeId)) {
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                response.getWriter().write("{\"success\": false, \"message\": \"Filme já comprado.\"}");
                return;
            }
        }

        List<Aluguel> alugueisDoCliente = aluguelModel.buscarAlugueisPorCliente(email);
        for (Aluguel aluguel : alugueisDoCliente) {
            if (aluguel.getFilmeId().equals(filmeId)) {
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                response.getWriter().write("{\"success\": false, \"message\": \"Filme já alugado.\"}");
                return;
            }
        }

        Filme filme = filmesModel.buscarFilmePorId(filmeId);
        if (filme == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"success\": false}");
            return;
        }

        Compra compra = new Compra(UUID.randomUUID().toString(), cliente.getEmail(), filmeId, new Date());
        compraModel.adicionarCompra(compra);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectNode jsonResponse = mapper.createObjectNode();
        jsonResponse.put("success", true);

        ObjectNode compraJson = mapper.createObjectNode();
        compraJson.put("dataCompra", new SimpleDateFormat("yyyy-MM-dd").format(compra.getDataCompra()));
        jsonResponse.set("compra", compraJson);

        ObjectNode filmeJson = mapper.createObjectNode();
        filmeJson.put("tituloFilme", filme.getTituloFilme());
        filmeJson.put("imagem", filme.getImagem());
        filmeJson.put("sinopseFilme", filme.getSinopseFilme());
        filmeJson.put("anoFilme", filme.getAnoFilme());
        filmeJson.put("avaliacaoFilme", filme.getAvaliacaoFilme());
        jsonResponse.set("filme", filmeJson);

        response.getWriter().write(jsonResponse.toString());
    }
    private void processarAluguel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"success\": false}");
            return;
        }

        String email = (String) session.getAttribute("email");
        Cliente cliente = (Cliente) cadastroModel.buscarClientePorEmail(email);
        if (cliente == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"success\": false}");
            return;
        }

        String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = (ObjectNode) mapper.readTree(requestBody);
        String filmeId = json.get("filmeId").asText();

        // Verificar se o filme já foi alugado pelo cliente
        List<Aluguel> alugueisDoCliente = aluguelModel.buscarAlugueisPorCliente(email);
        for (Aluguel aluguel : alugueisDoCliente) {
            if (aluguel.getFilmeId().equals(filmeId)) {
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                response.getWriter().write("{\"success\": false, \"message\": \"Filme já alugado.\"}");
                return;
            }
        }

        List<Compra> comprasDoCliente = compraModel.buscarComprasPorCliente(email);
        for (Compra compra : comprasDoCliente) {
            if (compra.getFilmeId().equals(filmeId)) {
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                response.getWriter().write("{\"success\": false, \"message\": \"Filme já comprado.\"}");
                return;
            }
        }

        Filme filme = filmesModel.buscarFilmePorId(filmeId);
        if (filme == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"success\": false}");
            return;
        }

        // Calcula a data de devolução
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, DIAS_ALUGUEL);
        Date dataDevolucao = calendar.getTime();

        Aluguel aluguel = new Aluguel(UUID.randomUUID().toString(), cliente.getEmail(), filmeId, new Date(), dataDevolucao);
        aluguelModel.adicionarAluguel(aluguel);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectNode jsonResponse = mapper.createObjectNode();
        jsonResponse.put("success", true);

        ObjectNode aluguelJson = mapper.createObjectNode();
        aluguelJson.put("dataAluguel", new SimpleDateFormat("yyyy-MM-dd").format(aluguel.getDataAluguel()));
        aluguelJson.put("dataDevolucao", new SimpleDateFormat("yyyy-MM-dd").format(aluguel.getDataDevolucao()));
        jsonResponse.set("aluguel", aluguelJson);

        ObjectNode filmeJson = mapper.createObjectNode();
        filmeJson.put("tituloFilme", filme.getTituloFilme());
        filmeJson.put("imagem", filme.getImagem());
        filmeJson.put("sinopseFilme", filme.getSinopseFilme());
        filmeJson.put("anoFilme", filme.getAnoFilme());
        filmeJson.put("avaliacaoFilme", filme.getAvaliacaoFilme());
        jsonResponse.set("filme", filmeJson);

        response.getWriter().write(jsonResponse.toString());
    }
}


