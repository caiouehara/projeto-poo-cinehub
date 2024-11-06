import java.util.Scanner;
public class Main {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        Cadastro cadastro = new Cadastro();
        Menu.menuInicial(sc, cadastro);
        sc.close();
    }

}
