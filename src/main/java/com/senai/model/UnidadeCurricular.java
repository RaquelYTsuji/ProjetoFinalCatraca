package com.senai.model;

public class UnidadeCurricular {
    private String nome;
    private String disciplina;
    private String professorResponsavel;
    private String cargaHoraria;
    private String metodoAvaliacao;

    public UnidadeCurricular(String nome, String disciplina, String professorResponsavel, String cargaHoraria, String metodoAvaliacao) {
        this.nome = nome;
        this.disciplina = disciplina;
        this.professorResponsavel = professorResponsavel;
        this.cargaHoraria = cargaHoraria;
        this.metodoAvaliacao = metodoAvaliacao;
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

    public String getProfessorResponsavel() {
        return professorResponsavel;
    }

    public void setProfessorResponsavel(String professorResponsavel) {
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
        return String.format(
                """
                Nome da UC:           %s
                Disciplina:           %s
                Professor Responsável:%s
                Carga Horária:        %s
                Método de Avaliação:  %s
                """,
                nome, disciplina, professorResponsavel, cargaHoraria, metodoAvaliacao
        );
    }

}
