package br.com.cinehub.projetopoocinehub.Models.Aluguel;

import br.com.cinehub.projetopoocinehub.Models.Filmes.Filme;

/**
 * Classe auxiliar para armazenar dados de Aluguel e Filme juntos.
 */
public class AluguelFilmeData {
    private Aluguel aluguel;
    private Filme filme;

    public AluguelFilmeData(Aluguel aluguel, Filme filme) {
        this.aluguel = aluguel;
        this.filme = filme;
    }

    public Aluguel getAluguel() {
        return aluguel;
    }

    public Filme getFilme() {
        return filme;
    }
}
