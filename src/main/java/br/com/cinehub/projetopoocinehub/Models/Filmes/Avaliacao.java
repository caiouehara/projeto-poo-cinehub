/**
 * Representa uma avaliação feita por um usuário para um filme.
 * Contém informações como o identificador da avaliação, nome do autor, ID do filme, nota atribuída e a data da avaliação.
 */
package br.com.cinehub.projetopoocinehub.Models.Filmes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public class Avaliacao {
    /**
     * Identificador único da avaliação.
     * Este campo é gerado automaticamente com um UUID único.
     */
    @JsonProperty("id")
    private String id;

    /**
     * Nome do autor da avaliação.
     * Este campo armazena o nome da pessoa que fez a avaliação.
     */
    @JsonProperty("name")
    private String name;

    /**
     * Identificador do filme associado à avaliação.
     * Este campo é utilizado para vincular a avaliação a um filme específico.
     */
    @JsonProperty("filmeId")
    private String filmeId;

    /**
     * Nota atribuída ao filme pelo avaliador.
     * Este campo armazena a nota dada pelo autor da avaliação.
     */
    @JsonProperty("nota")
    private double nota;

    /**
     * Data e hora em que a avaliação foi realizada.
     * Este campo armazena a data e a hora da criação da avaliação.
     */
    @JsonProperty("data")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime data;

    /**
     * Construtor padrão necessário para desserialização.
     * Este construtor é utilizado quando o objeto é convertido de/para JSON.
     */
    public Avaliacao() {
    }
    
    /**
     * Construtor que inicializa os campos principais da avaliação.
     * 
     * @param name    Nome do autor da avaliação.
     * @param filmeId ID do filme associado à avaliação.
     * @param nota    Nota atribuída ao filme.
     */
    public Avaliacao(String name, String filmeId, double nota) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.filmeId = filmeId;
        this.nota = nota;
        this.data = LocalDateTime.now();
    }

    // Getters e Setters
    /**
     * Obtém o identificador único da avaliação.
     * 
     * @return O identificador único da avaliação.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtém o nome do autor da avaliação.
     * 
     * @return O nome do autor.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do autor da avaliação.
     * 
     * @param name O nome do autor a ser definido.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtém o identificador do filme associado à avaliação.
     * 
     * @return O identificador do filme.
     */
    public String getFilmeId() {
        return filmeId;
    }

    /**
     * Define o identificador do filme associado à avaliação.
     * 
     * @param filmeId O identificador do filme a ser definido.
     */
    public void setFilmeId(String filmeId) {
        this.filmeId = filmeId;
    }

    /**
     * Obtém a nota atribuída ao filme na avaliação.
     * 
     * @return A nota atribuída ao filme.
     */
    public double getNota() {
        return nota;
    }

    /**
     * Define a nota atribuída ao filme na avaliação.
     * 
     * @param nota A nota a ser atribuída.
     */
    public void setNota(double nota) {
        this.nota = nota;
    }

    /**
     * Obtém a data e hora da avaliação.
     * 
     * @return A data e hora em que a avaliação foi realizada.
     */
    public LocalDateTime getData() {
        return data;
    }

    /**
     * Define a data e hora da avaliação.
     * 
     * @param data A data e hora a serem definidas para a avaliação.
     */
    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
