package br.com.cinehub.projetopoocinehub.Models.Compras;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Classe que representa um aluguel de um filme por um cliente.
 */
public class Compra {

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
     * Data em que a compra foi efetuada
     */
    @JsonProperty("dataCompra")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date dataCompra;


    /**
     * Construtor padrão sem parâmetros.
     */
    public Compra() {}

    /**
     * Construtor com todos os parâmetros.
     *
     * @param id             Identificador único do aluguel.
     * @param clienteId      Identificador do cliente.
     * @param filmeId        Identificador do filme alugado.
     * @param dataCompra    Data em que a compra foi realizada.
     */
    public Compra(String id, String clienteId, String filmeId, Date dataCompra) {
        this.id = id;
        this.clienteId = clienteId;
        this.filmeId = filmeId;
        this.dataCompra = dataCompra;
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
     * Obtém a data em que a compra foi realizada.
     *
     * @return A data da compra.
     */
    public Date getDataCompra() {
        return dataCompra;
    }

    /**
     * Define a data em que o aluguel foi efetuado.
     *
     * @param dataCompra A nova data da compra.
     */
    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }
}
