public class Aluguel {

    private Filmes[] listaFilmesAlugados;
    private Filmes[] listaFilmesAlugadosTemporario;

    public Filmes[] getListaFilmesAlugados() {
        return this.listaFilmesAlugados;
    }

    public void setListaFilmesAlugados(Filmes[] listaFilmesAlugados) {
        this.listaFilmesAlugados = listaFilmesAlugados;
    }

    public Filmes[] getListaFilmesAlugadosTemporario() {
        return this.listaFilmesAlugadosTemporario;
    }

    public void setListaFilmesAlugadosTemporario(Filmes[] listaFilmesAlugadosTemporario) {
        this.listaFilmesAlugadosTemporario = listaFilmesAlugadosTemporario;
    }

    public boolean verificarAluguel(Filmes filme) {
        for (int i = 0; i < listaFilmesAlugadosTemporario.length; i++) {
            if (listaFilmesAlugadosTemporario[i] == filme) {
               return false;
            }
        }
        return true;
    }

}
