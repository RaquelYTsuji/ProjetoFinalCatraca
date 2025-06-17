package com.senai.model;
import java.time.LocalDateTime;

public class Ocorrencia {
    private int id;
    private int idAluno;
    private String tipo;
    private String descricao;
    private LocalDateTime dataHora;
    private boolean cancelar;
    private String status;

    public Ocorrencia(int id, int idAluno, String tipo, String descricao, LocalDateTime dataHora) {
        this.id = id;
        this.idAluno = idAluno;
        this.tipo = tipo;
        this.descricao = descricao;
        this.dataHora = dataHora;
    }

    public Ocorrencia() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}