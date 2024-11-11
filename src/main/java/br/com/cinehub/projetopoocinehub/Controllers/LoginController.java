package br.com.cinehub.projetopoocinehub.Controllers;

import br.com.cinehub.projetopoocinehub.Models.User.CadastroModel;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "login", value = "/login")
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
        // Encaminha a requisição para o arquivo JSP
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/user/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Definir o encoding
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        // Validação básica dos campos
        if (email == null || email.isEmpty() || senha == null || senha.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/jsp/user/login.jsp?erro=1");
            return;
        }

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("usuario") != null) {
            // Usuário já está logado, redireciona para a página inicial
            response.sendRedirect(request.getContextPath() + "/jsp/home.jsp");
            return;
        }

        // Valida o login e obtém o tipo de usuário
        String tipoUsuario = cadastro.validarLoginUser(email, senha);

        if ("Cliente".equals(tipoUsuario)) {
            // Logado como cliente
            session = request.getSession(true);
            session.setAttribute("usuario", "Cliente");
            session.setAttribute("email", email);
            response.sendRedirect(request.getContextPath() + "/jsp/home.jsp");
        } else if ("Gerente".equals(tipoUsuario)) {
            // Logado como gerente
            session = request.getSession(true);
            session.setAttribute("usuario", "Gerente");
            session.setAttribute("email", email);
            response.sendRedirect(request.getContextPath() + "/jsp/home.jsp");
        } else {
            // Dados inválidos
            response.sendRedirect(request.getContextPath() + "/jsp/user/login.jsp?erro=1");
        }
    }

}
