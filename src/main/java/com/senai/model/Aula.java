package com.senai.model;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

public class Aula {
    private int id;
    private String aulaCurricular;
    private List<Professor> professores;
    private List<UnidadeCurricular> unidadeCurriculares;
    private LocalTime unidadeHorario;


    public Aula(int id, String aulaCurricular, List<Professor> professores, List<UnidadeCurricular> unidadeCurriculares, LocalTime unidadeHorario) {
        this.id = id;
        this.aulaCurricular = aulaCurricular;
        this.professores = professores;
        this.unidadeCurriculares = unidadeCurriculares;
        this.unidadeHorario = unidadeHorario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAulaCurricular() {
        return aulaCurricular;
    }

    public void setAulaCurricular(String aulaCurricular) {
        this.aulaCurricular = aulaCurricular;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    public List<UnidadeCurricular> getUnidadeCurriculares() {
        return unidadeCurriculares;
    }

    public void setUnidadeCurriculares(List<UnidadeCurricular> unidadeCurriculares) {
        this.unidadeCurriculares = unidadeCurriculares;
    }

    public LocalTime getUnidadeHorario() {
        return unidadeHorario;
    }

    public void setUnidadeHorario(LocalTime unidadeHorario) {
        this.unidadeHorario = unidadeHorario;
    }

    @Override
    public String toString() {
        return "Aula{" +
                "id=" + id +
                ", aulaCurricular='" + aulaCurricular + '\'' +
                ", professores=" + professores +
                ", unidadeCurriculares=" + unidadeCurriculares +
                ", unidadeHorario=" + unidadeHorario +
                '}';
    }
}
