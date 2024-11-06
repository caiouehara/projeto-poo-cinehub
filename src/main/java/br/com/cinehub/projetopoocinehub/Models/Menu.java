import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public static void menuInicial (Scanner sc, Cadastro cadastro) {
        int n;
        System.out.println("Selecione opção se cadastrar ou login: ");
        System.out.println("0 - Cadastrar Cliente \n1 - Login \n");
        n = sc.nextInt();
        sc.nextLine();
        switch (n) {
            case 1:
                loginMenu(sc, cadastro);
                break;
            case 0:
                cadastroMenu(sc, cadastro);
                break;
            default:
                System.out.println("Selecione uma opção válida!");
                break;
        }
    }


    public static void loginMenu(Scanner sc, Cadastro cadastro) {
        String email, senha;
        System.out.println("Email: ");
        email = sc.nextLine();
        System.out.println("Senha: ");
        senha = sc.nextLine();
        Login.login(email, senha, cadastro);
        menuLogado();

    }

    public static void cadastroMenu(Scanner sc, Cadastro cadastro) {
        int n;
        System.out.println("Selecione opção de cadastro ou cliente: ");
        System.out.println("0 - Gerente \n1 - Cliente");
        n = sc.nextInt();
        sc.nextLine();
        switch (n) {
            case 0:
                String emailG, senhaG, nomeG;
                System.out.println("Nome: ");
                nomeG = sc.nextLine();
                System.out.println("Email: ");
                emailG = sc.nextLine();
                System.out.println("Senha: ");
                senhaG = sc.nextLine();
                cadastro.cadastroGerente(nomeG, emailG, senhaG);
                menuInicial(sc, cadastro);
                break;
        
            case 1:
                String emailC, senhaC, nomeC;
                System.out.println("Nome: ");
                nomeC = sc.nextLine();
                System.out.println("Email: ");
                emailC = sc.nextLine();
                System.out.println("Senha: ");
                senhaC = sc.nextLine();
                cadastro.cadastroCliente(nomeC, emailC, senhaC);
                menuInicial(sc, cadastro);
                break;
            default:
                System.out.println("Selecione uma opção válida!");
                break;
        }
}

    public static void filmeMenu(ArrayList<Filme> catalogo) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Filmes disponíveis para selecionar: ");
            Filme.imprimirFilmes(catalogo);
            System.out.print("Código do filme desejado: ");
            int n = sc.nextInt();
            if (n <= catalogo.size()) {
                Filme.imprimirFilme(catalogo.get(n-1));
            } else {
                System.out.println("Selecione opção válida! ");
            }
            sc.close();
    }

    public static void menuLogado() {

        System.out.println("Escolha uma opção: ");
        System.out.println("1 - Catálogo de filmes");
        System.out.println("2 - Filmes comprados");
        System.out.println("3 - Filmes alugados");

    }

    public static void exibirFilmesAlugados(Cliente cliente) {
        //para acessar filmes alugados pelo cliente
        ArrayList<Filme> filmes = cliente.getAluguel().getFilmesAlugados();
        Filme.imprimirFilmes(filmes);

    }

}