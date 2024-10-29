package br.com.cinehub.projetopoocinehub.Models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File; //Biblioteca para manipular arquivos

import java.io.IOException; //Biblioteca para manipular exceções
import java.util.ArrayList; //Biblioteca para manipular ArrayLit

public class FilmesFile {
    // Converte o JSON em uma lista de objetos Java do tipo Filme e aplica uma exceção para caso ocorra uma erro ao acessar o arquivo
    public static ArrayList<Filme> getFilmes() throws IOException {
        ObjectMapper mapper = new ObjectMapper(); // Responsável por converter o JSON do arquivo em uma lista de objetos Filme
        File jsonFile = new File("src/main/resources/filmes.json");

        // Verifica se o arquivo existe e cria caso contrário
        if (!jsonFile.exists()) {
            try {
                jsonFile.createNewFile();
                System.out.println("Arquivo filmes.json criado com sucesso.");
            } catch (IOException e) {
                System.out.println("Erro ao criar o arquivo: " + e.getMessage());
            }
        }


        //Lê do arquivo JSON e preenche o ArrayList
        ArrayList<Filme> filmes = mapper.readValue(jsonFile, new TypeReference<ArrayList<Filme>>() {});

        return filmes;
    }
}
