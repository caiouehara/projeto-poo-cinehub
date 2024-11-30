/**
 * Classe que representa um filme no sistema, incluindo suas propriedades, 
 * como título, ano, sinopse, avaliação, preço e comentários.
 * 
 * <p>Esta classe é serializável/deserializável para JSON utilizando as anotações 
 * da biblioteca Jackson.</p>
 */
package br.com.cinehub.projetopoocinehub.Models.Filmes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Filme {
    /** Identificador único do filme. */
    @JsonProperty("id")
    private String id;

    /** Ano de lançamento do filme. */
    @JsonProperty("anoFilme")
    private int anoFilme;

    /** Título do filme. */
    @JsonProperty("tituloFilme")
    private String tituloFilme;

    /** Sinopse do filme. */
    @JsonProperty("sinopseFilme")
    private String sinopseFilme;

    /** Avaliação média do filme. */
    @JsonProperty("avaliacaoFilme")
    private double avaliacaoFilme;

    /** Duração do filme, em horas. */
    @JsonProperty("duracaoFilme")
    private double duracaoFilme;

    /** Preço para compra do filme. */
    @JsonProperty("precoFilmeCompra")
    private double precoFilmeCompra;

    /** Preço para aluguel do filme. */
    @JsonProperty("precoFilmeAluguel")
    private double precoFilmeAluguel;

    /** Dias permitidos para o aluguel do filme. */
    @JsonProperty("diasAluguel")
    private int diasAluguel;

    /** Quantidade de usuários que avaliaram o filme. */
    @JsonProperty("qtdUsuariosAvaliaram")
    private int qtdUsuariosAvaliaram;

    /** Caminho ou URL para a imagem do filme. */
    @JsonProperty("imagem")
    private String imagem;

    /** Lista de comentários associados ao filme. */
    @JsonProperty("comentarios")
    private List<Comentario> comentarios;

    /** Lista de avaliações feitas pelos usuários. */
    @JsonProperty("avaliacoes")
    private List<Avaliacao> avaliacoes;

     /**
     * Construtor padrão que inicializa os atributos do filme, incluindo
     * um identificador único (UUID), uma lista de comentários e uma lista
     * de avaliações vazias.
     */
    // Construtor padrão necessário para a desserialização
    public Filme() {
        this.id = UUID.randomUUID().toString();
        this.comentarios = new ArrayList<>();
        this.avaliacoes = new ArrayList<>();
    }

    // Getters e Setters
    /**
     * Obtém o identificador do filme.
     * 
     * @return O identificador do filme.
     */
    public String getId() {
        return id;
    }

    /**
     * Define o identificador do filme.
     * 
     * @param id O identificador a ser definido.
     */
    // Se você precisa definir o ID a partir do JSON, inclua o setter
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtém o ano de lançamento do filme.
     *
     * @return O ano de lançamento do filme.
     */
    public int getAnoFilme() {
        return anoFilme;
    }

     /**
     * Define o ano de lançamento do filme.
     *
     * @param anoFilme O ano a ser definido.
     */
    public void setAnoFilme(int anoFilme) {
        this.anoFilme = anoFilme;
    }

    /**
     * Obtém o título do filme.
     *
     * @return O título do filme.
     */
    public String getTituloFilme() {
        return tituloFilme;
    }

    /**
     * Define o título do filme.
     *
     * @param tituloFilme O título a ser definido.
     */
    public void setTituloFilme(String tituloFilme) {
        this.tituloFilme = tituloFilme;
    }

    /**
     * Obtém a sinopse do filme.
     *
     * @return A sinopse do filme.
     */
    public String getSinopseFilme() {
        return sinopseFilme;
    }

     /**
     * Define a sinopse do filme.
     *
     * @param sinopseFilme A sinopse a ser definida.
     */
    public void setSinopseFilme(String sinopseFilme) {
        this.sinopseFilme = sinopseFilme;
    }

    /**
     * Obtém a avaliação média do filme.
     *
     * @return A avaliação média do filme.
     */
    public double getAvaliacaoFilme() {
        return avaliacaoFilme;
    }

    /**
     * Define a avaliação média do filme.
     *
     * @param avaliacaoFilme A avaliação a ser definida.
     */
    public void setAvaliacaoFilme(double avaliacaoFilme) {
        this.avaliacaoFilme = avaliacaoFilme;
    }

    /**
     * Obtém a duração do filme em horas.
     *
     * @return A duração do filme.
     */
    public double getDuracaoFilme() {
        return duracaoFilme;
    }
    
    /**
     * Define a duração do filme.
     *
     * @param duracaoFilme A duração a ser definida.
     */
    public void setDuracaoFilme(double duracaoFilme) {
        this.duracaoFilme = duracaoFilme;
    }

    /**
     * Obtém o preço do filme para compra.
     *
     * @return O preço do filme para compra.
     */
    public double getPrecoFilmeCompra() {
        return precoFilmeCompra;
    }

    /**
     * Define o preço do filme para compra.
     *
     * @param precoFilmeCompra O preço a ser definido.
     */
    public void setPrecoFilmeCompra(double precoFilmeCompra) {
        this.precoFilmeCompra = precoFilmeCompra;
    }

    /**
     * Obtém o preço do filme para aluguel.
     *
     * @return O preço do filme para aluguel.
     */
    public double getPrecoFilmeAluguel() {
        return precoFilmeAluguel;
    }

    /**
     * Define o preço do filme para aluguel.
     *
     * @param precoFilmeAluguel O preço a ser definido.
     */
    public void setPrecoFilmeAluguel(double precoFilmeAluguel) {
        this.precoFilmeAluguel = precoFilmeAluguel;
    }

    /**
     * Obtém o número de dias para aluguel do filme.
     *
     * @return O número de dias para aluguel do filme.
     */
    public int getDiasAluguel() {
        return diasAluguel;
    }

    /**
     * Define o número de dias para aluguel do filme.
     *
     * @param diasAluguel O número de dias a ser definido.
     */
    public void setDiasAluguel(int diasAluguel) {
        this.diasAluguel = diasAluguel;
    }

    /**
     * Obtém a quantidade de usuários que avaliaram o filme.
     *
     * @return A quantidade de usuários que avaliaram o filme.
     */
    public int getQtdUsuariosAvaliaram() {
        return qtdUsuariosAvaliaram;
    }

     /**
     * Define a quantidade de usuários que avaliaram o filme.
     *
     * @param qtdUsuariosAvaliaram A quantidade a ser definida.
     */
    public void setQtdUsuariosAvaliaram(int qtdUsuariosAvaliaram) {
        this.qtdUsuariosAvaliaram = qtdUsuariosAvaliaram;
    }

    /**
     * Obtém o caminho ou URL da imagem do filme.
     *
     * @return O caminho ou URL da imagem.
     */
    public String getImagem() {
        return imagem;
    }

    /**
     * Define o caminho ou URL da imagem do filme.
     *
     * @param imagem O caminho ou URL a ser definido.
     */
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    /**
     * Obtém a lista de comentários associados ao filme.
     *
     * @return A lista de comentários.
     */
    public List<Comentario> getComentarios() {
        return comentarios;
    }

    /**
     * Define a lista de comentários associados ao filme.
     *
     * @param comentarios A lista de comentários a ser definida.
     */
    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * Obtém a lista de avaliações feitas pelos usuários.
     *
     * @return A lista de avaliações.
     */
    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    /**
     * Define a lista de avaliações feitas pelos usuários e atualiza a avaliação média.
     *
     * @param avaliacoes A lista de avaliações a ser definida.
     */
    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
        atualizarAvaliacaoMedia();
    }

    // Métodos adicionais
    /**
     * Adiciona um comentário ao filme.
     * 
     * @param comentario O comentário a ser adicionado.
     */
    public void adicionarComentario(Comentario comentario) {
        this.comentarios.add(comentario);
    }

    /**
     * Adiciona uma avaliação ao filme e atualiza a avaliação média.
     * 
     * @param avaliacao A avaliação a ser adicionada.
     */
    public void adicionarAvaliacao(Avaliacao avaliacao) {
        this.avaliacoes.add(avaliacao);
        atualizarAvaliacaoMedia();
    }

    /**
     * Atualiza a avaliação média do filme com base nas avaliações existentes.
     */
    private void atualizarAvaliacaoMedia() {
        double somaNotas = 0.0;
        for (Avaliacao avaliacao : avaliacoes) {
            somaNotas += avaliacao.getNota();
        }
        this.qtdUsuariosAvaliaram = avaliacoes.size();
        this.avaliacaoFilme = (qtdUsuariosAvaliaram > 0) ? (somaNotas / qtdUsuariosAvaliaram) : 0.0;
    }

    /**
     * Remove um comentário do filme com base no ID do comentário.
     * 
     * @param comentarioId O identificador do comentário a ser removido.
     * @return {@code true} se o comentário foi removido, {@code false} caso contrário.
     */
    public boolean removerComentario(String comentarioId) {
        for (Iterator<Comentario> iterator = comentarios.iterator(); iterator.hasNext(); ) {
            Comentario comentario = iterator.next();
            if (comentario.getId().equals(comentarioId)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
