package com.senai.model;

public class Professor extends Usuario{
    int idProfessor;
    String unidadeCurricular;

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public String getUnidadeCurricular() {
        return unidadeCurricular;
    }

    public void setUnidadeCurricular(String unidadeCurricular) {
        unidadeCurricular = unidadeCurricular;
    }



    public Professor(String nome, String login, String senha, int idProfessor, String unidadeCurricular) {
        super(nome, login, senha);
        this.idProfessor = idProfessor;
        this.unidadeCurricular = unidadeCurricular;

    }

    @Override
    public String toString() {
        return "Professor{" +
                "ID=" + idProfessor +
                ", UC= " + getUnidadeCurricular() +
                ", Nome='" + getNome() +  '\'' +
                ", Login='" + getLogin() + '\'' +
                ", Senha='" + getSenha() + '\'' +
                '}';
    }
}
