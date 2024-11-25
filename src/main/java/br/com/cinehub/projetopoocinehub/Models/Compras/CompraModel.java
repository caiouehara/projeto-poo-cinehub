package br.com.cinehub.projetopoocinehub.Models.Compras;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.ServletContext;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.*;

/**
 * Classe responsável por gerenciar as operações relacionadas às compras.
 * Carrega, salva e manipula a lista de compras.
 */
public class CompraModel {
    private static ArrayList<Compra> listaCompras;
    private static final String COMPRAS_JSON = "compras.json";
    private final String dataDir;
    private final ServletContext context;

    /**
     * Construtor da classe CompraModel.
     *
     * @param context O ServletContext da aplicação, usado para acessar recursos.
     */
    public CompraModel(ServletContext context) {
        this.context = context;
        this.dataDir = System.getProperty("user.home") + "/cinehub/data/";
        listaCompras = loadCompras();
    }

    /**
     * Carrega a lista de compras do arquivo JSON.
     * Se o arquivo não existir, copia um arquivo de exemplo do diretório WEB-INF.
     *
     * @return Uma lista de Compra.
     */
    private ArrayList<Compra> loadCompras() {
        ObjectMapper mapper = new ObjectMapper();

        // Configura o ObjectMapper para lidar com java.util.Date
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.setDateFormat(new java.text.SimpleDateFormat("yyyy-MM-dd"));
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Path dataFilePath = Paths.get(dataDir, COMPRAS_JSON);

        if (!Files.exists(dataFilePath)) {
            try {
                // Garante que o diretório existe
                Files.createDirectories(dataFilePath.getParent());

                // Copia o arquivo do WEB-INF para o diretório de dados
                InputStream inputStream = context.getResourceAsStream("/WEB-INF/data/compras.json");
                if (inputStream == null) {
                    System.out.println("Erro: Arquivo compras.json não encontrado em WEB-INF.");
                    return new ArrayList<>();
                }

                Files.copy(inputStream, dataFilePath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Arquivo compras.json criado com sucesso.");
                inputStream.close();
            } catch (IOException e) {
                System.out.println("Erro ao copiar o arquivo compras.json: " + e.getMessage());
                return new ArrayList<>();
            }
        }

        try {
            ArrayList<Compra> compras = mapper.readValue(Files.newInputStream(dataFilePath), new TypeReference<ArrayList<Compra>>() {
            });

            boolean precisaSalvar = false;

            // Gera IDs para compras que não possuem
            for (Compra compra : compras) {
                if (compra.getId() == null || compra.getId().isEmpty()) {
                    compra.setId(UUID.randomUUID().toString());
                    precisaSalvar = true;
                }
            }

            if (precisaSalvar) {
                listaCompras = compras;
                salvarCompras();
            }

            System.out.println("Arquivo compras.json carregado com sucesso.");

            return compras;
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo " + COMPRAS_JSON + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Salva a lista atual de compras no arquivo JSON.
     * O método é sincronizado para evitar acesso concorrente.
     */
    public synchronized void salvarCompras() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.setDateFormat(new java.text.SimpleDateFormat("yyyy-MM-dd"));

        Path dataFilePath = Paths.get(dataDir, COMPRAS_JSON);

        try {
            Files.createDirectories(dataFilePath.getParent());
            mapper.writeValue(Files.newOutputStream(dataFilePath), listaCompras);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo " + COMPRAS_JSON + ": " + e.getMessage());
        }
    }

    /**
     * Retorna a lista atual de compras.
     *
     * @return Uma lista de Compra.
     */
    public static ArrayList<Compra> getListaCompras() {
        return listaCompras;
    }

    /**
     * Adiciona uma nova compra à lista e salva as alterações.
     *
     * @param compra A Compra a ser adicionada.
     */
    public void adicionarCompra(Compra compra) {
        if (compra.getId() == null || compra.getId().isEmpty()) {
            compra.setId(UUID.randomUUID().toString());
        }
        listaCompras.add(compra);
        salvarCompras();
    }

    /**
     * Remove uma compra da lista e salva as alterações.
     *
     * @param compra A Compra a ser removida.
     */
    public void removerCompra(Compra compra) {
        listaCompras.remove(compra);
        salvarCompras();
    }

    /**
     * Busca todas as compras de um cliente específico.
     *
     * @param clienteId O ID do cliente (normalmente o email).
     * @return Uma lista de Compra associadas ao cliente.
     */
    public static ArrayList<Compra> buscarComprasPorCliente(String clienteId) {
        ArrayList<Compra> comprasDoCliente = new ArrayList<>();
        for (Compra compra : listaCompras) {
            if (compra.getClienteId().equals(clienteId)) {
                comprasDoCliente.add(compra);
            }
        }
        return comprasDoCliente;
    }

    /**
     * Atualiza uma compra existente na lista e salva as alterações.
     *
     * @param compra A Compra com as informações atualizadas.
     */
    public void atualizarCompra(Compra compra) {
        for (int i = 0; i < listaCompras.size(); i++) {
            if (listaCompras.get(i).getId().equals(compra.getId())) {
                listaCompras.set(i, compra);
                salvarCompras();
                break;
            }
        }
    }

    /**
     * Retorna a lista atual de compras.
     *
     * @return Uma lista de compra.
     */
    public static ArrayList<Compra> getListaCompra() {
        return listaCompras;
    }
}
