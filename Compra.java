import java.util.ArrayList;

public class Compra {
    private ArrayList<Filmes> listaFilmesComprados;
    private ArrayList<Filmes> listaFilmesCompradosTemporario;

    public ArrayList<Filmes> getListaFilmesComprados() {
        return this.listaFilmesComprados;
    }

    public void setListaFilmesComprados(ArrayList<Filmes> listaFilmesComprados) {
        this.listaFilmesComprados = listaFilmesComprados;
    }

    public ArrayList<Filmes> getListaFilmesCompradosTemporario() {
        return this.listaFilmesCompradosTemporario;
    }

    public void setListaFilmesCompradosTemporario(ArrayList<Filmes> listaFilmesCompradosTemporario) {
        this.listaFilmesCompradosTemporario = listaFilmesCompradosTemporario;
    }

    public Compra () {
        listaFilmesComprados = new ArrayList<>();
        listaFilmesCompradosTemporario = new ArrayList<>();

    }

    public boolean verificarCompra(Filmes filme) {
        for (int i = 0; i < listaFilmesComprados.size(); i++) {
            if (listaFilmesComprados.get(i) == filme) {
               return false;
            }
        }
        return true;
    }

    public void removerCarrinhoCompra(Filmes filme) {
        if(verificarCompra(filme)) {
            listaFilmesCompradosTemporario.remove(filme);
        }
    }

    public void adicionarCarrinhoCompra(Filmes filme) {
        if(verificarCompra(filme)) {
            System.out.println("Filme já presente no carrinho");
        } else {
            listaFilmesCompradosTemporario.add(filme);
        }

    }

    public void adicionarListaComprados(Filmes filme) {

    }
}