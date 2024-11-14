package br.com.cinehub.projetopoocinehub.Models.Aluguel;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletContext;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.UUID;

public class AluguelModel {
    private static ArrayList<Aluguel> listaAlugueis;
    private static final String ALUGUEIS_JSON = "alugueis.json";
    private final String dataDir;
    private final ServletContext context;

    public AluguelModel(ServletContext context) {
        this.context = context;
        this.dataDir = System.getProperty("user.home") + "/cinehub/data/";
        listaAlugueis = loadAlugueis();
    }

    private ArrayList<Aluguel> loadAlugueis() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Path dataFilePath = Paths.get(dataDir, ALUGUEIS_JSON);

        if (!Files.exists(dataFilePath)) {
            try {
                // Ensure the directory exists
                Files.createDirectories(dataFilePath.getParent());

                // Copy the file from WEB-INF to the data directory
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

            // Check for missing IDs and generate new ones if necessary
            boolean precisaSalvar = false;
            for (Aluguel aluguel : alugueis) {
                if (aluguel.getId() == null || aluguel.getId().isEmpty()) {
                    aluguel.setId(UUID.randomUUID().toString());
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

    public synchronized void salvarAlugueis() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        Path dataFilePath = Paths.get(dataDir, ALUGUEIS_JSON);

        try {
            Files.createDirectories(dataFilePath.getParent());
            mapper.writeValue(Files.newOutputStream(dataFilePath), listaAlugueis);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo " + ALUGUEIS_JSON + ": " + e.getMessage());
        }
    }

    // Getter for the list of rentals
    public static ArrayList<Aluguel> getListaAlugueis() {
        return listaAlugueis;
    }

    // Method to find a rental by ID
    public Aluguel buscarAluguelPorId(String id) {
        return listaAlugueis.stream().filter(aluguel -> aluguel.getId().equals(id)).findFirst().orElse(null);
    }

    // Method to add a new rental
    public void adicionarAluguel(Aluguel aluguel) {
        if (aluguel.getId() == null || aluguel.getId().isEmpty()) {
            aluguel.setId(UUID.randomUUID().toString());
        }
        listaAlugueis.add(aluguel);
        salvarAlugueis();
    }

    // Method to remove a rental
    public void removerAluguel(Aluguel aluguel) {
        listaAlugueis.remove(aluguel);
        salvarAlugueis();
    }

    // Method to find rentals for a specific client
    public static ArrayList<Aluguel> buscarAlugueisPorCliente(String clienteId) {
        ArrayList<Aluguel> alugueisDoCliente = new ArrayList<>();
        for (Aluguel aluguel : listaAlugueis) {
            if (aluguel.getClienteId().equals(clienteId)) {
                alugueisDoCliente.add(aluguel);
            }
        }
        return alugueisDoCliente;
    }

    // Method to update an existing rental
    public void atualizarAluguel(Aluguel aluguel) {
        for (int i = 0; i < listaAlugueis.size(); i++) {
            if (listaAlugueis.get(i).getId().equals(aluguel.getId())) {
                listaAlugueis.set(i, aluguel);
                salvarAlugueis();
                break;
            }
        }
    }
}
