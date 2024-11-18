package br.com.cinehub.projetopoocinehub.Controllers;

import br.com.cinehub.projetopoocinehub.Models.Aluguel.Aluguel;
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

@WebServlet(name = "cliente", value = "/cliente")
public class ClienteController extends HttpServlet {

    private AluguelModel aluguelModel;
    private FilmesModel filmesModel;
    private CadastroModel cadastroModel; // Supondo que CadastroModel gerencia clientes

    @Override
    public void init() throws ServletException {
        super.init();
        // Inicializa os modelos com o contexto do servlet
        aluguelModel = new AluguelModel(getServletContext());
        filmesModel = new FilmesModel(getServletContext());
        cadastroModel = new CadastroModel(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém a sessão e verifica se o cliente está logado
        HttpSession session = request.getSession(false);

        // Obtém o email do cliente da sessão
        String email = (String) session.getAttribute("email");

        // Busca os detalhes do cliente usando o email
        Cliente cliente = (Cliente) cadastroModel.buscarClientePorEmail(email);
        if (cliente == null) {
            // Redireciona para uma página de erro se o cliente não for encontrado
            response.sendRedirect(request.getContextPath() + "/error");
            return;
        }

        // Busca os aluguéis do cliente
        List<Aluguel> alugueisDoCliente = aluguelModel.buscarAlugueisPorCliente(email);

        // Prepara a lista de filmes alugados
        List<Filme> filmesAlugados = new ArrayList<>();
        for (Aluguel aluguel : alugueisDoCliente) {
            Filme filme = filmesModel.buscarFilmePorId(aluguel.getFilmeId());
            if (filme != null) {
                filmesAlugados.add(filme);
            }
        }

        // Define os atributos para a JSP
        request.setAttribute("cliente", cliente);
        request.setAttribute("filmesAlugados", filmesAlugados);
        request.setAttribute("alugueisDoCliente", alugueisDoCliente); // Se precisar das datas de aluguel

        // Encaminha a requisição para a página JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/cliente/cliente.jsp");
        dispatcher.forward(request, response);
    }
}
