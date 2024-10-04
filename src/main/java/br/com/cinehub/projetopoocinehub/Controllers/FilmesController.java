package br.com.cinehub.projetopoocinehub.Controllers;

import br.com.cinehub.projetopoocinehub.Models.Filme;
import br.com.cinehub.projetopoocinehub.Models.FilmesModel;

import java.io.IOException;
import java.util.List;

public class FilmesController {
    public static List<Filme> carregarFilmes() {
        try {
            return FilmesModel.getFilmes();
        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo filmes.json: " + e.getMessage());
            return null;
        }
    }
}