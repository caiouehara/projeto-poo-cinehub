package br.com.cinehub.projetopoocinehub.Controllers.Login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
/**
 * Classe que representa o controlador de logout da aplicação.
 * 
 * O {@link LogoutController} é um servlet responsável por gerenciar
 * o processo de logout dos usuários, invalidando a sessão ativa e redirecionando
 * o usuário para a página de login após o logout.
 * 
 * A classe é configurada para mapear a URL "/logout" e, ao ser acessada, 
 * executa o processo de logout da aplicação.
 */
@WebServlet(name = "logout", value = "/logout")
public class LogoutController extends HttpServlet {

    /**
     * Método chamado para tratar requisições HTTP do tipo GET.
     * Esse método invalida a sessão do usuário, efetivando o logout,
     * e então redireciona o usuário para a página de login.
     *
     * @param request O objeto {@link HttpServletRequest} que contém as informações
     *                da requisição HTTP.
     * @param response O objeto {@link HttpServletResponse} usado para enviar
     *                 a resposta ao cliente.
     * @throws ServletException Se ocorrer um erro durante o processamento
     *                          da requisição.
     * @throws IOException Se ocorrer um erro de entrada/saída durante o
     *                     processamento da requisição ou resposta.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Invalida a sessão
        }
        response.sendRedirect(request.getContextPath() + "/user/login");
    }
}
