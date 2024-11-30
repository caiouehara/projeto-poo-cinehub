package br.com.cinehub.projetopoocinehub.Controllers.Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
/**
 * Filtro para garantir que apenas usuários com a função de "Gerente" tenham acesso a URLs sob o caminho "/gerente/*".
 * 
 * Este filtro intercepta todas as requisições para o caminho "/gerente/*", verificando se o usuário está logado
 * e se possui a função "Gerente". Caso contrário, ele será redirecionado para uma página de acesso negado ou login.
 * 
 * A verificação é feita com base na sessão do usuário.
 */
@WebFilter("/gerente/*") // Intercepta todas as requisições para /gerente/*
public class GerenteFilter implements Filter {

    /**
     * Inicializa o filtro, permitindo qualquer configuração necessária antes do processamento das requisições.
     * 
     * @param filterConfig O objeto de configuração do filtro.
     * @throws ServletException Se ocorrer um erro ao inicializar o filtro.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialização, se necessário
    }

    /**
     * Filtra as requisições HTTP para garantir que apenas gerentes possam acessar URLs sob "/gerente/*".
     * Caso o usuário não esteja logado ou não tenha a função "Gerente", será redirecionado para uma página de acesso negado.
     * 
     * @param request O objeto {@link ServletRequest} que contém as informações da requisição.
     * @param response O objeto {@link ServletResponse} que será enviado ao cliente.
     * @param chain A cadeia de filtros para permitir que a requisição continue, se necessário.
     * @throws IOException Se ocorrer um erro de entrada/saída ao processar a requisição.
     * @throws ServletException Se ocorrer um erro durante a execução do filtro.
     */
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

    /**
     * Limpeza de recursos do filtro, se necessário, após a execução do filtro.
     */
    @Override
    public void destroy() {
        // Limpeza, se necessário
    }
}
