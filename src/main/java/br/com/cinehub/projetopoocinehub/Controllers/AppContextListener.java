package br.com.cinehub.projetopoocinehub.Controllers;

import br.com.cinehub.projetopoocinehub.Models.User.CadastroModel;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Inicializa o CadastroModel sem necessidade do dataDir do ServletContext
        CadastroModel cadastroModel = new CadastroModel();
        sce.getServletContext().setAttribute("cadastroModel", cadastroModel);
        System.out.println("CadastroModel inicializado e armazenado no ServletContext.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Limpeza de recursos, se necessário
    }
}

