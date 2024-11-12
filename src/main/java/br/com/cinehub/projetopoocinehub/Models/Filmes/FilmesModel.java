package br.com.cinehub.projetopoocinehub.Models.Filmes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import jakarta.servlet.ServletContext;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.UUID;

public class FilmesModel {
    private static ArrayList<Filme> listaFilmes;
    private static final String FILMES_JSON = "filmes.json";
    private final String dataDir;
    private ServletContext context;

    public FilmesModel(ServletContext context) {
        this.context = context;
        this.dataDir = System.getProperty("user.home") + "/cinehub/data/";
        listaFilmes = loadFilmes();
    }

    private ArrayList<Filme> loadFilmes() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Path dataFilePath = Paths.get(dataDir, FILMES_JSON);

        // Verifica se o arquivo filmes.json existe no dataDir
        if (!Files.exists(dataFilePath)) {
            // Se não existir, copia do WEB-INF para o dataDir
            try {
                // Certifica-se de que o diretório existe
                Files.createDirectories(dataFilePath.getParent());

                // Obtém o InputStream do arquivo filmes.json em WEB-INF
                InputStream inputStream = context.getResourceAsStream("/WEB-INF/data/filmes.json");

                if (inputStream == null) {
                    System.out.println("Erro: Arquivo filmes.json não encontrado em WEB-INF.");
                    return new ArrayList<>();
                }

                // Copia o conteúdo do InputStream para o arquivo no dataDir
                Files.copy(inputStream, dataFilePath, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Arquivo filmes.json criado com sucesso.");

                inputStream.close();
            } catch (IOException e) {
                System.out.println("Erro ao copiar o arquivo filmes.json: " + e.getMessage());
                return new ArrayList<>();
            }
        }

        // Agora, carrega os filmes do arquivo no dataDir
        try {
            ArrayList<Filme> filmes = mapper.readValue(Files.newInputStream(dataFilePath), new TypeReference<ArrayList<Filme>>() {});

            // Verificar se algum filme não tem ID e gerar um novo
            boolean precisaSalvar = false;
            for (Filme filme : filmes) {
                if (filme.getId() == null || filme.getId().isEmpty()) {
                    filme.setId(UUID.randomUUID().toString());
                    precisaSalvar = true;
                }
            }

            // Se geramos novos IDs, salvar o arquivo atualizado
            if (precisaSalvar) {
                this.listaFilmes = filmes;
                salvarFilmes();
            }

            System.out.println("Arquivo filmes.json carregado com sucesso.");

            return filmes;
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo " + FILMES_JSON + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public synchronized void salvarFilmes() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        Path dataFilePath = Paths.get(dataDir, FILMES_JSON);

        try {
            // Certifica-se de que o diretório existe
            Files.createDirectories(dataFilePath.getParent());
            mapper.writeValue(Files.newOutputStream(dataFilePath), listaFilmes);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo " + FILMES_JSON + ": " + e.getMessage());
        }
    }

    // Métodos para manipular a lista de filmes
    public static ArrayList<Filme> getListaFilmes() {
        return listaFilmes;
    }

    public void adicionarFilme(Filme filme) {
        listaFilmes.add(filme);
        salvarFilmes(); // Salvar a lista atualizada
    }

    public void removerFilme(Filme filme) {
        listaFilmes.remove(filme);
        salvarFilmes(); // Salvar a lista atualizada
    }
}
