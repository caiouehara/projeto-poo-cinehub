package br.com.cinehub.projetopoocinehub.Models.Aluguel;

import java.time.LocalDate;

public class Aluguel {
    private String id;
    private String clienteId;
    private String filmeId;
    private LocalDate dataAluguel;
    private LocalDate dataDevolucao;

    // Construtores
    public Aluguel() {}

    public Aluguel(String id, String clienteId, String filmeId, LocalDate dataAluguel, LocalDate dataDevolucao) {
        this.id = id;
        this.clienteId = clienteId;
        this.filmeId = filmeId;
        this.dataAluguel = dataAluguel;
        this.dataDevolucao = dataDevolucao;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getFilmeId() {
        return filmeId;
    }

    public void setFilmeId(String filmeId) {
        this.filmeId = filmeId;
    }

    public LocalDate getDataAluguel() {
        return dataAluguel;
    }

    public void setDataAluguel(LocalDate dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
