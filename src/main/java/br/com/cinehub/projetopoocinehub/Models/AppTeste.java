package br.com.cinehub.projetopoocinehub.Models;
import java.util.Scanner;

public class AppTeste {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nomeC, emailC, senhaC;
        String nomeL, emailL, senhaL;
        System.out.println("Realize o cadastro: ");
        nomeC = sc.nextLine();
        emailC = sc.nextLine();
        senhaC = sc.nextLine();
        Cadastro cadastro = new Cadastro();
        cadastro.cadastroCliente(nomeC, emailC, senhaC);
        System.out.println("Realize o login: ");
        emailL = sc.nextLine();
        senhaL = sc.nextLine();
        Login.login(emailL, senhaL, cadastro);

    }
}
