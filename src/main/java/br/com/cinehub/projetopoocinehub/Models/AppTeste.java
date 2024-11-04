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
        String tituloFilme, sinopseFilme,imagem;
        int anoFilme,diasAluguel,qtdUsuariosAvaliaram;
        double avaliacaoFilme,duracaoFilme,precoFilmeCompra,precoFilmeAluguel;


        Gerente gerente = new Gerente("Taysa","taysa","taysa");
        cadastro.listaGerentes.add(gerente);
        System.out.println("Realize o login: ");
        emailL = sc.nextLine();
        senhaL = sc.nextLine();
        Login.login(emailL, senhaL, cadastro);

        Catalogo catalogo = new Catalogo();
        catalogo.setCatalogo(FilmesModel.getFilmes());
        System.out.println("Filmes no catálogo: ");
        catalogo.exibirLista();

        System.out.println();
        System.out.println("Nome do filme que quer remover do catálogo: ");
        tituloFilme = sc.nextLine();
        gerente.removerFilme(catalogo, tituloFilme);
        System.out.println("Filmes no catálogo: ");
        catalogo.exibirLista();
        System.out.println();

//        System.out.println("Adicione Filme no catálogo: ");
//        System.out.println("Titulo: ");
//        tituloFilme = sc.nextLine();
//        System.out.println("Ano: ");
//        anoFilme = sc.nextInt();
//        System.out.println("Duração: ");
//        duracaoFilme = sc.nextDouble();
//        System.out.println("Sinopse: ");
//        sinopseFilme = sc.nextLine();
//        System.out.println("avaliacaoFilme: ");
//        avaliacaoFilme = sc.nextDouble();
//        System.out.println("precoFilmeCompra: ");
//        precoFilmeCompra = sc.nextDouble();
//        System.out.println("precoFilmeAluguel: ");
//        precoFilmeAluguel = sc.nextDouble();
//        System.out.println("diasAluguel: ");
//        diasAluguel = sc.nextInt();
//        System.out.println("qtdUsuariosAvaliaram: ");
//        qtdUsuariosAvaliaram = sc.nextInt();
//        System.out.println("imagem: ");
//        imagem = sc.nextLine();
//
//        catalogo.adicionarAoCatalogo(anoFilme, tituloFilme, sinopseFilme, avaliacaoFilme, duracaoFilme, precoFilmeCompra, precoFilmeAluguel, diasAluguel, qtdUsuariosAvaliaram, imagem);
//        System.out.println("Filmes no catálogo: ");
//        catalogo.exibirLista();
//        for(int i = 0;i<5;i++){
//            System.out.println("Realize o cadastro: ");
//            nomeC = sc.nextLine();
//            emailC = sc.nextLine();
//            senhaC = sc.nextLine();
//            cadastro.cadastroCliente(nomeC, emailC, senhaC);
//        }
//        System.out.println("Remova um cliente:");

//        System.out.println("Realize o cadastro de um gerente: ");
//        nomeC = sc.nextLine();
//        emailC = sc.nextLine();
//        senhaC = sc.nextLine();
//        cadastro.cadastroGerente(nomeC, emailC, senhaC);

//        for(int i = 0;i<cadastro.listaClientes.size();i++){
//            System.out.println(cadastro.getListaClientes().get(i).getEmail());
//        }
//        System.out.println("Digite o email do cliente q quer remover:");
//        emailRemover = sc.nextLine();
//        cadastro.removeCliente(emailRemover);
//        System.out.println("Nova lista: ");
//        for(int i = 0;i<cadastro.listaClientes.size();i++){
//            System.out.println(cadastro.getListaClientes().get(i).getEmail());
//        }


    }
}
