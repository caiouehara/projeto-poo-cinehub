package br.com.cinehub.projetopoocinehub.Models.User.Tipos;

import br.com.cinehub.projetopoocinehub.Models.User.Usuario;
import br.com.cinehub.projetopoocinehub.Models.User.CadastroModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Gerente extends Usuario {

    // Construtor com parâmetros e anotações para o Jackson
    @JsonCreator
    public Gerente(
            @JsonProperty("nome") String nome,
            @JsonProperty("email") String email,
            @JsonProperty("senha") String senha
    ) {
        super(nome, email, senha);
    }

    // Métodos Específicos do Gerente

    /**
     * Cadastra um novo gerente no sistema.
     *
     * @param nome          Nome do novo gerente.
     * @param email         Email do novo gerente.
     * @param senha         Senha do novo gerente.
     * @param cadastro      Instância do CadastroModel para gerenciar os gerentes.
     * @return              True se o cadastro foi realizado com sucesso, false caso contrário.
     */
    public boolean cadastrarGerente(String nome, String email, String senha, CadastroModel cadastro) {
        if (cadastro.verificarCadastroUser(email)) {
            Gerente novoGerente = new Gerente(nome, email, senha);
            cadastro.getListaUsers().add(novoGerente);
            cadastro.salvarUser(); // Salva a lista atualizada de usuários
            System.out.println("Novo gerente cadastrado com sucesso!");
            return true;
        } else {
            System.out.println("Erro: Gerente com este email já está cadastrado.");
            return false;
        }
    }

    /**
     * Remove um cliente do sistema.
     *
     * @param email         Email do cliente a ser removido.
     * @param cadastro      Instância do CadastroModel para gerenciar os clientes.
     * @return              True se o cliente foi removido com sucesso, false caso contrário.
     */
    public boolean removerCliente(String email, CadastroModel cadastro) {
        Cliente clienteARemover = null;
        for (Usuario user : cadastro.getListaUsers()) {
            if (user instanceof Cliente) {
                Cliente cliente = (Cliente) user;
                if (cliente.getEmail().equals(email)) {
                    clienteARemover = cliente;
                    break;
                }
            }
        }
        if (clienteARemover != null) {
            cadastro.getListaUsers().remove(clienteARemover);
            cadastro.salvarUser(); // Salva a lista atualizada de usuários
            System.out.println("Cliente removido com sucesso!");
            return true;
        } else {
            System.out.println("Erro: Cliente não encontrado.");
            return false;
        }
    }

    /**

    /**
     * Realiza a compra de um filme para o catálogo.
     *
     * @param filme         Instância do filme a ser adquirido.
     * @param catalogo      Instância do CatalogoFilmes para gerenciar os filmes.
     * @return              True se a compra foi realizada com sucesso, false caso contrário.
     */
    /**
    public boolean realizarCompraFilme(Filme filme, CatalogoFilmes catalogo) {
        if (!catalogo.verificarFilmeExistente(filme)) {
            catalogo.adicionarFilme(filme);
            catalogo.saveFilmes(); // Salva o catálogo atualizado
            System.out.println("Filme adquirido e adicionado ao catálogo com sucesso!");
            return true;
        } else {
            System.out.println("Erro: Filme já existe no catálogo.");
            return false;
        }
    }


    /**
     * Apaga um comentário do sistema.
     *
     * @param comentario        Instância do Comentarios a ser apagado.
     * @param listaComentarios  Instância da ListaComentarios para gerenciar os comentários.
     * @return                   True se o comentário foi apagado com sucesso, false caso contrário.
     */
/*    public boolean apagarComentario(Comentarios comentario, ListaComentarios listaComentarios) {
        if (listaComentarios.removerComentario(comentario)) {
            listaComentarios.saveComentarios(); // Salva a lista atualizada de comentários
            System.out.println("Comentário apagado com sucesso!");
            return true;
        } else {
            System.out.println("Erro: Comentário não encontrado.");
            return false;
        }
    }*/

    /**
     * Adiciona um novo filme ao catálogo.
     *
     * @param filme         Instância do filme a ser adicionado.
     * @param catalogo      Instância do CatalogoFilmes para gerenciar os filmes.
     * @return              True se o filme foi adicionado com sucesso, false caso contrário.
     */
/*    public boolean adicionarFilme(Filme filme, CatalogoFilmes catalogo) {
        if (!catalogo.verificarFilmeExistente(filme)) {
            catalogo.adicionarFilme(filme);
            catalogo.saveFilmes(); // Salva o catálogo atualizado
            System.out.println("Filme adicionado ao catálogo com sucesso!");
            return true;
        } else {
            System.out.println("Erro: Filme já existe no catálogo.");
            return false;
        }
    }*/
}
