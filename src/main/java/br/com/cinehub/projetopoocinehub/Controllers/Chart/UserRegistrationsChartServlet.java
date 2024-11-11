package br.com.cinehub.projetopoocinehub.Controllers.Chart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/chart/userRegistrations")
public class UserRegistrationsChartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Preparar conjunto de dados
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Dados de exemplo (Substitua pelos dados reais do seu modelo)
        dataset.addValue(10, "Registros", "Jan");
        dataset.addValue(15, "Registros", "Fev");
        dataset.addValue(20, "Registros", "Mar");
        dataset.addValue(25, "Registros", "Abr");
        dataset.addValue(30, "Registros", "Mai");

        // Criar gráfico
        JFreeChart chart = ChartFactory.createLineChart(
                "Novos Usuários por Mês", // Título do gráfico
                "Mês",                   // Rótulo do eixo X
                "Número de Usuários",    // Rótulo do eixo Y
                dataset
        );

        // Definir o tipo de conteúdo da resposta
        response.setContentType("image/png");

        // Escrever o gráfico como imagem PNG
        OutputStream out = response.getOutputStream();
        ChartUtils.writeChartAsPNG(out, chart, 600, 400);
        out.close();
    }
}
