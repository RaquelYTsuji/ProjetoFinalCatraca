package com.senai.model;

public class Aluno extends Usuario{
    int idAluno;
    String ocorrencias;
    String justificativas;

    public Aluno(String nome, String login, String senha, int idAluno, String ocorrencias, String justificativas) {
        super(nome, login, senha);
        this.idAluno = idAluno;
        this.ocorrencias = ocorrencias;
        this.justificativas = justificativas;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public String getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(String ocorrencias) {
        this.ocorrencias = ocorrencias;
    }

    public String getJustificativas() {
        return justificativas;
    }

    public void setJustificativas(String justificativas) {
        this.justificativas = justificativas;
    }
}
