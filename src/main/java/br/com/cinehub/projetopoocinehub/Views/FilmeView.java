package br.com.cinehub.projetopoocinehub.Views;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "client", value = "/client")
public class FilmeView extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        // Define um atributo para ser usado no JSP
        request.setAttribute("message", message);

        // Encaminha a requisição para o arquivo JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("filme.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}