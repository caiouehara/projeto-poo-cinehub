package br.com.cinehub.projetopoocinehub.Models;

public class Filme {
    private int anoFilme;
    private String tituloFilme;
    private String sinopseFilme;
    private double avaliacaoFilme = 0;
    private double duracaoFilme;
    private double precoFilmeCompra;
    private double precoFilmeAluguel;
    private int diasAluguel;
    private int usuariosAvaliaram = 0;

    public Filmes(int ano, String titulo, String sinopse, double precoAluguel, double precoCompra, int dias) {
        anoFilme = ano;
        tituloFilme = titulo;
        sinopseFilme = sinopse;
        precoFilmeAluguel = precoAluguel;
        precoFilmeCompra = precoCompra;
        diasAluguel = dias;
    }

    public int getUsuariosAvaliaram() {
        return this.usuariosAvaliaram;
    }

    public void setUsuariosAvaliaram(int usuariosAvaliaram) {
        this.usuariosAvaliaram = usuariosAvaliaram;
    }

    public int getAnoFilme() {
        return this.anoFilme;
    }

    public void setAnoFilme(int anoFilme) {
        this.anoFilme = anoFilme;
    }

    public String getTituloFilme() {
        return this.tituloFilme;
    }

    public void setTituloFilme(String tituloFilme) {
        this.tituloFilme = tituloFilme;
    }

    public String getSinopseFilme() {
        return this.sinopseFilme;
    }

    public void setSinopseFilme(String sinopseFilme) {
        this.sinopseFilme = sinopseFilme;
    }

    public double getAvaliacaoFilme() {
        return this.avaliacaoFilme;
    }

    public void setAvaliacaoFilme(double avaliacaoFilme) {
        this.avaliacaoFilme = avaliacaoFilme;
    }

    public double getDuracaoFilme() {
        return this.duracaoFilme;
    }

    public void setDuracaoFilme(double duracaoFilme) {
        this.duracaoFilme = duracaoFilme;
    }

    public double getPrecoFilmeCompra() {
        return this.precoFilmeCompra;
    }

    public void setPrecoFilmeCompra(double precoFilmeCompra) {
        this.precoFilmeCompra = precoFilmeCompra;
    }

    public double getPrecoFilmeAluguel() {
        return this.precoFilmeAluguel;
    }

    public void setPrecoFilmeAluguel(double precoFilmeAluguel) {
        this.precoFilmeAluguel = precoFilmeAluguel;
    }

    public int getDiasAluguel() {
        return this.diasAluguel;
    }

    public void setDiasAluguel(int diasAluguel) {
        this.diasAluguel = diasAluguel;
    }
}

