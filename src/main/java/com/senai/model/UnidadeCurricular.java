package com.senai.model;

import java.util.List;

//Aqui segue o mesmo padrão do POO, onde criamos os atributos e chamos construtores, getter e setters e fazemos o toString
public class UnidadeCurricular {

    private int id;
    private String nome;
    private String disciplina;
    private List<Professor> idProfessor;
    private String cargaHoraria;
    private String metodoAvaliacao;

    public UnidadeCurricular(int id, String nome, String disciplina, List<Professor> idProfessor, String cargaHoraria, String metodoAvaliacao) {
        this.id = id;
        this.nome = nome;
        this.disciplina = disciplina;
        this.idProfessor = idProfessor;
        this.cargaHoraria = cargaHoraria;
        this.metodoAvaliacao = metodoAvaliacao;
    }

    public UnidadeCurricular() {

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

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public List<Professor> idProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(List<Professor> idProfessor) {
        this.idProfessor = idProfessor;;
    }
    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getMetodoAvaliacao() {
        return metodoAvaliacao;
    }

    public List<Professor> getIdProfessor() {
        return idProfessor;
    }

    public void setMetodoAvaliacao(String metodoAvaliacao) {
        this.metodoAvaliacao = metodoAvaliacao;
    }

    @Override
    public String toString() {
        return String.format("""
            id da UC:             %d
            Nome da UC:           %s
            Disciplina:           %s
            Id Professor:%s
            Carga Horária:        %s
            Método de Avaliação:  %s
            """, id, nome, disciplina, idProfessor, cargaHoraria, metodoAvaliacao);
    }
}
