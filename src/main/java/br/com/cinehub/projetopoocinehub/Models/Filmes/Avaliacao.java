package br.com.cinehub.projetopoocinehub.Models.Filmes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public class Avaliacao {
    @JsonProperty("id")
    private String id;

    @JsonProperty("usuarioId")
    private String usuarioId;

    @JsonProperty("filmeId")
    private String filmeId;

    @JsonProperty("nota")
    private double nota;

    @JsonProperty("data")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime data;

    // Construtor padrão para desserialização
    public Avaliacao() {
    }

    public Avaliacao(String usuarioId, String filmeId, double nota) {
        this.id = UUID.randomUUID().toString();
        this.usuarioId = usuarioId;
        this.filmeId = filmeId;
        this.nota = nota;
        this.data = LocalDateTime.now();
    }

    // Getters e Setters

    public String getId() {
        return id;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getFilmeId() {
        return filmeId;
    }

    public void setFilmeId(String filmeId) {
        this.filmeId = filmeId;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
