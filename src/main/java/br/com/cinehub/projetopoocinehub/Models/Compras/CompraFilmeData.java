package br.com.cinehub.projetopoocinehub.Models.Compras;

import br.com.cinehub.projetopoocinehub.Models.Filmes.Filme;

/**
 * Classe auxiliar que armazena dados de uma compra e o filme correspondente.
 * Utilizada para combinar informações de uma compra e seu respectivo filme.
 */
public class CompraFilmeData {
    /**
     * Objeto que contém os dados de uma compra.
     */
    private Compra compra;
    /**
     * Objeto que contém os dados de um filme.
     */
    private Filme filme;

    /**
     * Construtor que inicializa os objetos de compra e filme.
     * 
     * @param compra O objeto de compra relacionado ao filme.
     * @param filme O objeto de filme relacionado à compra.
     */
    public CompraFilmeData(Compra compra, Filme filme) {
        this.compra = compra;
        this.filme = filme;
    }

    /**
     * Retorna o objeto de compra associado.
     * 
     * @return O objeto {@link Compra} que representa a compra.
     */
    public Compra getCompra() {
        return compra;
    }

    /**
     * Retorna o objeto de filme associado.
     * 
     * @return O objeto {@link Filme} que representa o filme comprado.
     */
    public Filme getFilme() {
        return filme;
    }
}
