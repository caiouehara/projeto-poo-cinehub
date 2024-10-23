import java.util.Scanner;

public class MenuInicial {
    
    public static void Menu(Scanner sc, Cadastro cadastro) {
        int n;
        System.out.println("Selecione opção de cadastro ou login: ");
        System.out.println("0 - Cadastrar \n1 - Login");
        n = sc.nextInt();
        sc.nextLine();
        switch (n) {
            case 1:
                MenuLogin.loginMenu(sc, cadastro);
                break;
            case 0:
                MenuLogin.cadastroMenu(sc, cadastro);
                break;
            default:
                System.out.println("Selecione uma opção válida!");
                break;
        }
    }

}

