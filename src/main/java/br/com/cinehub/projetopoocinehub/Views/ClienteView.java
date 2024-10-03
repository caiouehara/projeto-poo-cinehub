package br.com.cinehub.projetopoocinehub.Views;

import java.io.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "cliente", value = "/cliente")
public class ClienteView extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        // Define um atributo para ser usado no JSP
        request.setAttribute("message", message);

        // Encaminha a requisição para o arquivo JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("app/cliente.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém os dados do formulário
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String confirmarSenha = request.getParameter("confirmarSenha");

        // Validação básica
        if (!senha.equals(confirmarSenha)) {
            request.setAttribute("errorMessage", "As senhas não coincidem.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroUsuario.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Aqui você adicionaria a lógica para salvar o usuário no banco de dados
        // Por exemplo:
        // Usuario novoUsuario = new Usuario(nome, email, senhaHash);
        // usuarioDAO.salvar(novoUsuario);

        // Redireciona para uma página de sucesso ou login
        response.sendRedirect("login.jsp");
    }

    public void destroy() {
    }
}
