package com.senai.model;

public class Aluno extends Usuario {
    String idAcesso;

    public Aluno(String nome, String login, String senha, String idAluno) {
        super(nome, login, senha);
        this.idAcesso = idAluno;
    }

    public String getIdAcesso() {
        return idAcesso;
    }

    public void setIdAcesso(String idAcesso) {
        this.idAcesso = idAcesso;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "ID=" + idAcesso +
                ", Nome='" + getNome() +  '\'' +
                ", Login='" + getLogin() + '\'' +
                ", Senha='" + getSenha() + '\'' +
                '}';
    }
}
