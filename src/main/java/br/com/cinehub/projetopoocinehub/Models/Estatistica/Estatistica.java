package br.com.cinehub.projetopoocinehub.Models.Estatistica;
import br.com.cinehub.projetopoocinehub.Models.Aluguel.Aluguel;
import br.com.cinehub.projetopoocinehub.Models.Compras.Compra;
import br.com.cinehub.projetopoocinehub.Models.Filmes.Filme;
import br.com.cinehub.projetopoocinehub.Models.Filmes.FilmesModel;
import br.com.cinehub.projetopoocinehub.Models.User.CadastroModel;
import br.com.cinehub.projetopoocinehub.Models.User.Usuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.ServletContext;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Estatistica {

    private static ArrayList<Usuario> listaUsers;
    private static ArrayList<Filme> listaFilmes;
    private static ArrayList<Aluguel> listaAluguel;
    private static ArrayList<Compra> listaCompra;
    private static final String USERS_JSON = "users.json";
    private static final String FILMES_JSON = "filmes.json";
    private static final String ALUGUEL_JSON = "alugueis.json";
    private static final String COMPRA_JSON = "compras.json";
    private final String dataDir;
    private ServletContext context;

    public Estatistica() {
        this.dataDir = System.getProperty("user.home") + "/cinehub/data";
        listaUsers = loadUsers();
        listaFilmes = loadFilmes();
        listaAluguel = loadAlugueis();
        listaCompra = loadCompra();
    }

    //leitura do json dos usuários e armazenamento em um arraylist de objetos de usuários
    private ArrayList<Usuario> loadUsers() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Path filePath = Paths.get(dataDir, USERS_JSON); //gera filePath.toString(); // "/home/user/cinehub/data/users.json"
        if (!Files.exists(filePath)) {
            // Se o arquivo não existir, avisa que nenhum cliente foi achado
            System.out.println("Erro ao encontrar arquivo!!");
            return new ArrayList<>();
        }
        try {
            return mapper.readValue(Files.newInputStream(filePath), new TypeReference<ArrayList<Usuario>>() {});
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo " + USERS_JSON + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }
    //------------------------------------------------------------------------------------------------------------------------------------


    //leitura do json dos filmes e armazenamento em um arraylist de objetos de filmes
    private ArrayList<Filme> loadFilmes() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Path filePathFilme = Paths.get(dataDir, FILMES_JSON); //gera filePath.toString(); // "/home/user/cinehub/data/filmes.json"
        if (!Files.exists(filePathFilme)) {
            // Se o arquivo não existir, avisa que nenhum cliente foi achado
            System.out.println("Erro ao encontrar arquivo!!");
            return new ArrayList<>();
        }
        try {
            return mapper.readValue(Files.newInputStream(filePathFilme), new TypeReference<ArrayList<Filme>>() {});
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo " + FILMES_JSON + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }
    //------------------------------------------------------------------------------------------------------------------------------------



    //leitura do json dos alugies e armazenamento em um arraylist de objetos de alugueis
    private ArrayList<Aluguel> loadAlugueis() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        Path filePathAluguel = Paths.get(dataDir, ALUGUEL_JSON); //gera filePath.toString(); // "/home/user/cinehub/data/alugueis.json"
        if (!Files.exists(filePathAluguel)) {
            // Se o arquivo não existir, avisa que nenhum cliente foi achado
            System.out.println("Erro ao encontrar arquivo!!");
            return new ArrayList<>();
        }
        try {
            return mapper.readValue(Files.newInputStream(filePathAluguel), new TypeReference<ArrayList<Aluguel>>() {});
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo " + ALUGUEL_JSON + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }
    //------------------------------------------------------------------------------------------------------------------------------------



    //leitura do json das compras e armazenamento em um arraylist de objetos de compras
    private ArrayList<Compra> loadCompra() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        Path filePathCompra = Paths.get(dataDir, COMPRA_JSON); //gera filePath.toString(); // "/home/user/cinehub/data/compras.json"
        if (!Files.exists(filePathCompra)) {
            // Se o arquivo não existir, avisa que nenhum cliente foi achado
            System.out.println("Erro ao encontrar arquivo!!");
            return new ArrayList<>();
        }
        try {
            return mapper.readValue(Files.newInputStream(filePathCompra), new TypeReference<ArrayList<Compra>>() {});
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo " + COMPRA_JSON + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }
    //------------------------------------------------------------------------------------------------------------------------------------

    //metodo retorna quantidade de filmes presentes no catálogo
    public int quantidadeFilmes() {
        //se lista de filmes não estiver vazia, retorna seu tamanho
        if(!listaFilmes.isEmpty()){
            return listaFilmes.size();
        } else { //se lista está vazia retorna valor zero (ou seja não há filmes no catálogo)
            return 0;
        }
    }
    //------------------------------------------------------------------------------------------------------------------------------------

    //metodo retorna quantidade de clientes cadastrados
    public int quantidadeClientes() {
        int quantidade = 0;
        //se lista de usuários não estiver vazia
        if(!listaUsers.isEmpty()) {
            for (Usuario listaUser : listaUsers) {
                if (listaUser.getTipoDeUsuario().equals("Cliente")) {
                    quantidade++;
                }
            }
        }
        return quantidade;
    }
    //------------------------------------------------------------------------------------------------------------------------------------

    //metodo retorna lucro com os alugueis
    public double lucroAlugueis() {
        double lucroAluguel = 0;
        FilmesModel filmes = new FilmesModel(context);
        Filme filme;
        //se lista de usuários não estiver vazia
        if(!listaAluguel.isEmpty()) {
            for (int i = 0; i < listaAluguel.size(); i++) {
                filme = FilmesModel.buscarFilmePorId(listaAluguel.get(i).getFilmeId());
                if(filme!=null) {
                    lucroAluguel += filme.getPrecoFilmeAluguel();
                }
            }

        }
        return lucroAluguel;
    }
    //------------------------------------------------------------------------------------------------------------------------------------

    //metodo retorna lucro com as compras
    public double lucroCompras() {
        double lucroCompra = 0;
        Filme filme;
        FilmesModel filmes = new FilmesModel(context);
        //se lista de usuários não estiver vazia
        if(!listaCompra.isEmpty()) {
            for (int i = 0; i < listaCompra.size(); i++) {
                filme = FilmesModel.buscarFilmePorId(listaCompra.get(i).getFilmeId());
                if (filme != null) {
                    lucroCompra += filme.getPrecoFilmeCompra();
                }
            }

        }
        return lucroCompra;
    }
    //------------------------------------------------------------------------------------------------------------------------------------


    public double lucroTotal() {
        double lucroA = lucroAlugueis();
        double lucroC = lucroCompras();
        return lucroC+lucroA;
    }

    public static void main(String args[]) {
        Estatistica statistics = new Estatistica();
        int clientes = statistics.quantidadeClientes();
        //int filmes = statistics.quantidadeFilmes();
        double lucroAluguel = statistics.lucroAlugueis();
        double lucroCompras = statistics.lucroCompras();
        double lucroTotal = statistics.lucroTotal();
        System.out.println("Número Total de Clientes: " + clientes);
        //System.out.println("Filmes: " + filmes);
        System.out.println("Lucro com Alugueis: " + lucroAluguel);
        System.out.println("Lucro com Compras: " + lucroCompras);
        System.out.println("Lucro Total: " + lucroTotal);

    }
}
