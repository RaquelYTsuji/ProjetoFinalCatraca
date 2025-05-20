package com.senai.model;

public class Aluno extends Usuario{
    int idAluno;
    String ocorrencias;
    String justificativas;

    public Aluno(String nome, String login, String senha, int idAluno) {
        super(nome, login, senha);
        this.idAluno = idAluno;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

}
