//import java.util.Scanner;
//
//public class MenuLogin {
//
//
//
//    public static void loginMenu(Scanner sc, Cadastro cadastro) {
//        String email, senha;
//        System.out.println("Email: ");
//        email = sc.nextLine();
//        System.out.println("Senha: ");
//        senha = sc.nextLine();
//        Login.login(email, senha, cadastro);
//
//    }
//
//    public static void cadastroMenu(Scanner sc, Cadastro cadastro) {
//        int n;
//        System.out.println("Selecione opção de cadastro de gerente ou cliente: ");
//        System.out.println("0 - Gerente \n1 - Cliente");
//        n = sc.nextInt();
//        sc.nextLine();
//        switch (n) {
//            case 0:
//                String emailG, senhaG, nomeG;
//                System.out.println("Nome: ");
//                nomeG = sc.nextLine();
//                System.out.println("Email: ");
//                emailG = sc.nextLine();
//                System.out.println("Senha: ");
//                senhaG = sc.nextLine();
//                cadastro.cadastroGerente(nomeG, emailG, senhaG);
//                MenuInicial.Menu(sc, cadastro);
//                break;
//
//            case 1:
//                String emailC, senhaC, nomeC;
//                System.out.println("Nome: ");
//                nomeC = sc.nextLine();
//                System.out.println("Email: ");
//                emailC = sc.nextLine();
//                System.out.println("Senha: ");
//                senhaC = sc.nextLine();
//                cadastro.cadastroCliente(nomeC, emailC, senhaC);
//                MenuInicial.Menu(sc, cadastro);
//                break;
//            default:
//                System.out.println("Selecione uma opção válida!");
//                break;
//        }
//    }
//
//}
