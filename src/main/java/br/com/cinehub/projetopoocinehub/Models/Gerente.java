package br.com.cinehub.projetopoocinehub.Models;

public class Gerente extends Usuario {
    public Gerente(String nome, String email, String senha) {
        super(nome, email, senha);
    }
    //Funcionalidade do Gerente de cadastrar um novo Gerente
    public void cadastroGerente(Cadastro cadastro, String email, String senha, String nome) {
        cadastro.cadastroGerente(email, senha, nome);
    }
    //Remover o acesso de um usuario
    public void removerUsuario(Cadastro cadastro,String email) {
        cadastro.removeCliente(email);
    }
    //Remover o acesso de um gerente
    public void removerGerente(Cadastro cadastro,String email) {
        cadastro.removeGerente(email);
    }
    //Cadastrar um novo filme no catalogo, após passar os dados do novo filme, ele chama a classe do catalogo e verifica se o filme já está cadastrado, se não, ele adiciona ao catalogo criando uma nova instancia de filme e adicionando ao arrayList do catalogo
    public void cadastrarFilme(Catalogo catalogo, int anoFilme, String tituloFilme, String sinopseFilme, double avaliacaoFilme, double duracaoFilme, double precoFilmeCompra, double precoFilmeAluguel, int diasAluguel, int usuariosAvaliaram, String imagem) {
        catalogo.adicionarAoCatalogo(anoFilme, tituloFilme, sinopseFilme, avaliacaoFilme, duracaoFilme, precoFilmeCompra, precoFilmeAluguel, diasAluguel, usuariosAvaliaram, imagem);
    }
    public void removerFilme(Catalogo catalogo, String tituloFilme) {
        catalogo.removerDoCatalogo(tituloFilme);
    }
    void apagarComentario(Comentarios comentario){}
    //void adicionarFilme(Filme filme, Sistema catalogo){}




}
