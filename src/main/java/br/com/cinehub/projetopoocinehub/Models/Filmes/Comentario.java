/**
 * Representa um comentário associado a um filme.
 * Contém informações como identificador, nome do autor, ID do filme, texto do comentário e a data de criação.
 */
package br.com.cinehub.projetopoocinehub.Models.Filmes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public class Comentario {
    /**
     * Identificador único do comentário.
     * Esse campo é gerado automaticamente com um UUID único.
     */
    @JsonProperty("id")
    private String id;

     /**
     * Nome do autor do comentário.
     * Este campo armazena o nome da pessoa que fez o comentário.
     */
    @JsonProperty("name")
    private String name;

    /**
     * Identificador do filme associado ao comentário.
     * Este campo é usado para vincular o comentário a um filme específico.
     */
    @JsonProperty("filmeId")
    private String filmeId;

    /**
     * Texto do comentário.
     * Este campo armazena o conteúdo do comentário deixado pelo autor.
     */
    @JsonProperty("texto")
    private String texto;

     /**
     * Data de criação do comentário.
     * Este campo armazena a data e hora em que o comentário foi criado.
     */
    @JsonProperty("data")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime data;

     /**
     * Construtor padrão necessário para desserialização.
     */
    // Construtor padrão para desserialização
    public Comentario() {
    }

    /**
     * Construtor que inicializa os campos principais do comentário.
     *
     * @param name    Nome do autor do comentário.
     * @param filmeId ID do filme associado ao comentário.
     * @param texto   Texto do comentário.
     */
    public Comentario(String name, String filmeId, String texto) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.filmeId = filmeId;
        this.texto = texto;
        this.data = LocalDateTime.now();
    }

    // Getters e Setters

    /**
     * Obtém o identificador único do comentário.
     *
     * @return O identificador único do comentário.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtém o nome do autor do comentário.
     *
     * @return O nome do autor.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do autor do comentário.
     *
     * @param name O nome do autor.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtém o identificador do filme associado ao comentário.
     *
     * @return O identificador do filme.
     */
    public String getFilmeId() {
        return filmeId;
    }

    /**
     * Define o identificador do filme associado ao comentário.
     *
     * @param filmeId O identificador do filme.
     */
    public void setFilmeId(String filmeId) {
        this.filmeId = filmeId;
    }


    /**
     * Obtém o texto do comentário.
     *
     * @return O texto do comentário.
     */
    public String getTexto() {
        return texto;
    }

    /**
     * Define o texto do comentário.
     *
     * @param texto O texto do comentário.
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * Obtém a data de criação do comentário.
     *
     * @return A data de criação do comentário.
     */
    public LocalDateTime getData() {
        return data;
    }

    /**
     * Define a data de criação do comentário.
     *
     * @param data A data de criação a ser definida.
     */
    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
