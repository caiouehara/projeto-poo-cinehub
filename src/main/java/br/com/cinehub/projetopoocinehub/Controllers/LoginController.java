package br.com.cinehub.projetopoocinehub.Controllers;

import br.com.cinehub.projetopoocinehub.Models.User.CadastroModel;
import br.com.cinehub.projetopoocinehub.Models.User.Login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private CadastroModel cadastro;

    @Override
    public void init() throws ServletException {
        // Inicialize o objeto CadastroModel com dados de clientes e gerentes existentes
        cadastro = new CadastroModel();
        // Carregue os clientes e gerentes existentes (pode ser de um banco de dados ou fonte de dados)
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        if(Login.validarLoginCliente(email, senha, cadastro)) {
            // Logado como cliente
            HttpSession session = request.getSession();
            session.setAttribute("usuario", "Cliente");
            session.setAttribute("email", email);
            response.sendRedirect("paginaCliente.jsp");
        }
        else if(Login.validarLoginGerente(email, senha, cadastro)) {
            // Logado como gerente
            HttpSession session = request.getSession();
            session.setAttribute("usuario", "Gerente");
            session.setAttribute("email", email);
            response.sendRedirect(request.getContextPath() + "/pages/user/gerente/gerente.jsp");
        }
        else {
            // Dados inválidos
            response.sendRedirect(request.getContextPath() + "/pages/user/login.jsp?erro=1");
        }
    }
}
