package br.com.cinehub.projetopoocinehub.Models;

public class Gerente extends Usuario {
    public Gerente(String nome, String email, String senha) {
        super(nome, email, senha);
    }
    //Funcionalidade do Gerente de cadastrar um novo Gerente
    void cadastroGerente(Cadastro cadastro, String email, String senha, String nome) {
        cadastro.cadastroGerente(email, senha, nome);
    }
    //Remover o acesso de um usuario
    void removerUsuario(Cadastro cadastro,String email) {
        cadastro.removeCliente(email);
    }
    //Remover o acesso de um gerente
    void removerGerente(Cadastro cadastro,String email) {
        cadastro.removeGerente(email);
    }
    void realizarCompra(Filme filme){}
    void apagarComentario(Comentarios comentario){}
    //void adicionarFilme(Filme filme, Sistema catalogo){}




}
