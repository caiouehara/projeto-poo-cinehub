package br.com.cinehub.projetopoocinehub.Controllers.Filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*") // Intercepta todas as requisições
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialização, se necessário
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Converte para HttpServletRequest e HttpServletResponse
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Obtém o URI da requisição
        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();

        // Define as URLs que devem ser excluídas da filtragem (recursos públicos)
        String[] excludedUrls = new String[] {
            contextPath + "/user/login",
            contextPath + "/cadastro",
            contextPath + "/css/",
            contextPath + "/js/",
            contextPath + "/img/",
            contextPath + "/images/",
            contextPath + "/public/",
            contextPath + "/getFilmeDetalhes",
            // Adicione outras URLs ou caminhos de recursos públicos conforme necessário
        };

        // Verifica se a URI da requisição está na lista de URLs excluídas
        boolean isExcluded = false;
        for (String url : excludedUrls) {
            if (requestURI.startsWith(url)) {
                isExcluded = true;
                break;
            }
        }

        if (isExcluded) {
            // Prossegue sem autenticação
            chain.doFilter(request, response);
        } else {
            // Verifica se o usuário está logado
            HttpSession session = httpRequest.getSession(false);
            if (session != null && session.getAttribute("usuario") != null) {
                // Usuário está logado, continua a requisição
                chain.doFilter(request, response);
            } else {
                // Usuário não está logado, redireciona para a página de login
                httpResponse.sendRedirect(contextPath + "/user/login");
            }
        }
    }

    @Override
    public void destroy() {
        // Limpeza, se necessário
    }
}
