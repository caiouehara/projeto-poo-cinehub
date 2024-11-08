package br.com.cinehub.projetopoocinehub.Controllers;

import br.com.cinehub.projetopoocinehub.Models.User.CadastroModel;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "cadastro", value = "/cadastro")
public class CadastroController extends HttpServlet {
    private CadastroModel cadastro;

    @Override
    public void init() throws ServletException {
        // Recupera o CadastroModel do ServletContext
        cadastro = (CadastroModel) getServletContext().getAttribute("cadastroModel");
        if (cadastro == null) {
            throw new ServletException("CadastroModel não foi inicializado!");
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Encaminha a requisição para o arquivo JSP
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/user/cadastro.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém os parâmetros do formulário de cadastro
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String tipo = request.getParameter("tipo"); // "Cliente" ou "Gerente"

        // Obtém o tipo do usuário logado
        HttpSession session = request.getSession(false);
        String usuarioLogado = null;
        if (session != null) {
            usuarioLogado = (String) session.getAttribute("usuario");
        }

        boolean sucesso = cadastro.adicionarUser(nome, email, senha, tipo, usuarioLogado);

        if (sucesso) {
            if ("Gerente".equals(tipo)) {
                // Cadastro de Gerente realizado por um Gerente
                response.sendRedirect(request.getContextPath() + "/jsp/user/login.jsp?sucesso=1");
            } else {
                // Cadastro de Cliente realizado por qualquer um (Gerente ou não)
                response.sendRedirect(request.getContextPath() + "/jsp/user/login.jsp?sucesso=1");
            }
        } else {
            if ("Gerente".equals(tipo) && "Gerente".equals(usuarioLogado)) {
                // Falha no cadastro de Gerente
                response.sendRedirect(request.getContextPath() + "/jsp/user/login.jsp?erro=1");
            } else {
                // Falha no cadastro de Cliente ou tentativa de cadastrar Gerente sem autorização
                response.sendRedirect(request.getContextPath() + "/jsp/user/login.jsp?erro=1");
            }
        }
    }
}
