// CadastroServlet.java
package br.com.cinehub.projetopoocinehub.Controllers;

import br.com.cinehub.projetopoocinehub.Models.User.CadastroModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/cadastro")
public class CadastroController extends HttpServlet {

    private CadastroModel cadastroModel;

    @Override
    public void init() throws ServletException {
        cadastroModel = new CadastroModel();
        // Inicialize com dados existentes se necessário
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String tipoUsuario = request.getParameter("tipoUsuario"); // "Cliente" ou "Gerente"

        HttpSession session = request.getSession(false);
        String usuarioLogado = (session != null) ? (String) session.getAttribute("usuario") : null;

        boolean cadastroRealizado = false;

        if ("Gerente".equalsIgnoreCase(tipoUsuario)) {
            // Cadastro de Gerente
            cadastroRealizado = cadastroModel.cadastroGerente(nome, email, senha, usuarioLogado);
            if (cadastroRealizado) {
                response.sendRedirect("cadastroSucesso.jsp?tipo=gerente");
            } else {
                response.sendRedirect("cadastro.jsp?erro=1&tipo=gerente");
            }
        } else {
            // Cadastro de Cliente
            cadastroRealizado = cadastroModel.cadastroCliente(nome, email, senha);
            if (cadastroRealizado) {
                response.sendRedirect("cadastroSucesso.jsp?tipo=cliente");
            } else {
                response.sendRedirect("cadastro.jsp?erro=1&tipo=cliente");
            }
        }
    }
}
