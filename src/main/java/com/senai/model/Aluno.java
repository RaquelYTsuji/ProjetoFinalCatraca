package com.senai.model;

public class Aluno extends Usuario {
    int idAluno;

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

    @Override
    public String toString() {
        return "Aluno{" +
                "ID=" + idAluno +
                ", Nome='" + getNome() +  '\'' +
                ", Login='" + getLogin() + '\'' +
                ", Senha='" + getSenha() + '\'' +
                '}';
    }

    @Override
    public String getTipo() {
        return "Aluno";
    }
}
