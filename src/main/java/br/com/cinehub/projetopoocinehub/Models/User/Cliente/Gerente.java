package br.com.cinehub.projetopoocinehub.Models.User.Cliente;

import br.com.cinehub.projetopoocinehub.Comentarios;
import br.com.cinehub.projetopoocinehub.Models.Filmes.Filme;
import br.com.cinehub.projetopoocinehub.Usuario;

public class Gerente extends Usuario {
    public Gerente(String nome, String email, String senha) {
        super(nome, email, senha);
    }
    void cadastroGerente(String email, String senha, String nome) {}
    void removerUsuario(Cliente cliente) {}
    void realizarCompra(Filme filme){}
    void apagarComentario(Comentarios comentario){}
    //void adicionarFilme(Filme filme, Sistema catalogo){}




}
