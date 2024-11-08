package br.com.cinehub.projetopoocinehub.Controllers;

import br.com.cinehub.projetopoocinehub.Models.User.CadastroModel;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.io.File;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Define o diretório de dados, por exemplo, WEB-INF/data
        String dataDir = sce.getServletContext().getRealPath("/WEB-INF/data");

        // Cria o diretório se não existir
        File dir = new File(dataDir);
        if (!dir.exists()) {
            boolean criado = dir.mkdirs();
            if (criado) {
                System.out.println("Diretório de dados criado em: " + dataDir);
            } else {
                System.out.println("Erro ao criar o diretório de dados em: " + dataDir);
            }
        }

        // Inicializa o CadastroModel
        CadastroModel cadastroModel = new CadastroModel(dataDir);

        // Armazena o CadastroModel no ServletContext para acesso global
        sce.getServletContext().setAttribute("cadastroModel", cadastroModel);
        System.out.println("CadastroModel inicializado e armazenado no ServletContext.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Limpeza de recursos, se necessário
        System.out.println("Aplicação sendo destruída. Recursos sendo liberados.");
    }
}
