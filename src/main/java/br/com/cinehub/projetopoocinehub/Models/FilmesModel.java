package br.com.cinehub.projetopoocinehub.Models;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.*;
import java.util.ArrayList;

/**
 * FilmesModels é uma classe Model que lida com a persistência dos dados em JSON dos filmes.
 *
 * O caminho dos arquivos está configurado para todos os arquivos da pasta resource serem copiados para a pasta de compilação "target" via Maven.
 * Assim, o caminho deve ser referenciado em tempo de compilação. Trate os arquivos de resource como a pasta raiz.
 */
public class FilmesModel {
    // Converte o JSON em uma lista de objetos Java do tipo Filme e aplica uma exceção para caso ocorra um erro ao acessar o arquivo
    public static ArrayList<Filme> getFilmes() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //evita falhas ao desserializar JSON que contém campos extras

        try {
            // Obtém o InputStream do arquivo JSON no classpath
            InputStream inputStream = FilmesModel.class.getResourceAsStream("/filmes.json");

            if (inputStream == null) {
                System.out.println("Erro: Arquivo filmes.json não encontrado no classpath.");
                return new ArrayList<Filme>();
            }

            // Lê do InputStream e preenche o ArrayList
            return mapper.readValue(inputStream, new TypeReference<ArrayList<Filme>>() {});

        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo filmes.json: " + e.getMessage());
        }

        return new ArrayList<Filme>();
    }
    public static void setFilmes(ArrayList<Filme> filmes) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try{
            mapper.writeValue(new File("/filmes.jsp"), filmes);
            System.out.println("Filmes salvos com sucesso em filmes.json");
        }
        catch(IOException e){
            System.out.println("Erro ao salvar o arquivo filmes.json: " + e.getMessage());
        }
    }

}