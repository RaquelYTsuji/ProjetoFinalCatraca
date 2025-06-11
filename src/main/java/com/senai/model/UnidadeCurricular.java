package com.senai.model;

//Aqui segue o mesmo padrão do POO, onde criamos os atributos e chamos construtores, getter e setters e fazemos o toString
public class UnidadeCurricular {

    private int id;
    private String nome;
    private String disciplina;
    private Professor professorResponsavel;
    private String cargaHoraria;
    private String metodoAvaliacao;

    public UnidadeCurricular(int id, String nome, String disciplina, Professor professorResponsavel, String cargaHoraria, String metodoAvaliacao) {
        this.id = id;
        this.nome = nome;
        this.disciplina = disciplina;
        this.professorResponsavel = professorResponsavel;
        this.cargaHoraria = cargaHoraria;
        this.metodoAvaliacao = metodoAvaliacao;
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

    public Professor getProfessorResponsavel() {
        return professorResponsavel;
    }

    public void setProfessorResponsavel(Professor professorResponsavel) {
        this.professorResponsavel = professorResponsavel;
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

    public void setMetodoAvaliacao(String metodoAvaliacao) {
        this.metodoAvaliacao = metodoAvaliacao;
    }

    @Override
    public String toString() {
        return String.format("""
            id da UC:             %d
            Nome da UC:           %s
            Disciplina:           %s
            Professor Responsável:%s
            Carga Horária:        %s
            Método de Avaliação:  %s
            """, id, nome, disciplina, professorResponsavel, cargaHoraria, metodoAvaliacao);
    }
}
