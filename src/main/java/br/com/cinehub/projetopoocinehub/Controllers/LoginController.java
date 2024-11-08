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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        // Valida o login e obtém o tipo de usuário
        String tipoUsuario = cadastro.validarLoginUser(email, senha);

        if ("Cliente".equals(tipoUsuario)) {
            // Logado como cliente
            HttpSession session = request.getSession();
            session.setAttribute("usuario", "Cliente");
            session.setAttribute("email", email);
            response.sendRedirect(request.getContextPath() + "/home.jsp");
        } else if ("Gerente".equals(tipoUsuario)) {
            // Logado como gerente
            HttpSession session = request.getSession();
            session.setAttribute("usuario", "Gerente");
            session.setAttribute("email", email);
            response.sendRedirect(request.getContextPath() + "/home.jsp");
        } else {
            // Dados inválidos
            response.sendRedirect(request.getContextPath() + "/user/login.jsp?erro=1");
        }
    }
}
