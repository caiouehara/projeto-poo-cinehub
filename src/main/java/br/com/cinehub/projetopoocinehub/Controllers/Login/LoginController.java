package br.com.cinehub.projetopoocinehub.Controllers.Login;

import br.com.cinehub.projetopoocinehub.Models.User.CadastroModel;
import br.com.cinehub.projetopoocinehub.Models.User.Usuario;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "/user/login", value = "/user/login")
public class LoginController extends HttpServlet {
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
        // Definir o encoding
        response.setContentType("text/html; charset=UTF-8");
        // Encaminha a requisição para o arquivo JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/user/login.jsp");
        dispatcher.forward(request, response);
    }

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
