/**
 * Classe que gerencia a lista de filmes do sistema, incluindo operações como
 * carregar, salvar, adicionar, editar e remover filmes. Também lida com a
 * persistência de dados no formato JSON.
 * 
 * <p>O arquivo JSON contendo os filmes é armazenado no diretório
 * {@code $USER_HOME/cinehub/data/filmes.json}. Se o arquivo não existir, será
 * criado automaticamente com base no recurso em {@code WEB-INF/data/filmes.json}.</p>
 */
package br.com.cinehub.projetopoocinehub.Models.Filmes;

import br.com.cinehub.projetopoocinehub.Models.Aluguel.AluguelModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.servlet.ServletContext;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.UUID;

public class FilmesModel {
    /** Lista de filmes armazenados no sistema. */
    private static ArrayList<Filme> listaFilmes;
    /** Nome do arquivo JSON onde os filmes são armazenados. */
    private static final String FILMES_JSON = "filmes.json";
     /** Diretório onde os dados são armazenados. */
    private static final String DATA_DIR = System.getProperty("user.home") + "/cinehub/data/";
    /** Contexto do servlet para acessar recursos da aplicação. */
    private ServletContext context;

    /**
     * Construtor da classe que inicializa o contexto e carrega os filmes do arquivo JSON.
     * 
     * @param context O contexto do servlet.
     */
    public FilmesModel(ServletContext context) {
        this.context = context;
        listaFilmes = loadFilmes();
    }

    /**
     * Carrega os filmes do arquivo JSON. Se o arquivo não existir, será criado
     * com base no recurso padrão em {@code WEB-INF/data/filmes.json}.
     * 
     * @return A lista de filmes carregada.
     */
    private ArrayList<Filme> loadFilmes() {
        ObjectMapper mapper = new ObjectMapper();

        //Configuração do mapeamento para Serialização
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Path dataFilePath = Paths.get(DATA_DIR, FILMES_JSON);

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
            ArrayList<Filme> filmes = mapper.readValue(Files.newInputStream(dataFilePath), new TypeReference<ArrayList<Filme>>() {
            });

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

    /**
     * Salva a lista atual de filmes no arquivo JSON.
     */
    public static void salvarFilmes() {
        ObjectMapper mapper = new ObjectMapper();

        // Registrar o módulo JavaTimeModule
        mapper.registerModule(new JavaTimeModule());

        // Desativar a escrita de datas como timestamps
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // Habilitar a escrita de datas no formato ISO
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        Path dataFilePath = Paths.get(DATA_DIR, FILMES_JSON);

        try {
            Files.createDirectories(dataFilePath.getParent());
            mapper.writeValue(Files.newOutputStream(dataFilePath), listaFilmes);
            System.out.println("Arquivo filmes.json salvo com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo filmes.json: " + e.getMessage());
        }
    }

    /**
     * Obtém a lista de filmes.
     * 
     * @return A lista de filmes.
     */
    // Métodos para manipular a lista de filmes
    public static ArrayList<Filme> getListaFilmes() {
        return listaFilmes;
    }

    /**
     * Busca um filme pelo seu ID.
     * 
     * @param id O ID do filme a ser buscado.
     * @return O filme correspondente ou {@code null} se não encontrado.
     */
    public static Filme buscarFilmePorId(String id) {
        for (Filme filme : listaFilmes) {
            if (filme.getId().equals(id)) {
                return filme;
            }
        }
        return null;
    }

    /**
     * Adiciona um novo filme à lista e salva as alterações no arquivo JSON.
     * 
     * @param filme O filme a ser adicionado.
     */
    public void adicionarFilme(Filme filme) {
        listaFilmes.add(filme);
        salvarFilmes(); // Salvar a lista atualizada
    }

    /**
     * Remove um filme da lista e salva as alterações.
     *
     * @param filme O Filme a ser removido.
     * @throws IllegalArgumentException se o filme não for encontrado na lista.
     */
    public void removerFilme(Filme filme) {
        if (filme == null) {
            throw new IllegalArgumentException("Filme não pode ser null.");
        }

        if (!listaFilmes.contains(filme)) {
            throw new IllegalArgumentException("Filme com ID " + filme.getId() + " não encontrado na lista.");
        }

        // Remover o filme da lista
        listaFilmes.remove(filme);

        // Remover a imagem associada ao filme (se existir)
        String imagemFileName = filme.getImagem();
        if (imagemFileName != null && !imagemFileName.trim().isEmpty()) {
            Path imagemPath = Paths.get(DATA_DIR, "img", "films", imagemFileName);
            try {
                Files.deleteIfExists(imagemPath);
                System.out.println("Imagem " + imagemFileName + " excluída com sucesso.");
            } catch (IOException e) {
                System.out.println("Erro ao excluir a imagem " + imagemFileName + ": " + e.getMessage());
                // Opcional: Você pode lançar uma exceção ou logar detalhadamente
            }
        }

        // Salvar a lista atualizada
        salvarFilmes();
    }

    /**
     * Edita as informações de um filme existente.
     * 
     * @param id          O ID do filme a ser editado.
     * @param titulo      O novo título do filme.
     * @param ano         O novo ano do filme.
     * @param sinopse     A nova sinopse do filme.
     * @param duracao     A nova duração do filme.
     * @param precoCompra O novo preço de compra do filme.
     * @param precoAluguel O novo preço de aluguel do filme.
     * @param novaImagem  O novo caminho da imagem do filme.
     * @throws IllegalArgumentException Se o filme com o ID especificado não for encontrado.
     */
    public void editarFilme(String id, String titulo, Integer ano, String sinopse,
                            Double duracao, Double precoCompra,
                            Double precoAluguel, String novaImagem) {

        Filme filmeExistente = buscarFilmePorId(id);
        if (filmeExistente == null) {
            throw new IllegalArgumentException("Filme com ID " + id + " não encontrado.");
        }

        // Atualizar apenas os campos fornecidos
        if (titulo != null && !titulo.isEmpty()) {
            filmeExistente.setTituloFilme(titulo);
        }
        if (ano != null) {
            filmeExistente.setAnoFilme(ano);
        }
        if (sinopse != null && !sinopse.isEmpty()) {
            filmeExistente.setSinopseFilme(sinopse);
        }
        if (duracao != null) {
            filmeExistente.setDuracaoFilme(duracao);
        }
        if (precoCompra != null) {
            filmeExistente.setPrecoFilmeCompra(precoCompra);
        }
        if (precoAluguel != null) {
            filmeExistente.setPrecoFilmeAluguel(precoAluguel);
        }
        if (novaImagem != null && !novaImagem.isEmpty()) {
            filmeExistente.setImagem(novaImagem);
        }

        // Salvar as alterações na lista
        salvarFilmes();
    }
}
