package com.senai.model.justificativa;

import java.time.LocalDateTime;

public class Justificativa {

    private int id;
    private String tipo;
    private String descricao;
    private LocalDateTime dataHoraJustificatida;
    private int quantidadeDias;
    private int prazoDeAceite;
    private String anexo;
    private Status status;  // Usar o enum Status em vez de Enum genérico
    private boolean cancelar;

    // Construtor
    public Justificativa(int id, String tipo, String descricao, LocalDateTime dataHoraJustificatida,
                         int quantidadeDias, int prazoDeAceite, String anexo, Status status, boolean cancelar) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
        this.dataHoraJustificatida = dataHoraJustificatida;
        this.quantidadeDias = quantidadeDias;
        this.prazoDeAceite = prazoDeAceite;
        this.anexo = anexo;
        this.status = status;
        this.cancelar = cancelar;
    }

    // Métodos getters e setters
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

    public LocalDateTime getDataHoraJustificatida() {
        return dataHoraJustificatida;
    }

    public void setDataHoraJustificatida(LocalDateTime dataHoraJustificatida) {
        this.dataHoraJustificatida = dataHoraJustificatida;
    }

    public int getQuantidadeDias() {
        return quantidadeDias;
    }

    public void setQuantidadeDias(int quantidadeDias) {
        this.quantidadeDias = quantidadeDias;
    }

    public int getPrazoDeAceite() {
        return prazoDeAceite;
    }

    public void setPrazoDeAceite(int prazoDeAceite) {
        this.prazoDeAceite = prazoDeAceite;
    }

    public String getAnexo() {
        return anexo;
    }

    public void setAnexo(String anexo) {
        this.anexo = anexo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isCancelar() {
        return cancelar;
    }

    public void setCancelar(boolean cancelar) {
        this.cancelar = cancelar;
    }

    // Metodo toString para facilitar a visualização do objeto
    @Override
    public String toString() {
        return "Justificativa{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataHoraJustificatida=" + dataHoraJustificatida +
                ", quantidadeDias=" + quantidadeDias +
                ", prazoDeAceite=" + prazoDeAceite +
                ", anexo='" + anexo + '\'' +
                ", status=" + status +
                ", cancelar=" + cancelar +
                '}';
    }
}
