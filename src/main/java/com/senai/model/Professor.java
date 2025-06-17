package com.senai.model;

public class Professor extends Usuario{
    String unidadeCurricular;

    public String getUnidadeCurricular() {
        return unidadeCurricular;
    }

    public void setUnidadeCurricular(String unidadeCurricular) {
        unidadeCurricular = unidadeCurricular;
    }



    public Professor(String nome, String login, String senha, int idProfessor, String unidadeCurricular) {
        super(idProfessor, nome, login, senha);
        this.unidadeCurricular = unidadeCurricular;

    }

    @Override
    public String toString() {
        return "Professor{" +
                "ID=" + getId() +
                ", UC= " + getUnidadeCurricular() +
                ", Nome='" + getNome() +  '\'' +
                ", Login='" + getLogin() + '\'' +
                ", Senha='" + getSenha() + '\'' +
                '}';
    }

    @Override
    public String getTipo() {
        return "Professor";
    }
}
