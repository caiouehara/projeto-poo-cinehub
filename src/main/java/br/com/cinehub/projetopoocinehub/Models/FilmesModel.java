package br.com.cinehub.projetopoocinehub.Models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
* FilmesModels é uma classe Model que lida com a persistência dos dados em json dos filmes.
*
 * @author Caio Uehara
*
*/
public class FilmesModel {
    public static List<Filme> getFilmes() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        File jsonFile = new File("filmes.json");
        List<Filme> filmes = mapper.readValue(jsonFile, new TypeReference<List<Filme>>() {});

        return filmes;
    }
}