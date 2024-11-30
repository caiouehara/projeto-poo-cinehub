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
/**
 * Filtro de autenticação para proteger recursos que exigem que o usuário esteja logado.
 * 
 * Este filtro intercepta todas as requisições e verifica se o usuário está autenticado. Se a requisição for para
 * uma URL pública (como login ou recursos estáticos), a autenticação não é realizada. Caso contrário, verifica-se
 * se o usuário está logado por meio da sessão. Se não estiver, o usuário é redirecionado para a página de login.
 */
@WebFilter("/*") // Intercepta todas as requisições
public class AuthFilter implements Filter {

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
     * Filtra as requisições HTTP para garantir que apenas usuários autenticados possam acessar os recursos protegidos.
     * As requisições para URLs públicas (como login e recursos estáticos) são excluídas da filtragem.
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

    /**
     * Limpeza de recursos do filtro, se necessário, após a execução do filtro.
     */
    @Override
    public void destroy() {
        // Limpeza, se necessário
    }
}
