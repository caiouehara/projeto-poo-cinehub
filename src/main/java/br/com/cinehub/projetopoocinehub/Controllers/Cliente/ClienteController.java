package br.com.cinehub.projetopoocinehub.Controllers.Cliente;

import br.com.cinehub.projetopoocinehub.Models.Aluguel.Aluguel;
import br.com.cinehub.projetopoocinehub.Models.Aluguel.AluguelFilmeData;
import br.com.cinehub.projetopoocinehub.Models.Aluguel.AluguelModel;
import br.com.cinehub.projetopoocinehub.Models.User.CadastroModel;
import br.com.cinehub.projetopoocinehub.Models.Filmes.Filme;
import br.com.cinehub.projetopoocinehub.Models.Filmes.FilmesModel;
import br.com.cinehub.projetopoocinehub.Models.User.Tipos.Cliente;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet responsável por gerenciar as operações relacionadas ao cliente.
 * Este servlet lida com a exibição das informações do cliente, incluindo seus aluguéis e filmes alugados.
 */
@WebServlet(name = "cliente", value = "/cliente")
public class ClienteController extends HttpServlet {

    /**
     * Modelo para manipulação dos dados de aluguéis.
     */
    private AluguelModel aluguelModel;

    /**
     * Modelo para manipulação dos dados dos filmes.
     */
    private FilmesModel filmesModel;

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

        // Busca os aluguéis do cliente
        List<Aluguel> alugueisDoCliente = AluguelModel.buscarAlugueisPorCliente(email);

        // Prepara a lista de AluguelFilmeData combinando aluguéis e filmes
        List<AluguelFilmeData> alugueisFilmes = new ArrayList<>();
        for (Aluguel aluguel : alugueisDoCliente) {
            Filme filme = FilmesModel.buscarFilmePorId(aluguel.getFilmeId());
            if (filme != null) {
                alugueisFilmes.add(new AluguelFilmeData(aluguel, filme));
            }
        }

        // Define os atributos para a JSP
        request.setAttribute("cliente", cliente);
        request.setAttribute("alugueisFilmes", alugueisFilmes);

        // Encaminha a requisição para a página JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/cliente/cliente.jsp");
        dispatcher.forward(request, response);
    }
}
