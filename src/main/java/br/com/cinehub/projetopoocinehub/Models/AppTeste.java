package br.com.cinehub.projetopoocinehub.Models;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AppTeste {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        Cadastro cadastro = new Cadastro();
//        String nomeC, emailC, senhaC;
//        String nomeL, emailL, senhaL;
//
////        for(int i = 0;i<5;i++){
////            System.out.println("Realize o cadastro: ");
////            nomeC = sc.nextLine();
////            emailC = sc.nextLine();
////            senhaC = sc.nextLine();
////            cadastro.cadastroCliente(nomeC, emailC, senhaC);
////        }
//        Gerente gerente = new Gerente("Taysa","taysa","taysa");
//        cadastro.adicionarNaListaGerente(gerente);
//        System.out.println("Realize o login: ");
//        emailL = sc.nextLine();
//        senhaL = sc.nextLine();
//        Login.login(emailL, senhaL, cadastro);
//
//
//        System.out.println("Realize o cadastro de um gerente: ");
//        nomeC = sc.nextLine();
//        emailC = sc.nextLine();
//        senhaC = sc.nextLine();
//        cadastro.cadastroGerente(nomeC, emailC, senhaC);
//
//        for(int i = 0;i<cadastro.listaGerentes.size();i++){
//            System.out.println(cadastro.getListaGerentes().get(i).getNome());
//        }
        //Printa os titulos dos filmes
        try{
            ArrayList<Filme> filmes = FilmesFile.getFilmes();
            if (filmes != null && !filmes.isEmpty()) {
                filmes.forEach(filme -> System.out.println(filme.getTituloFilme()));
            }
            else {
                System.out.println("A lista de filmes está vazia ou não pôde ser carregada.");
            }
        }
        catch(IOException e){
            System.out.println("Erro ao ler o arquivo JSON: " + e.getMessage());
        }



    }
}
