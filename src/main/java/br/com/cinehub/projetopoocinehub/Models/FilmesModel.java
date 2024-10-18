package br.com.cinehub.projetopoocinehub.Models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.List;
//Persistência de dados
public class FilmesModel {
    public static List<Filme> getFilmes() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        File jsonFile = new File("filmes.json");
        List<Filme> filmes = mapper.readValue(jsonFile, new TypeReference<List<Filme>>() {});

        if (filmes != null) {
            filmes.forEach(filme -> System.out.println(filme.getTitulo()));
        }

        return filmes;
    }
}