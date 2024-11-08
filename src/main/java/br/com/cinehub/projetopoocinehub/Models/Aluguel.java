package br.com.cinehub.projetopoocinehub.Models;
import java.time.LocalDate;
import java.util.ArrayList;

public class Aluguel {
    private LocalDate dataAluguel;
    private LocalDate dataFinal;
    private ArrayList<Filme> filmesAlugados;

    //construtores
    public Aluguel() {
        dataAluguel = LocalDate.now(); //pega a data em que filme foi alugado
        dataFinal = dataAluguel.plusDays(7);
        filmesAlugados = new ArrayList<>();
    }
    //getters e setters
    public LocalDate getDataAluguel() {
        return dataAluguel;
    }
    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public ArrayList<Filme> getFilmesAlugados() {
        return this.filmesAlugados;
    }

    public void setFilmesAlugados(ArrayList<Filme> filmesAlugados) {
        this.filmesAlugados = filmesAlugados;
    }

    public Compra getCompra() {
        return this.compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Aluguel getAluguel() {
        return this.aluguel;
    }

    public void setAluguel(Aluguel aluguel) {
        this.aluguel = aluguel;
    }


    //verifica se filme já não foi alugado, caso já esteja alugado ele não permite alugar
    public boolean verificarAluguel(Filme filme) {
        for (int i = 0; i < filmesAlugados.size(); i++) {
            if (filmesAlugados.get(i) == filme) {
                return false;
            }
        }
        return true;
    }

    //adiciona filme à lista de alugueis
    public void adicionarListaAlugueis(Filme filme) {
        filmesAlugados.add(filme);
    }


    //método que remove filme da lista dos alugueis após data de vencimento do aluguel
    public boolean fimAluguel(Filme filme) {
        LocalDate hoje = LocalDate.now();
        if(hoje.isAfter(dataFinal)) {
            filmesAlugados.remove(filme);
            return true;
        } else {
            return false;
        }
    }

    //imprime filmes alugados
    public void imprimirFilmesAlugados() {
    	if(filmesAlugados.size()>0) {
    		System.out.println("==================================================================================================================");
            System.out.println("                                                Filmes Alugados                                                   ");
            System.out.println("==================================================================================================================");
        	Filme.imprimirFilmes(filmesAlugados);
    	} else {
    		System.out.println("Você ainda não alugou nenhum filme!");
    	}
    }
}
