package br.com.cinehub.projetopoocinehub.Models.Filmes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public class Comentario {
    @JsonProperty("id")
    private String id;

    @JsonProperty("usuarioId")
    private String usuarioId;

    @JsonProperty("filmeId")
    private String filmeId;

    @JsonProperty("texto")
    private String texto;

    @JsonProperty("data")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime data;

    // Construtor padrão para desserialização
    public Comentario() {
    }

    public Comentario(String usuarioId, String filmeId, String texto) {
        this.id = UUID.randomUUID().toString();
        this.usuarioId = usuarioId;
        this.filmeId = filmeId;
        this.texto = texto;
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

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
