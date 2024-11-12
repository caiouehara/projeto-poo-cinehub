package br.com.cinehub.projetopoocinehub.Controllers.Chart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/chart/movieRentals")
public class MovieRentalsChartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Preparar conjunto de dados
        DefaultPieDataset dataset = new DefaultPieDataset();

        // Dados de exemplo (Substitua pelos dados reais do seu modelo)
        dataset.setValue("Ação", 50);
        dataset.setValue("Comédia", 30);
        dataset.setValue("Drama", 20);
        dataset.setValue("Ficção Científica", 40);

        // Criar gráfico
        JFreeChart chart = ChartFactory.createPieChart(
                "Aluguéis de Filmes por Gênero", // Título do gráfico
                dataset,
                true,  // Incluir legenda
                true,
                false
        );

        // Definir o tipo de conteúdo da resposta
        response.setContentType("image/png");

        // Escrever o gráfico como imagem PNG
        OutputStream out = response.getOutputStream();
        ChartUtils.writeChartAsPNG(out, chart, 600, 400);
        out.close();
    }
}
