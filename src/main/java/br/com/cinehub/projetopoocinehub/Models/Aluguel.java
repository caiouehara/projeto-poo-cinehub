package br.com.cinehub.projetopoocinehub.Models;

public class Aluguel {
    private Filme[] listaFilmesAlugados;
    private Filme[] listaFilmesAlugadosTemporario;

    public Filme[] getListaFilmesAlugados() {
        return this.listaFilmesAlugados;
    }

    public void setListaFilmesAlugados(Filme[] listaFilmesAlugados) {
        this.listaFilmesAlugados = listaFilmesAlugados;
    }

    public Filme[] getListaFilmesAlugadosTemporario() {
        return this.listaFilmesAlugadosTemporario;
    }

    public void setListaFilmesAlugadosTemporario(Filme[] listaFilmesAlugadosTemporario) {
        this.listaFilmesAlugadosTemporario = listaFilmesAlugadosTemporario;
    }

    public boolean verificarAluguel(Filme filme) {
        for (int i = 0; i < listaFilmesAlugadosTemporario.length; i++) {
            if (listaFilmesAlugadosTemporario[i] == filme) {
                return false;
            }
        }
        return true;
    }
}
