package br.com.cinehub.projetopoocinehub.Models;

public class Cliente extends Usuario {
    private double gastoAluguel = 0;
    private double gastoCompra = 0;
    private double gastoTotal = 0;
    private Compra compra;
    private Aluguel aluguel;

    public Cliente (String nome, String email, String senha){
        super(nome,email,senha);
        compra = new Compra();
        aluguel = new Aluguel();
    }

    //setter e getters
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

    //método para cliente avaliar filme
    public void avaliarFilme(Filme filme, double nota) {
        double notaAtual = filme.getAvaliacaoFilme();
        int qtdUsuariosAvaliaram = filme.getQtdUsuariosAvaliaram();
        nota = (notaAtual*qtdUsuariosAvaliaram) + nota;
        qtdUsuariosAvaliaram++;
        nota = nota/qtdUsuariosAvaliaram;
        filme.setAvaliacaoFilme(nota);
        filme.setQtdUsuariosAvaliaram(qtdUsuariosAvaliaram);
    }

     //método para cliente dar deslike em comentário
    public void deslikeComentario(Comentarios comentario) {
        int deslikesAtuais = comentario.getQuantidadeDeslikes();
        deslikesAtuais++;
        comentario.setQuantidadeDeslikes(deslikesAtuais);
    }

    //método para cliente dar like em comentário
    public void likeComentario(Comentarios comentario) {
        int likesAtuais = comentario.getQuantidadeLikes();
        likesAtuais++;
        comentario.setQuantidadeLikes(likesAtuais);
    }

   //método para cliente realizar compra de filme
    public void realizarCompra(Filme filme) {
        if(compra.verificarCompra(filme)) { //se filme já foi comprado antes a compra não é disponível
            double precoCompra = filme.getPrecoFilmeCompra(); //pega preço do filme
            precoCompra = gastoCompra + precoCompra; //adiciona no gasto de compras do cliente
            setGastoCompra(precoCompra); //set novo valor de gasto de compras
            compra.adicionarListaComprados(filme);//adiciona filme a lista de compras
        } else {
            System.out.println("Filme já comprado!");
        }
    }


    //método para cliente realizar aluguel de filme
    public void realizarAluguel(Filme filme) {
        if(aluguel.verificarAluguel(filme)) {
            double precoAluguel = filme.getPrecoFilmeAluguel();
            precoAluguel = gastoAluguel + precoAluguel;
            setGastoAluguel(precoAluguel);
            aluguel.adicionarListaAlugueis(filme);
    
        } else {
            System.out.println("Filme já alugado!");
        }
    }
}
