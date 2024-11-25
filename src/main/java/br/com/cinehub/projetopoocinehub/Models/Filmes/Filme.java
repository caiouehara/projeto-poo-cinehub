package br.com.cinehub.projetopoocinehub.Models.Filmes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Filme {
    @JsonProperty("id")
    private String id;

    @JsonProperty("anoFilme")
    private int anoFilme;

    @JsonProperty("tituloFilme")
    private String tituloFilme;

    @JsonProperty("sinopseFilme")
    private String sinopseFilme;

    @JsonProperty("avaliacaoFilme")
    private double avaliacaoFilme;

    @JsonProperty("duracaoFilme")
    private double duracaoFilme;

    @JsonProperty("precoFilmeCompra")
    private double precoFilmeCompra;

    @JsonProperty("precoFilmeAluguel")
    private double precoFilmeAluguel;

    @JsonProperty("diasAluguel")
    private int diasAluguel;

    @JsonProperty("qtdUsuariosAvaliaram")
    private int qtdUsuariosAvaliaram;

    @JsonProperty("imagem")
    private String imagem;

    @JsonProperty("comentarios")
    private List<Comentario> comentarios;

    @JsonProperty("avaliacoes")
    private List<Avaliacao> avaliacoes;

    // Construtor padrão necessário para a desserialização
    public Filme() {
        this.id = UUID.randomUUID().toString();
        this.comentarios = new ArrayList<>();
        this.avaliacoes = new ArrayList<>();
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    // Se você precisa definir o ID a partir do JSON, inclua o setter
    public void setId(String id) {
        this.id = id;
    }

    public int getAnoFilme() {
        return anoFilme;
    }

    public void setAnoFilme(int anoFilme) {
        this.anoFilme = anoFilme;
    }

    public String getTituloFilme() {
        return tituloFilme;
    }

    public void setTituloFilme(String tituloFilme) {
        this.tituloFilme = tituloFilme;
    }

    public String getSinopseFilme() {
        return sinopseFilme;
    }

    public void setSinopseFilme(String sinopseFilme) {
        this.sinopseFilme = sinopseFilme;
    }

    public double getAvaliacaoFilme() {
        return avaliacaoFilme;
    }

    public void setAvaliacaoFilme(double avaliacaoFilme) {
        this.avaliacaoFilme = avaliacaoFilme;
    }

    public double getDuracaoFilme() {
        return duracaoFilme;
    }

    public void setDuracaoFilme(double duracaoFilme) {
        this.duracaoFilme = duracaoFilme;
    }

    public double getPrecoFilmeCompra() {
        return precoFilmeCompra;
    }

    public void setPrecoFilmeCompra(double precoFilmeCompra) {
        this.precoFilmeCompra = precoFilmeCompra;
    }

    public double getPrecoFilmeAluguel() {
        return precoFilmeAluguel;
    }

    public void setPrecoFilmeAluguel(double precoFilmeAluguel) {
        this.precoFilmeAluguel = precoFilmeAluguel;
    }

    public int getDiasAluguel() {
        return diasAluguel;
    }

    public void setDiasAluguel(int diasAluguel) {
        this.diasAluguel = diasAluguel;
    }

    public int getQtdUsuariosAvaliaram() {
        return qtdUsuariosAvaliaram;
    }

    public void setQtdUsuariosAvaliaram(int qtdUsuariosAvaliaram) {
        this.qtdUsuariosAvaliaram = qtdUsuariosAvaliaram;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
        atualizarAvaliacaoMedia();
    }

    // Métodos adicionais
    public void adicionarComentario(Comentario comentario) {
        this.comentarios.add(comentario);
    }

    public void adicionarAvaliacao(Avaliacao avaliacao) {
        this.avaliacoes.add(avaliacao);
        atualizarAvaliacaoMedia();
    }

    private void atualizarAvaliacaoMedia() {
        double somaNotas = 0.0;
        for (Avaliacao avaliacao : avaliacoes) {
            somaNotas += avaliacao.getNota();
        }
        this.qtdUsuariosAvaliaram = avaliacoes.size();
        this.avaliacaoFilme = (qtdUsuariosAvaliaram > 0) ? (somaNotas / qtdUsuariosAvaliaram) : 0.0;
    }

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
