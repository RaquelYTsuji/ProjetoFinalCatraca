package com.senai.model;

import java.time.LocalDateTime;

public class Justificativa {

    private int id;
    private String tipo;
    private String descricao;
    private LocalDateTime dataHoraJustificatida;
    private int quantidadeDias;
    private int prazoDeAceite;
    private String anexo;
    private String status;
    private boolean cancelar;


    public Justificativa(int id, String tipo, String descricao,
                         LocalDateTime now, int quantidadeDias, int prazoDeAceite,
                         String anexo, String status, boolean cancelar) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
        this.dataHoraJustificatida = LocalDateTime.now();
        this.quantidadeDias = quantidadeDias; //quantidade de dias que a justificativa vale
        this.prazoDeAceite = prazoDeAceite; //o maximo de dias que aqv tem que aceitar
        this.anexo = anexo;
        this.status = status;
        this.cancelar = cancelar;
    }

//    public Justificativa(int id, String tipo, String descricao,LocalDateTime dataHoraJustificatida, String status){
//        this.id=id;
//        this.tipo=tipo;
//        this.descricao=descricao;
//        this.dataHoraJustificatida=dataHoraJustificatida;
//        this.status=status;
//    }


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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isCancelar() {
        return cancelar;
    }

    public void setCancelar(boolean cancelar) {
        this.cancelar = cancelar;
    }




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
