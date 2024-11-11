// CadastroModel.java
package br.com.cinehub.projetopoocinehub.Models.User;

import br.com.cinehub.projetopoocinehub.Models.User.Tipos.Cliente;
import br.com.cinehub.projetopoocinehub.Models.User.Tipos.Gerente;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CadastroModel {
    private ArrayList<Usuario> listaUsers;
    private static final String USERS_JSON = "users.json";
    private final String dataDir;

    public CadastroModel() {
        this.dataDir = System.getProperty("user.home") + "/cinehub/data/";
        listaUsers = loadUsers();
    }

    private ArrayList<Usuario> loadUsers() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Path filePath = Paths.get(dataDir, USERS_JSON);

        if (!Files.exists(filePath)) {
            // Se o arquivo não existir, criar arquivo com dados padrão
            ArrayList<Usuario> defaultUsers = criarUsuariosPadrao();
            try {
                // Certificar-se de que o diretório existe
                Files.createDirectories(filePath.getParent());
                // Salvar os usuários padrão no arquivo JSON
                mapper.enable(SerializationFeature.INDENT_OUTPUT);
                mapper.writeValue(Files.newOutputStream(filePath), defaultUsers);
            } catch (IOException e) {
                System.out.println("Erro ao criar o arquivo " + USERS_JSON + ": " + e.getMessage());
            }
            return defaultUsers;
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

        if (verificarCadastroUser(email)) {
            Usuario novoUsuario;
            if ("Gerente".equalsIgnoreCase(tipo) && "Gerente".equals(usuarioLogado)) {
                novoUsuario = new Gerente(nome, email, hashedSenha);
            } else if ("Cliente".equalsIgnoreCase(tipo)) {
                novoUsuario = new Cliente(nome, email, hashedSenha, 0.0, 0.0, 0.0);
            } else {
                // Tipo de usuário inválido ou tentativa de cadastro de Gerente sem permissão
                return false;
            }
            listaUsers.add(novoUsuario);
            salvarUser(); // Salvar no arquivo JSON
            return true; // Cadastro realizado com sucesso
        } else {
            return false; // Email já cadastrado
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
                    return user.getTipoDeUsuario();
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

    private ArrayList<Usuario> criarUsuariosPadrao() {
        ArrayList<Usuario> defaultUsers = new ArrayList<>();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Criar um usuário Gerente padrão
        Gerente defaultGerente = new Gerente("Admin", "admin@example.com", passwordEncoder.encode("admin123"));
        defaultUsers.add(defaultGerente);

        return defaultUsers;
    }
}
