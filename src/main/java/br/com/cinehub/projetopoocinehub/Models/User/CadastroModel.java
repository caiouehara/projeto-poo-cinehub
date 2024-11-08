package br.com.cinehub.projetopoocinehub.Models.User;

import br.com.cinehub.projetopoocinehub.Models.User.Tipos.Cliente;
import br.com.cinehub.projetopoocinehub.Models.User.Tipos.Gerente;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Objects;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CadastroModel {
    private ArrayList<Usuario> listaUsers;

    private static final String USERS_JSON = "users.json";

    private final String dataDir;

    public CadastroModel(String dataDir) {
        this.dataDir = Objects.requireNonNull(dataDir, "dataDir não pode ser null");
        listaUsers = loadUsers();
    }

    private ArrayList<Usuario> loadUsers() {
        ObjectMapper mapper = new ObjectMapper();
        // Configura o mapper para ignorar propriedades desconhecidas
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Path filePath = Paths.get(dataDir, USERS_JSON);

        if (!Files.exists(filePath)) {
            // Se o arquivo não existir, retornar lista vazia
            return new ArrayList<>();
        }

        try {
            return mapper.readValue(Files.newInputStream(filePath), new TypeReference<ArrayList<Usuario>>() {});
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo " + USERS_JSON + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public synchronized void salvarUser() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        Path filePath = Paths.get(dataDir, USERS_JSON);

        try {
            // Certificar-se de que o diretório existe
            Files.createDirectories(filePath.getParent());
            mapper.writeValue(Files.newOutputStream(filePath), listaUsers);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo " + USERS_JSON + ": " + e.getMessage());
        }
    }

    /**
     * Adiciona um novo usuário (Cliente ou Gerente) ao sistema.
     *
     * @param nome          Nome do novo usuário.
     * @param email         Email do novo usuário.
     * @param senha         Senha do novo usuário.
     * @param tipo          Tipo do usuário ("Cliente" ou "Gerente").
     * @param usuarioLogado Tipo de usuário que está realizando a operação. Pode ser "Gerente" ou null.
     * @return              True se o cadastro foi realizado com sucesso, false caso contrário.
     */
    public boolean adicionarUser(String nome, String email, String senha, String tipo, String usuarioLogado) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedSenha = passwordEncoder.encode(senha);

        if (usuarioLogado != null && "Gerente".equals(usuarioLogado)) {
            // Usuário logado é um Gerente
            if ("Gerente".equalsIgnoreCase(tipo)) {
                Gerente novoGerente = new Gerente(nome, email, hashedSenha);
                if (verificarCadastroUser(email)) {
                    listaUsers.add(novoGerente);
                    salvarUser(); // Salvar no arquivo JSON
                    return true; // Cadastro realizado com sucesso
                } else {
                    return false; // Email já cadastrado
                }
            } else if ("Cliente".equalsIgnoreCase(tipo)) {
                Cliente novoCliente = new Cliente(nome, email, hashedSenha, 0.0, 0.0, 0.0);
                if (verificarCadastroUser(email)) {
                    listaUsers.add(novoCliente);
                    salvarUser(); // Salvar no arquivo JSON
                    return true; // Cadastro realizado com sucesso
                } else {
                    return false; // Email já cadastrado
                }
            } else {
                // Tipo de usuário inválido
                return false;
            }
        } else {
            // Usuário não está logado ou não é um Gerente; permitir apenas cadastro como Cliente
            if ("Cliente".equalsIgnoreCase(tipo)) {
                Cliente novoCliente = new Cliente(nome, email, hashedSenha, 0.0, 0.0, 0.0);
                if (verificarCadastroUser(email)) {
                    listaUsers.add(novoCliente);
                    salvarUser(); // Salvar no arquivo JSON
                    return true; // Cadastro realizado com sucesso
                } else {
                    return false; // Email já cadastrado
                }
            } else {
                // Tentativa de cadastrar como Gerente sem autorização
                return false;
            }
        }
    }

    /**
     * Valida o login de um usuário (Cliente ou Gerente).
     *
     * @param email     Email do usuário.
     * @param senha     Senha do usuário.
     * @return          Tipo do usuário ("Cliente", "Gerente") se o login for válido, null caso contrário.
     */
    public String validarLoginUser(String email, String senha) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        for (Usuario user : listaUsers) {
            if (user.getEmail().equals(email)) {
                if (passwordEncoder.matches(senha, user.getSenha())) {
                    if (user instanceof Cliente) {
                        return "Cliente";
                    } else if (user instanceof Gerente) {
                        return "Gerente";
                    }
                }
            }
        }
        return null; // Login inválido
    }

    /**
     * Verifica se um usuário já está cadastrado.
     *
     * @param email Email a ser verificado.
     * @return      True se o email não estiver cadastrado, false caso contrário.
     */
    public boolean verificarCadastroUser(String email) {
        return listaUsers.stream()
                .noneMatch(user -> user.getEmail().equals(email));
    }

    // Getters e Setters

    public ArrayList<Usuario> getListaUsers() {
        return listaUsers;
    }

    public synchronized void setListaUsers(ArrayList<Usuario> listaUsers) {
        this.listaUsers = listaUsers;
        salvarUser(); // Salvar no arquivo JSON
    }
}
