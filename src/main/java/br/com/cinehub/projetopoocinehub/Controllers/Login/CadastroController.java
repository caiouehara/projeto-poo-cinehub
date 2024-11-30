package br.com.cinehub.projetopoocinehub.Controllers.Login;

import br.com.cinehub.projetopoocinehub.Models.User.CadastroModel;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
/**
 * Controlador responsável pelo processo de cadastro de novos usuários.
 * 
 * A classe {@link CadastroController} gerencia o processo de cadastro de usuários,
 * seja como "Cliente" ou "Gerente". Ela trata tanto a exibição do formulário de cadastro,
 * quanto o processamento e validação das informações enviadas.
 * 
 * O controlador utiliza o modelo {@link CadastroModel} para adicionar novos usuários à aplicação.
 */
@WebServlet(name = "cadastro", value = "/cadastro")
public class CadastroController extends HttpServlet {
    private CadastroModel cadastro;

    /**
     * Inicializa o controlador, recuperando o modelo {@link CadastroModel} do ServletContext.
     * 
     * @throws ServletException Se o modelo {@link CadastroModel} não estiver inicializado.
     */
    @Override
    public void init() throws ServletException {
        // Recupera o CadastroModel do ServletContext
        cadastro = (CadastroModel) getServletContext().getAttribute("cadastroModel");
        if (cadastro == null) {
            throw new ServletException("CadastroModel não foi inicializado!");
        }
    }

    /**
     * Método chamado para tratar requisições HTTP do tipo GET.
     * Este método exibe o formulário de cadastro de um novo usuário.
     * 
     * @param request O objeto {@link HttpServletRequest} que contém as informações da requisição.
     * @param response O objeto {@link HttpServletResponse} usado para enviar a resposta ao cliente.
     * @throws ServletException Se ocorrer um erro ao encaminhar a requisição para a página de cadastro.
     * @throws IOException Se ocorrer um erro de entrada/saída ao processar a requisição.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Encaminha a requisição para o arquivo JSP
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/user/cadastro.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Método chamado para tratar requisições HTTP do tipo POST.
     * Este método processa os dados do formulário de cadastro e adiciona o usuário ao sistema.
     * 
     * @param request O objeto {@link HttpServletRequest} que contém as informações da requisição.
     * @param response O objeto {@link HttpServletResponse} usado para enviar a resposta ao cliente.
     * @throws ServletException Se ocorrer um erro durante o processamento da requisição.
     * @throws IOException Se ocorrer um erro de entrada/saída durante o processamento da requisição ou resposta.
     */
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
