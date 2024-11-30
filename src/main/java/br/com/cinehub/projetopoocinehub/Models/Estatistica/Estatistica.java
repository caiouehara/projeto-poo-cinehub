package br.com.cinehub.projetopoocinehub.Models.Estatistica;

import br.com.cinehub.projetopoocinehub.Models.Aluguel.AluguelModel;
import br.com.cinehub.projetopoocinehub.Models.Compras.CompraModel;
import br.com.cinehub.projetopoocinehub.Models.Filmes.Filme;
import br.com.cinehub.projetopoocinehub.Models.Filmes.FilmesModel;
import br.com.cinehub.projetopoocinehub.Models.User.CadastroModel;

public class Estatistica {

    private FilmesModel filmesModel;
    private CadastroModel cadastroModel;
    private AluguelModel aluguelModel;
    private CompraModel compraModel;

    public Estatistica(FilmesModel filmesModel, CadastroModel cadastroModel, AluguelModel aluguelModel, CompraModel compraModel) {
        this.filmesModel = filmesModel;
        this.cadastroModel = cadastroModel;
        this.aluguelModel = aluguelModel;
        this.compraModel = compraModel;
    }

    public int quantidadeFilmes() {
        return filmesModel.getListaFilmes().size();
    }

    public int quantidadeClientes() {
        return cadastroModel.getListaUsers().size();
    }

    public double lucroAlugueis() {
        return aluguelModel.getListaAlugueis().stream()
                .mapToDouble(aluguel -> {
                    Filme filme = filmesModel.buscarFilmePorId(aluguel.getFilmeId());
                    return filme != null ? filme.getPrecoFilmeAluguel() : 0;
                })
                .sum();
    }

    public double lucroCompras() {
        return compraModel.getListaCompra().stream()
                .mapToDouble(compra -> {
                    Filme filme = filmesModel.buscarFilmePorId(compra.getFilmeId());
                    return filme != null ? filme.getPrecoFilmeCompra() : 0;
                })
                .sum();
    }

    public double lucroTotal() {
        return lucroAlugueis() + lucroCompras();
    }
}
