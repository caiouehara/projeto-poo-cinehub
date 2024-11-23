package br.com.cinehub.projetopoocinehub.Models.Compras;

import br.com.cinehub.projetopoocinehub.Models.Filmes.Filme;

/**
 * Classe auxiliar para armazenar dados de Aluguel e Filme juntos.
 */
public class CompraFilmeData {
    private Compra compra;
    private Filme filme;

    public CompraFilmeData(Compra compra, Filme filme) {
        this.compra = compra;
        this.filme = filme;
    }

    public Compra getCompra() {
        return compra;
    }

    public Filme getFilme() {
        return filme;
    }
}
