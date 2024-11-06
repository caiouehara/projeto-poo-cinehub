package br.com.cinehub.projetopoocinehub.Models;
import java.util.ArrayList;

public class Compra {
    private ArrayList<Filme> filmesComprados;

    //construtor
    public Compra() {
        filmesComprados = new ArrayList<>();
    }

    //getter e setter
    public ArrayList<Filme> getFilmesComprados() {
        return this.filmesComprados;
    }

    public void setFilmesComprados(ArrayList<Filme> filmesComprados) {
        this.filmesComprados = filmesComprados;
    }


    //método para verificar se filme já não foi comprado antes, caso foi, a compra não é permitida
    public boolean verificarCompra(Filme filme) {
        for (int i = 0; i < filmesComprados.size(); i++) {
            if (filmesComprados.get(i) == filme) {
                return false;
            }
        }
        return true;
    }

    //após compra, filme é adicionado a lista de filmes comprados, disponíveis para o cliente
    public void adicionarListaComprados(Filme filme) {
        filmesComprados.add(filme); //adiciona filme na lista de filmes comprados
    }

        //método imprime lista de filmes comprados
    public void imprimirFilmesComprados() {
    	if(filmesComprados.size() >0) {
    		System.out.println("==================================================================================================================");
            System.out.println("                                                Filmes Comprados                                                  ");
            System.out.println("==================================================================================================================");
        	Filme.imprimirFilmes(filmesComprados);
    	} else {
    		System.out.println("Nenhum filme foi comprado!");
    	}
    	
    }
}
