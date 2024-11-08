package br.com.cinehub.projetopoocinehub.Models;

/**
 * A classe Gerente representa um usuário com permissões de administrador
 * no sistema CineHub, permitindo gerenciar outros gerentes, clientes e o catálogo de filmes.
 */

public class Gerente extends Usuario {
    /**
     * Construtor da classe Gerente.
     * @param nome O nome do gerente.
     * @param email O e-mail do gerente.
     * @param senha A senha do gerente.
     */

    public Gerente(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    /**
     * Cadastra um novo gerente no sistema.
     * @param cadastro Instância da classe Cadastro utilizada para gerenciar os usuários.
     * @param email O e-mail do novo gerente.
     * @param senha A senha do novo gerente.
     * @param nome O nome do novo gerente.
     */

    public void cadastroGerente(Cadastro cadastro, String email, String senha, String nome) {
        cadastro.cadastroGerente(email, senha, nome);
    }
    //Remover o acesso de um usuario
    public void removerCliente(Cadastro cadastro,Cliente cliente) {
        cadastro.removeCliente(cliente);
    }
    //Remover o acesso de um gerente
    public void removerGerente(Cadastro cadastro,Gerente gerente) {
        cadastro.removeGerente(gerente);
    }
    //Cadastrar um novo filme no catalogo, após passar os dados do novo filme, ele chama a classe do catalogo e verifica se o filme já está cadastrado, se não, ele adiciona ao catalogo criando uma nova instancia de filme e adicionando ao arrayList do catalogo
    public void cadastrarFilme(Catalogo catalogo, int anoFilme, String tituloFilme, String sinopseFilme, double avaliacaoFilme, double duracaoFilme, double precoFilmeCompra, double precoFilmeAluguel, int diasAluguel, int usuariosAvaliaram, String imagem) {
        catalogo.adicionarAoCatalogo(anoFilme, tituloFilme, sinopseFilme, avaliacaoFilme, duracaoFilme, precoFilmeCompra, precoFilmeAluguel, diasAluguel, usuariosAvaliaram, imagem);
    }
    public void removerFilme(Catalogo catalogo, String tituloFilme) {
        catalogo.removerDoCatalogo(tituloFilme);
    }
    public void editarFilme(){

    }
    void apagarComentario(Comentarios comentario){}
    //void adicionarFilme(Filme filme, Sistema catalogo){}




}
