package com.senai.model;

import java.util.List;

public class SubTurma {
    private int id;
    private String nome;
    //private Horario horario;
    private List<Aluno> alunos;

    public SubTurma() {
    }

    public SubTurma(int id, String nome, List<Aluno> alunos) {
        this.id = id;
        this.nome = nome;
        this.alunos = alunos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public String toString() {
        return "SubTurma{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", alunos=" + alunos +
                '}';
    }
}
