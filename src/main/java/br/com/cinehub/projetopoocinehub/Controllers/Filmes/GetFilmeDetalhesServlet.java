package br.com.cinehub.projetopoocinehub.Controllers.Filmes;

import br.com.cinehub.projetopoocinehub.Models.Filmes.Filme;
import br.com.cinehub.projetopoocinehub.Models.Filmes.FilmesModel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/getFilmeDetalhes")
public class GetFilmeDetalhesServlet extends HttpServlet {
    private FilmesModel filmesModel;

    @Override
    public void init() throws ServletException {
        filmesModel = (FilmesModel) getServletContext().getAttribute("filmesModel");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String filmeId = request.getParameter("filmeId");
        Filme filme = filmesModel.buscarFilmePorId(filmeId);

        if (filme != null) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

            String jsonResponse = mapper.writeValueAsString(filme);
            response.setContentType("application/json");
            response.getWriter().write(jsonResponse);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
