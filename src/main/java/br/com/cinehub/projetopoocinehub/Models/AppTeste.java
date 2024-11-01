package br.com.cinehub.projetopoocinehub.Models;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AppTeste {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Cadastro cadastro = new Cadastro();
        String nomeC, emailC, senhaC;
        String nomeL, emailL, senhaL;
        String emailRemover;


        Gerente gerente = new Gerente("Taysa","taysa","taysa");
        cadastro.listaGerentes.add(gerente);
        System.out.println("Realize o login: ");
        emailL = sc.nextLine();
        senhaL = sc.nextLine();
        Login.login(emailL, senhaL, cadastro);

        for(int i = 0;i<5;i++){
            System.out.println("Realize o cadastro: ");
            nomeC = sc.nextLine();
            emailC = sc.nextLine();
            senhaC = sc.nextLine();
            cadastro.cadastroCliente(nomeC, emailC, senhaC);
        }
        System.out.println("Remova um cliente:");

//        System.out.println("Realize o cadastro de um gerente: ");
//        nomeC = sc.nextLine();
//        emailC = sc.nextLine();
//        senhaC = sc.nextLine();
//        cadastro.cadastroGerente(nomeC, emailC, senhaC);

        for(int i = 0;i<cadastro.listaClientes.size();i++){
            System.out.println(cadastro.getListaClientes().get(i).getEmail());
        }
        System.out.println("Digite o email do cliente q quer remover:");
        emailRemover = sc.nextLine();
        cadastro.removeCliente(emailRemover);
        System.out.println("Nova lista: ");
        for(int i = 0;i<cadastro.listaClientes.size();i++){
            System.out.println(cadastro.getListaClientes().get(i).getEmail());
        }


    }
}
