//package br.com.cinehub.projetopoocinehub.Models;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.core.type.TypeReference;
////Metodo estático que salva os dados(classe separadas)
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
////Persistência de dados
//public class ClienteFile {
//    public static ArrayList<Cliente> get() throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//
//        File jsonFile = new File("filmes.json");
//        List<Filme> filmes = mapper.readValue(jsonFile, new TypeReference<List<Filme>>() {});
//
//        if (filmes != null) {
//            //  filmes.forEach(filme -> System.out.println(filme.getTitulo()));
//        }
//
//        return filmes;
//    }
//}