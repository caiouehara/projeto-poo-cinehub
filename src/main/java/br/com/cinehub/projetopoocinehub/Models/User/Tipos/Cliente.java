// Cliente.java
package br.com.cinehub.projetopoocinehub.Models.User.Tipos;

import br.com.cinehub.projetopoocinehub.Comentarios;
import br.com.cinehub.projetopoocinehub.Compra;
import br.com.cinehub.projetopoocinehub.Models.Filmes.Filme;
import br.com.cinehub.projetopoocinehub.Models.User.Usuario;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Cliente extends Usuario {
    private double gastoAluguel = 0;
    private double gastoCompra = 0;
    private double gastoTotal = 0;

    @JsonIgnore
    private Compra compra;

    public Cliente() {
        super();
        this.tipoDeUsuario = "Cliente";
    }

    @JsonCreator
    public Cliente(
            @JsonProperty("nome") String nome,
            @JsonProperty("email") String email,
            @JsonProperty("senha") String senha,
            @JsonProperty("gastoAluguel") double gastoAluguel,
            @JsonProperty("gastoCompra") double gastoCompra,
            @JsonProperty("gastoTotal") double gastoTotal
    ) {
        super(nome, email, senha, "Cliente");
        this.gastoAluguel = gastoAluguel;
        this.gastoCompra = gastoCompra;
        this.gastoTotal = gastoTotal;
    }

    // Getters e Setters

    @JsonProperty("gastoAluguel")
    public double getGastoAluguel() {
        return gastoAluguel;
    }

    public void setGastoAluguel(double gastoAluguel) {
        this.gastoAluguel = gastoAluguel;
    }

    @JsonProperty("gastoCompra")
    public double getGastoCompra() {
        return gastoCompra;
    }

    public void setGastoCompra(double gastoCompra) {
        this.gastoCompra = gastoCompra;
    }

    @JsonProperty("gastoTotal")
    public double getGastoTotal() {
        return gastoTotal;
    }

    public void setGastoTotal(double gastoTotal) {
        this.gastoTotal = gastoTotal;
    }

    // Métodos Específicos do Cliente

    public void avaliarFilme(Filme filme, double nota) {
        double notaAtual = filme.getAvaliacaoFilme();
        int usuariosAvaliaram = filme.getUsuariosAvaliaram();
        nota = (notaAtual * usuariosAvaliaram) + nota;
        usuariosAvaliaram++;
        nota = nota / usuariosAvaliaram;
        filme.setAvaliacaoFilme(nota);
        filme.setUsuariosAvaliaram(usuariosAvaliaram);
    }

    public void deslikeComentario(Comentarios comentario) {
        int deslikesAtuais = comentario.getQuantidadeDeslikes();
        deslikesAtuais++;
        comentario.setQuantidadeDeslikes(deslikesAtuais);
    }

    public void likeComentario(Comentarios comentario) {
        int likesAtuais = comentario.getQuantidadeLikes();
        likesAtuais++;
        comentario.setQuantidadeLikes(likesAtuais);
    }

    public void realizarCompra(Filme filme) {
        if (compra.verificarCompra(filme)) {
            double preco = filme.getPrecoFilmeCompra();
            gastoCompra += preco;
            setGastoCompra(gastoCompra);
        }
    }

    public void realizarAluguel(Filme filme) {
        double preco = filme.getPrecoFilmeAluguel();
        gastoAluguel += preco;
        setGastoAluguel(gastoAluguel);
    }
}
