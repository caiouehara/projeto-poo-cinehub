package br.com.cinehub.projetopoocinehub.Models;

import java.util.ArrayList;

public class Catalogo {
    private ArrayList<Filme> catalogo;
    private int indexFilme;
    public Catalogo() {
        this.catalogo = new ArrayList<>();
        this.indexFilme = -1;
    }
    public boolean verificaCatalogo(String tituloFilme) {
        for(int i = 0; i<this.catalogo.size();i++){
            if(catalogo.get(i).getTitulo().equals(tituloFilme)){
                indexFilme = i;
                return true; //Filme está no catálogo
            }
        }
        return false; //Filme não está no catálogo
    }
    public void setCatalogo(ArrayList<Filme> catalogo) {
        this.catalogo = catalogo;
    }
    public ArrayList<Filme> getCatalogo() {
        return catalogo;
    }
    public void adicionarAoCatalogo(int anoFilme, String tituloFilme,
                                    String sinopseFilme, double avaliacaoFilme, double duracaoFilme,
                                    double precoFilmeCompra, double precoFilmeAluguel, int diasAluguel,
                                    int qtdUsuariosAvaliaram, String imagem){
        if(!verificaCatalogo(tituloFilme)){ //se o filme não tá no catalogo ele é adicionado ao catalogo
            Filme novoFilme = new Filme(anoFilme, tituloFilme, sinopseFilme, avaliacaoFilme, duracaoFilme, precoFilmeCompra, precoFilmeAluguel, diasAluguel, qtdUsuariosAvaliaram, imagem);
            catalogo.add(novoFilme);
            FilmesModel.setFilmes(catalogo);
        }
        else{
            System.out.println("Não foi possível adicionar o filme, pois ele já está no catálogo");
        }
    }
    public void removerDoCatalogo(String tituloFilme) {
        if(verificaCatalogo(tituloFilme)){
            catalogo.remove(indexFilme); //Retira o filme do catalogo
            FilmesModel.setFilmes(catalogo);
        }
    }
    public void exibirLista(){
        for(int i = 0; i<this.catalogo.size();i++){
            System.out.println(catalogo.get(i).getTitulo() + " - " + catalogo.get(i).getAnoFilme());
            System.out.println(catalogo.get(i).getSinopseFilme() + "\n" + catalogo.get(i).getAvaliacaoFilme() + "->"
                    + catalogo.get(i).getDuracaoFilme() + " Compra - R$" + catalogo.get(i).getPrecoFilmeCompra()
                    + " Aluguel - R$" + catalogo.get(i).getPrecoFilmeAluguel());
            System.out.println();
        }
    }
}
