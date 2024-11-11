package br.com.cinehub.projetopoocinehub.Controllers.Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/gerente/*") // Intercepta todas as requisições para /gerente/*
public class GerenteFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialização, se necessário
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Converte para HttpServletRequest e HttpServletResponse
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Obtém o contexto e a sessão
        String contextPath = httpRequest.getContextPath();
        HttpSession session = httpRequest.getSession(false);

        // Verifica se o usuário está logado e se é um gerente
        if (session != null && "Gerente".equals(session.getAttribute("usuario"))) {
            // Usuário é um gerente, continua a requisição
            chain.doFilter(request, response);
        } else {
            // Usuário não é um gerente ou não está logado, redireciona para a página de acesso negado ou login

            // Encaminha a requisição para o arquivo JSP
            response.setContentType("text/html");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/gerente/feedbacks/home.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void destroy() {
        // Limpeza, se necessário
    }
}
