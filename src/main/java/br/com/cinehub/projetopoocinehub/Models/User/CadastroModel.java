package br.com.cinehub.projetopoocinehub.Models.User;

import br.com.cinehub.projetopoocinehub.Models.User.Cliente.Cliente;
import br.com.cinehub.projetopoocinehub.Models.User.Cliente.Gerente;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class CadastroModel {
    private ArrayList<Gerente> listaGerentes;
    private ArrayList<Cliente> listaClientes;

    private static final String CLIENTES_JSON = "clientes.json";
    private static final String GERENTES_JSON = "gerentes.json";

    private String dataDir;

    public CadastroModel() {
        this.dataDir = "/filmes.json";
        listaClientes = loadClientes();
        listaGerentes = loadGerentes();
    }

    private ArrayList<Cliente> loadClientes() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Path filePath = Paths.get(dataDir, CLIENTES_JSON);

        if (!Files.exists(filePath)) {
            // Se o arquivo não existir, retornar lista vazia
            return new ArrayList<>();
        }

        try {
            return mapper.readValue(Files.newInputStream(filePath), new TypeReference<ArrayList<Cliente>>() {});
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo clientes.json: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private ArrayList<Gerente> loadGerentes() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Path filePath = Paths.get(dataDir, GERENTES_JSON);

        if (!Files.exists(filePath)) {
            // Se o arquivo não existir, retornar lista vazia
            return new ArrayList<>();
        }

        try {
            return mapper.readValue(Files.newInputStream(filePath), new TypeReference<ArrayList<Gerente>>() {});
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo gerentes.json: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private void saveClientes() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        Path filePath = Paths.get(dataDir, CLIENTES_JSON);

        try {
            // Certificar-se de que o diretório existe
            Files.createDirectories(filePath.getParent());
            mapper.writeValue(Files.newOutputStream(filePath), listaClientes);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo clientes.json: " + e.getMessage());
        }
    }

    private void saveGerentes() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        Path filePath = Paths.get(dataDir, GERENTES_JSON);

        try {
            // Certificar-se de que o diretório existe
            Files.createDirectories(filePath.getParent());
            mapper.writeValue(Files.newOutputStream(filePath), listaGerentes);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo gerentes.json: " + e.getMessage());
        }
    }

    public boolean cadastroGerente(String nome, String email, String senha, String usuarioLogado) {
        // Verifica se é um gerente que está adicionando
        if ("Gerente".equals(usuarioLogado)) {
            Gerente novoGerente = new Gerente(nome, email, senha);
            if (verificarCadastroGerente(email)) {
                listaGerentes.add(novoGerente);
                saveGerentes(); // Salvar no arquivo JSON
                return true; // Cadastro realizado com sucesso
            } else {
                return false; // Email já cadastrado
            }
        } else {
            // Usuário não é gerente
            return false;
        }
    }

    public boolean cadastroCliente(String nome, String email, String senha) {
        Cliente novoCliente = new Cliente(nome, email, senha);
        if (verificarCadastroCliente(email)) {
            listaClientes.add(novoCliente);
            saveClientes(); // Salvar no arquivo JSON
            return true; // Cadastro realizado com sucesso
        } else {
            return false; // Email já cadastrado
        }
    }

    private boolean verificarCadastroGerente(String email) {
        return listaGerentes.stream()
                .noneMatch(gerente -> gerente.getEmail().equals(email));
    }

    private boolean verificarCadastroCliente(String email) {
        return listaClientes.stream()
                .noneMatch(cliente -> cliente.getEmail().equals(email));
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public ArrayList<Gerente> getListaGerentes() {
        return listaGerentes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
        saveClientes(); // Salvar no arquivo JSON
    }

    public void setListaGerentes(ArrayList<Gerente> listaGerentes) {
        this.listaGerentes = listaGerentes;
        saveGerentes(); // Salvar no arquivo JSON
    }
}
