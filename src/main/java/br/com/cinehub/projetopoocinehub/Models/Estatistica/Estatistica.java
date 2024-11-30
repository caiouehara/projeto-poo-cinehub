package br.com.cinehub.projetopoocinehub.Models.Estatistica;

import br.com.cinehub.projetopoocinehub.Models.Aluguel.AluguelModel;
import br.com.cinehub.projetopoocinehub.Models.Compras.CompraModel;
import br.com.cinehub.projetopoocinehub.Models.Filmes.Filme;
import br.com.cinehub.projetopoocinehub.Models.Filmes.FilmesModel;
import br.com.cinehub.projetopoocinehub.Models.User.CadastroModel;
/**
 * Classe responsável por calcular e fornecer estatísticas relacionadas ao sistema de filmes.
 * Ela fornece métodos para calcular a quantidade de filmes, a quantidade de clientes, 
 * o lucro gerado por aluguéis e compras, e o lucro total do sistema.
 */
public class Estatistica {

    /**
     * Objeto que contém informações sobre os filmes no sistema.
     */
    private FilmesModel filmesModel;
    /**
     * Objeto que contém informações sobre os cadastros de usuários no sistema.
     */
    private CadastroModel cadastroModel;
     /**
     * Objeto que contém informações sobre os aluguéis realizados no sistema.
     */
    private AluguelModel aluguelModel;
    /**
     * Objeto que contém informações sobre as compras realizadas no sistema.
     */
    private CompraModel compraModel;

    /**
     * Construtor que inicializa a classe com os modelos necessários.
     * 
     * @param filmesModel  O modelo de filmes.
     * @param cadastroModel O modelo de cadastro de usuários.
     * @param aluguelModel O modelo de aluguéis.
     * @param compraModel O modelo de compras.
     */
    public Estatistica(FilmesModel filmesModel, CadastroModel cadastroModel, AluguelModel aluguelModel, CompraModel compraModel) {
        this.filmesModel = filmesModel;
        this.cadastroModel = cadastroModel;
        this.aluguelModel = aluguelModel;
        this.compraModel = compraModel;
    }

    /**
     * Obtém a quantidade total de filmes no sistema.
     * 
     * @return O número total de filmes cadastrados.
     */
    public int quantidadeFilmes() {
        return filmesModel.getListaFilmes().size();
    }

    /**
     * Obtém a quantidade total de clientes no sistema.
     * 
     * @return O número total de usuários cadastrados.
     */
    public int quantidadeClientes() {
        return cadastroModel.getListaUsers().size();
    }

    /**
     * Calcula o lucro gerado pelos aluguéis no sistema.
     * 
     * @return O valor total arrecadado com os aluguéis.
     */
    public double lucroAlugueis() {
        return aluguelModel.getListaAlugueis().stream()
                .mapToDouble(aluguel -> {
                    Filme filme = filmesModel.buscarFilmePorId(aluguel.getFilmeId());
                    return filme != null ? filme.getPrecoFilmeAluguel() : 0;
                })
                .sum();
    }

    /**
     * Calcula o lucro gerado pelas compras no sistema.
     * 
     * @return O valor total arrecadado com as compras.
     */
    public double lucroCompras() {
        return compraModel.getListaCompra().stream()
                .mapToDouble(compra -> {
                    Filme filme = filmesModel.buscarFilmePorId(compra.getFilmeId());
                    return filme != null ? filme.getPrecoFilmeCompra() : 0;
                })
                .sum();
    }
     /**
     * Calcula o lucro total gerado pelo sistema, somando os lucros de aluguéis e compras.
     * 
     * @return O valor total arrecadado com aluguéis e compras.
     */
    public double lucroTotal() {
        return lucroAlugueis() + lucroCompras();
    }
}
