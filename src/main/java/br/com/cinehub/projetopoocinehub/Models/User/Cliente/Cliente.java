package br.com.cinehub.projetopoocinehub.Models.User.Cliente;

import br.com.cinehub.projetopoocinehub.Comentarios;
import br.com.cinehub.projetopoocinehub.Compra;
import br.com.cinehub.projetopoocinehub.Models.Filmes.Filme;
import br.com.cinehub.projetopoocinehub.Usuario;

public class Cliente extends Usuario {
    private double gastoAluguel = 0;
    private double gastoCompra = 0;
    private double gastoTotal = 0;
    private Compra compra;

    public Cliente (String nome, String email, String senha/*, Compra compra*/){
        super(nome,email,senha);
        //compra = new Compra();
    }

    public double getGastoAluguel() {
        return this.gastoAluguel;
    }

    public void setGastoAluguel(double gastoAluguel) {
        this.gastoAluguel = gastoAluguel;
    }

    public double getGastoCompra() {
        return this.gastoCompra;
    }

    public void setGastoCompra(double gastoCompra) {
        this.gastoCompra = gastoCompra;
    }

    public double getGastoTotal() {
        return this.gastoTotal;
    }

    public void setGastoTotal(double gastoTotal) {
        this.gastoTotal = gastoTotal;
    }

    public void avaliarFilme(Filme filme, double nota) {
        double notaAtual = filme.getAvaliacaoFilme();
        int usuariosAvaliaram = filme.getUsuariosAvaliaram();
        nota = (notaAtual*usuariosAvaliaram) + nota;
        usuariosAvaliaram++;
        nota = nota/usuariosAvaliaram;
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
        if(compra.verificarCompra(filme)) {
            double preco = filme.getPrecoFilmeCompra();
            preco = gastoCompra + preco;
            setGastoCompra(preco);
        }
    }

    public void realizarAluguel(Filme filme) {
        double preco = filme.getPrecoFilmeAluguel();
        preco = gastoAluguel + preco;
        setGastoAluguel(preco);
    }
}
