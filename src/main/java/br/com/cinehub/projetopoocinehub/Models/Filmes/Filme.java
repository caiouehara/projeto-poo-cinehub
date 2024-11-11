package br.com.cinehub.projetopoocinehub.Models.Filmes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * A classe {@code Filme} representa um filme com diversas propriedades como título, ano, sinopse, etc.
 * <p>
 * Esta classe utiliza as anotações do Jackson para permitir a serialização e desserialização de objetos
 * para JSON. O construtor é anotado com {@code @JsonCreator}, e cada parâmetro é anotado com
 * {@code @JsonProperty} para mapear as propriedades do JSON para os campos correspondentes da classe.
 * </p>
 */
public class Filme {
    private String id;
    private int anoFilme;
    private String tituloFilme;
    private String sinopseFilme;
    private double avaliacaoFilme;
    private double duracaoFilme;
    private double precoFilmeCompra;
    private double precoFilmeAluguel;
    private int diasAluguel;
    private int usuariosAvaliaram;
    private String imagem;

    /**
     * Construtor da classe {@code Filme} utilizado pelo Jackson para desserialização do JSON.
     *
     * @param id                o ID único do filme
     * @param anoFilme          o ano de lançamento do filme
     * @param tituloFilme       o título do filme
     * @param sinopseFilme      a sinopse do filme
     * @param avaliacaoFilme    a avaliação do filme
     * @param duracaoFilme      a duração do filme em minutos
     * @param precoFilmeCompra  o preço para compra do filme
     * @param precoFilmeAluguel o preço para aluguel do filme
     * @param diasAluguel       a quantidade de dias para aluguel
     * @param usuariosAvaliaram o número de usuários que avaliaram o filme
     * @param imagem            o caminho ou URL da imagem do filme
     */
    @JsonCreator
    public Filme(
        @JsonProperty("id") String id,
        @JsonProperty("anoFilme") int anoFilme,
        @JsonProperty("tituloFilme") String tituloFilme,
        @JsonProperty("sinopseFilme") String sinopseFilme,
        @JsonProperty("avaliacaoFilme") double avaliacaoFilme,
        @JsonProperty("duracaoFilme") double duracaoFilme,
        @JsonProperty("precoFilmeCompra") double precoFilmeCompra,
        @JsonProperty("precoFilmeAluguel") double precoFilmeAluguel,
        @JsonProperty("diasAluguel") int diasAluguel,
        @JsonProperty("usuariosAvaliaram") int usuariosAvaliaram,
        @JsonProperty("imagem") String imagem
    ) {
        // Se o ID for nulo ou vazio, gerar um novo UUID
        if (id == null || id.isEmpty()) {
            this.id = UUID.randomUUID().toString();
        } else {
            this.id = id;
        }
        this.anoFilme = anoFilme;
        this.tituloFilme = tituloFilme;
        this.sinopseFilme = sinopseFilme;
        this.avaliacaoFilme = avaliacaoFilme;
        this.duracaoFilme = duracaoFilme;
        this.precoFilmeCompra = precoFilmeCompra;
        this.precoFilmeAluguel = precoFilmeAluguel;
        this.diasAluguel = diasAluguel;
        this.usuariosAvaliaram = usuariosAvaliaram;
        this.imagem = imagem;
    }

    // Construtor sem parâmetros para criação de novos filmes programaticamente
    public Filme() {

    }

    // Getters e Setters

    /**
     * Obtém o ID único do filme.
     *
     * @return o ID do filme
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * Define o ID único do filme.
     *
     * @param id o ID do filme
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("anoFilme")
    public int getAnoFilme() {
        return anoFilme;
    }

    @JsonProperty("anoFilme")
    public void setAnoFilme(int anoFilme) {
        this.anoFilme = anoFilme;
    }

    @JsonProperty("tituloFilme")
    public String getTituloFilme() {
        return tituloFilme;
    }

    @JsonProperty("imagem")
    public String getImagem() {
        return this.imagem;
    }

    @JsonProperty("sinopseFilme")
    public String getSinopseFilme() {
        return this.sinopseFilme;
    }

    @JsonProperty("avaliacaoFilme")
    public double getAvaliacaoFilme() {
        return this.avaliacaoFilme;
    }

    @JsonProperty("tituloFilme")
    public void setTituloFilme(String tituloFilme) {
        this.tituloFilme = tituloFilme;
    }

    public void setAvaliacaoFilme(double nota) {
        this.avaliacaoFilme = nota;
    }

    public double getPrecoFilmeCompra() {
        return precoFilmeCompra;
    }

    public double getPrecoFilmeAluguel() {
        return precoFilmeAluguel;
    }
    
    public void setSinopseFilme(String sinopse) {
    }

    public void setDuracaoFilme(double duracao) {
    }

    public void setPrecoFilmeCompra(double precoCompra) {
    }

    public void setPrecoFilmeAluguel(double precoAluguel) {
    }

    public void setDiasAluguel(int diasAluguel) {
    }

    public void setImagem(String imagemFileName) {
    }
}
