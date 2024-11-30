package br.com.cinehub.projetopoocinehub.Models.Aluguel;

import br.com.cinehub.projetopoocinehub.Models.Filmes.Filme;

/**
 * Classe auxiliar que armazena dados de um aluguel e o filme correspondente.
 * Utilizada para combinar informações de um aluguel e seu respectivo filme.
 */
public class AluguelFilmeData {
    /**
     * Objeto que contém os dados de um aluguel.
     */
    private Aluguel aluguel;
    /**
     * Objeto que contém os dados de um filme.
     */
    private Filme filme;

     /**
     * Construtor que inicializa os objetos de aluguel e filme.
     * 
     * @param aluguel O objeto de aluguel relacionado ao filme.
     * @param filme O objeto de filme relacionado ao aluguel.
     */
    public AluguelFilmeData(Aluguel aluguel, Filme filme) {
        this.aluguel = aluguel;
        this.filme = filme;
    }

    /**
     * Retorna o objeto de aluguel associado.
     * 
     * @return O objeto {@link Aluguel} que representa o aluguel.
     */
    public Aluguel getAluguel() {
        return aluguel;
    }

    /**
     * Retorna o objeto de filme associado.
     * 
     * @return O objeto {@link Filme} que representa o filme alugado.
     */
    public Filme getFilme() {
        return filme;
    }
}
