package com.senai.model;

public class Aluno extends Usuario{
    int idAluno;

    public Aluno(String nome, String login, String senha) {
        super(nome, login, senha);
        this.idAluno= idAluno;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

}
