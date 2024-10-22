package br.com.cinehub.projetopoocinehub.Controllers;

import java.io.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "user", value = "/user/")
public class ClienteController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Encaminha a requisição para o arquivo JSP
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/cadastroUsuario.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}
