package br.com.cinehub.projetopoocinehub.Models.Filmes;

import br.com.cinehub.projetopoocinehub.Models.User.Tipos.Cliente;

public class Comentarios {
    private String comentario;
    private String autor;

    public Comentarios (String comentario, Cliente cliente) {
        this.comentario = comentario;
        this.autor = cliente.getNome();
    }

    public Cliente getAutor() {
        return this.autor;
    }

    public void setAutor(Cliente autor) {
        this.autor = autor;
    }

    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getQuantidadeLikes() {
        return this.quantidadeLikes;
    }

    public void setQuantidadeLikes(int quantidadeLikes) {
        this.quantidadeLikes = quantidadeLikes;
    }

    public int getQuantidadeDeslikes() {
        return this.quantidadeDeslikes;
    }

    public void setQuantidadeDeslikes(int quantidadeDeslikes) {
        this.quantidadeDeslikes = quantidadeDeslikes;
    }
}
