package br.com.cinehub.projetopoocinehub.Models;
import java.util.ArrayList;

public class Compra {
    private ArrayList<Filme> listaFilmesComprados;
    private ArrayList<Filme> listaFilmesCompradosTemporario;

    public ArrayList<Filme> getListaFilmesComprados() {
        return this.listaFilmesComprados;
    }

    public void setListaFilmesComprados(ArrayList<Filme> listaFilmesComprados) {
        this.listaFilmesComprados = listaFilmesComprados;
    }

    public ArrayList<Filme> getListaFilmesCompradosTemporario() {
        return this.listaFilmesCompradosTemporario;
    }

    public void setListaFilmesCompradosTemporario(ArrayList<Filme> listaFilmesCompradosTemporario) {
        this.listaFilmesCompradosTemporario = listaFilmesCompradosTemporario;
    }

    public Compra () {
        listaFilmesComprados = new ArrayList<>();
        listaFilmesCompradosTemporario = new ArrayList<>();

    }

    public boolean verificarCompra(Filme filme) {
        for (int i = 0; i < listaFilmesComprados.size(); i++) {
            if (listaFilmesComprados.get(i) == filme) {
                return false;
            }
        }
        return true;
    }

    public void removerCarrinhoCompra(Filme filme) {
        if(verificarCompra(filme)) {
            listaFilmesCompradosTemporario.remove(filme);
        }
    }

    public void adicionarCarrinhoCompra(Filme filme) {
        if(verificarCompra(filme)) {
            System.out.println("Filme já presente no carrinho");
        } else {
            listaFilmesCompradosTemporario.add(filme);
        }

    }

    public void adicionarListaComprados(Filme filme) {

    }
}
