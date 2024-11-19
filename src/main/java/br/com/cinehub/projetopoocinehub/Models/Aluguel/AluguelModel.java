package br.com.cinehub.projetopoocinehub.Models.Aluguel;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletContext;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Classe responsável por gerenciar as operações relacionadas aos aluguéis.
 * Carrega, salva e manipula a lista de aluguéis, incluindo a remoção de aluguéis vencidos.
 */
public class AluguelModel {
    private static ArrayList<Aluguel> listaAlugueis;
    private static final String ALUGUEIS_JSON = "alugueis.json";
    private final String dataDir;
    private final ServletContext context;

    /**
     * Construtor da classe AluguelModel.
     *
     * @param context O ServletContext da aplicação, usado para acessar recursos.
     */
    public AluguelModel(ServletContext context) {
        this.context = context;
        this.dataDir = System.getProperty("user.home") + "/cinehub/data/";
        listaAlugueis = loadAlugueis();
    }

    /**
     * Carrega a lista de aluguéis do arquivo JSON.
     * Se o arquivo não existir, copia um arquivo de exemplo do diretório WEB-INF.
     * Remove aluguéis vencidos com base na data de devolução.
     *
     * @return Uma lista de Aluguel.
     */
    private ArrayList<Aluguel> loadAlugueis() {
        ObjectMapper mapper = new ObjectMapper();

        // Registra o módulo para serialização/deserialização de LocalDate
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Path dataFilePath = Paths.get(dataDir, ALUGUEIS_JSON);

        if (!Files.exists(dataFilePath)) {
            try {
                // Garante que o diretório existe
                Files.createDirectories(dataFilePath.getParent());

                // Copia o arquivo do WEB-INF para o diretório de dados
                InputStream inputStream = context.getResourceAsStream("/WEB-INF/data/alugueis.json");
                if (inputStream == null) {
                    System.out.println("Erro: Arquivo alugueis.json não encontrado em WEB-INF.");
                    return new ArrayList<>();
                }

                Files.copy(inputStream, dataFilePath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Arquivo alugueis.json criado com sucesso.");
                inputStream.close();
            } catch (IOException e) {
                System.out.println("Erro ao copiar o arquivo alugueis.json: " + e.getMessage());
                return new ArrayList<>();
            }
        }

        try {
            ArrayList<Aluguel> alugueis = mapper.readValue(Files.newInputStream(dataFilePath), new TypeReference<ArrayList<Aluguel>>() {});

            boolean precisaSalvar = false;

            // Gera IDs para aluguéis que não possuem
            for (Aluguel aluguel : alugueis) {
                if (aluguel.getId() == null || aluguel.getId().isEmpty()) {
                    aluguel.setId(UUID.randomUUID().toString());
                    precisaSalvar = true;
                }
            }

            // Remove aluguéis vencidos
            LocalDate hoje = LocalDate.now();
            Iterator<Aluguel> iterator = alugueis.iterator();
            while (iterator.hasNext()) {
                Aluguel aluguel = iterator.next();
                if (aluguel.getDataDevolucao().isBefore(hoje)) {
                    iterator.remove();
                    precisaSalvar = true;
                }
            }

            if (precisaSalvar) {
                listaAlugueis = alugueis;
                salvarAlugueis();
            }

            System.out.println("Arquivo alugueis.json carregado com sucesso.");

            return alugueis;
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo " + ALUGUEIS_JSON + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Salva a lista atual de aluguéis no arquivo JSON.
     * O método é sincronizado para evitar acesso concorrente.
     */
    public synchronized void salvarAlugueis() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        Path dataFilePath = Paths.get(dataDir, ALUGUEIS_JSON);

        try {
            Files.createDirectories(dataFilePath.getParent());
            mapper.writeValue(Files.newOutputStream(dataFilePath), listaAlugueis);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo " + ALUGUEIS_JSON + ": " + e.getMessage());
        }
    }

    /**
     * Retorna a lista atual de aluguéis.
     *
     * @return Uma lista de Aluguel.
     */
    public static ArrayList<Aluguel> getListaAlugueis() {
        return listaAlugueis;
    }

    /**
     * Busca um aluguel pelo seu ID.
     *
     * @param id O ID do aluguel a ser buscado.
     * @return O Aluguel correspondente ou null se não encontrado.
     */
    public Aluguel buscarAluguelPorId(String id) {
        return listaAlugueis.stream().filter(aluguel -> aluguel.getId().equals(id)).findFirst().orElse(null);
    }

    /**
     * Adiciona um novo aluguel à lista e salva as alterações.
     *
     * @param aluguel O Aluguel a ser adicionado.
     */
    public void adicionarAluguel(Aluguel aluguel) {
        if (aluguel.getId() == null || aluguel.getId().isEmpty()) {
            aluguel.setId(UUID.randomUUID().toString());
        }
        listaAlugueis.add(aluguel);
        salvarAlugueis();
    }

    /**
     * Remove um aluguel da lista e salva as alterações.
     *
     * @param aluguel O Aluguel a ser removido.
     */
    public void removerAluguel(Aluguel aluguel) {
        listaAlugueis.remove(aluguel);
        salvarAlugueis();
    }

    /**
     * Busca todos os aluguéis de um cliente específico.
     *
     * @param clienteId O ID do cliente (normalmente o email).
     * @return Uma lista de Aluguel associados ao cliente.
     */
    public static ArrayList<Aluguel> buscarAlugueisPorCliente(String clienteId) {
        ArrayList<Aluguel> alugueisDoCliente = new ArrayList<>();
        for (Aluguel aluguel : listaAlugueis) {
            if (aluguel.getClienteId().equals(clienteId)) {
                alugueisDoCliente.add(aluguel);
            }
        }
        return alugueisDoCliente;
    }

    /**
     * Atualiza um aluguel existente na lista e salva as alterações.
     *
     * @param aluguel O Aluguel com as informações atualizadas.
     */
    public void atualizarAluguel(Aluguel aluguel) {
        for (int i = 0; i < listaAlugueis.size(); i++) {
            if (listaAlugueis.get(i).getId().equals(aluguel.getId())) {
                listaAlugueis.set(i, aluguel);
                salvarAlugueis();
                break;
            }
        }
    }

    //metodo para remover aluguel após data de devolução
    public void fimAluguel(Aluguel aluguel) {
        TimerTask tarefa = new TimerTask() {
            @Override //como TimerTask é uma classe abstrata precisamos implementar o método run com o código que queremos que vire uma tarefa a ser automatizada
            public void run() {
                LocalDate hoje = LocalDate.now();
                if (hoje.isAfter(aluguel.getDataDevolucao())) { 
                  	removerAluguel(aluguel);
                    cancel();
                }
            }
        };

    Timer timer = new Timer();
    timer.scheduleAtFixedRate(tarefa, 86400000, 86400000);
    }
    
}
