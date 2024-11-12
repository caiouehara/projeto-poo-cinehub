package br.com.cinehub.projetopoocinehub.Models.Filmes;

import br.com.cinehub.projetopoocinehub.Models.User.Tipos.Cliente;

public class Comentarios {
    private String comentario;
    private String autor;

    public Comentarios (String comentario, Cliente cliente) {
        this.comentario = comentario;
        this.autor = cliente.getNome();
    }
}
