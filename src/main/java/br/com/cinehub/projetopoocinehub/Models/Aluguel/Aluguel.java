package br.com.cinehub.projetopoocinehub.Models.Aluguel;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

/**
 * Classe que representa um aluguel de um filme por um cliente.
 */
public class Aluguel {

    /**
     * Identificador único do aluguel.
     */
    @JsonProperty("id")
    private String id;

    /**
     * Identificador do cliente (geralmente o email).
     */
    @JsonProperty("clienteId")
    private String clienteId;

    /**
     * Identificador do filme alugado.
     */
    @JsonProperty("filmeId")
    private String filmeId;

    /**
     * Data em que o aluguel foi efetuado.
     */
    @JsonProperty("dataAluguel")
    private LocalDate dataAluguel;

    /**
     * Data prevista para a devolução do filme.
     */
    @JsonProperty("dataDevolucao")
    private LocalDate dataDevolucao;

    /**
     * Construtor padrão sem parâmetros.
     */
    public Aluguel() {}

    /**
     * Construtor com todos os parâmetros.
     *
     * @param id             Identificador único do aluguel.
     * @param clienteId      Identificador do cliente.
     * @param filmeId        Identificador do filme alugado.
     * @param dataAluguel    Data em que o aluguel foi efetuado.
     * @param dataDevolucao  Data prevista para a devolução do filme.
     */
    public Aluguel(String id, String clienteId, String filmeId, LocalDate dataAluguel, LocalDate dataDevolucao) {
        this.id = id;
        this.clienteId = clienteId;
        this.filmeId = filmeId;
        this.dataAluguel = dataAluguel;
        this.dataDevolucao = dataDevolucao;
    }

    /**
     * Obtém o identificador único do aluguel.
     *
     * @return O ID do aluguel.
     */
    public String getId() {
        return id;
    }

    /**
     * Define o identificador único do aluguel.
     *
     * @param id O novo ID do aluguel.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtém o identificador do cliente.
     *
     * @return O ID do cliente.
     */
    public String getClienteId() {
        return clienteId;
    }

    /**
     * Define o identificador do cliente.
     *
     * @param clienteId O novo ID do cliente.
     */
    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * Obtém o identificador do filme alugado.
     *
     * @return O ID do filme.
     */
    public String getFilmeId() {
        return filmeId;
    }

    /**
     * Define o identificador do filme alugado.
     *
     * @param filmeId O novo ID do filme.
     */
    public void setFilmeId(String filmeId) {
        this.filmeId = filmeId;
    }

    /**
     * Obtém a data em que o aluguel foi efetuado.
     *
     * @return A data do aluguel.
     */
    public LocalDate getDataAluguel() {
        return dataAluguel;
    }

    /**
     * Define a data em que o aluguel foi efetuado.
     *
     * @param dataAluguel A nova data do aluguel.
     */
    public void setDataAluguel(LocalDate dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    /**
     * Obtém a data prevista para a devolução do filme.
     *
     * @return A data de devolução.
     */
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    /**
     * Define a data prevista para a devolução do filme.
     *
     * @param dataDevolucao A nova data de devolução.
     */
    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
