package com.senai.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Ocorrencia {
    private int id;
    private String tipo;
    private String descricao;
    private LocalDateTime dataHora;
    private boolean cancelar;
    private boolean status;

    public Ocorrencia( int id, String tipo, String descricao, boolean cancelar, boolean status) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
        this.dataHora = LocalDateTime.now();
        this.cancelar = cancelar;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public boolean isCancelar() {
        return cancelar;
    }

    public void setCancelar(boolean cancelar) {
        this.cancelar = cancelar;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String










    toString() {
        return "Ocorrencia{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataHora=" + dataHora +
                ", cancelar=" + cancelar +
                ", status=" + status +
                '}';
    }
}

