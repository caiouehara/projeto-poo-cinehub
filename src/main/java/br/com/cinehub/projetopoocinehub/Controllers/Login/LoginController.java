package br.com.cinehub.projetopoocinehub.Controllers.Login;

import br.com.cinehub.projetopoocinehub.Models.User.CadastroModel;
import br.com.cinehub.projetopoocinehub.Models.User.Usuario;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
/**
 * Controlador responsável pelo processo de login de usuários na aplicação.
 * 
 * A classe {@link LoginController} lida com as requisições de login, tanto para exibir o formulário de login
 * quanto para processar as credenciais fornecidas e autenticar o usuário. Em caso de sucesso, o usuário é redirecionado
 * para a página principal, e em caso de falha, é exibida uma mensagem de erro.
 * 
 * O controlador utiliza o modelo {@link CadastroModel} para validar as credenciais do usuário.
 */
@WebServlet(name = "/user/login", value = "/user/login")
public class LoginController extends HttpServlet {
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
     * Este método exibe o formulário de login para o usuário.
     * 
     * @param request O objeto {@link HttpServletRequest} que contém as informações da requisição.
     * @param response O objeto {@link HttpServletResponse} usado para enviar a resposta ao cliente.
     * @throws ServletException Se ocorrer um erro ao encaminhar a requisição para a página de login.
     * @throws IOException Se ocorrer um erro de entrada/saída ao processar a requisição.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Definir o encoding
        response.setContentType("text/html; charset=UTF-8");
        // Encaminha a requisição para o arquivo JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/user/login.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Método chamado para tratar requisições HTTP do tipo POST.
     * Esse método processa o login do usuário, validando as credenciais e
     * iniciando uma nova sessão ou redirecionando para a página de login em caso de erro.
     * 
     * @param request O objeto {@link HttpServletRequest} que contém as informações da requisição.
     * @param response O objeto {@link HttpServletResponse} usado para enviar a resposta ao cliente.
     * @throws ServletException Se ocorrer um erro durante o processamento da requisição.
     * @throws IOException Se ocorrer um erro de entrada/saída durante o processamento da requisição ou resposta.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Definir o encoding
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        // Validação básica dos campos
        if (email == null || email.isEmpty() || senha == null || senha.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/user/login?erro=1");
            return;
        }

        // Valida o login e obtém o usuário
        Usuario usuario = cadastro.validarLoginUser(email, senha);

        // Invalida a sessão existente, se houver
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        if (usuario != null) {
            // Login bem-sucedido
            session = request.getSession(true);
            session.setAttribute("usuario", usuario.getTipoDeUsuario()); // "Cliente" ou "Gerente"
            session.setAttribute("email", email);
            session.setAttribute("nome", usuario.getNome()); // Armazenar o nome do usuário
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            // Dados inválidos
            response.sendRedirect(request.getContextPath() + "/user/login?erro=1");
        }
    }
}
