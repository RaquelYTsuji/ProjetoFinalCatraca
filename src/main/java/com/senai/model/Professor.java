package com.senai.model;

public class Professor extends Usuario{
    int idProfessor;

    public Professor() {

    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }


    public Professor(String nome, String login, String senha, int idProfessor) {
        super(nome, login, senha);
        this.idProfessor = idProfessor;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "ID=" + idProfessor +
                ", Nome='" + getNome() +  '\'' +
                ", Login='" + getLogin() + '\'' +
                ", Senha='" + getSenha() + '\'' +
                '}';
    }
}
