package com.senai.model;

import java.util.ArrayList;
import java.util.List;

public class UnidadeCurricular {
    private String disciplina;
    private double cargaHoraria;
    private String subTurma;
    private List<UnidadeCurricular> unidades;

    public UnidadeCurricular(String disciplina, double cargaHoraria, String subTurma) {
        this.disciplina = disciplina;
        this.cargaHoraria = cargaHoraria;
        this.subTurma = subTurma;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public double getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(double cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getSubTurma() {
        return subTurma;
    }

    public void setSubTurma(String subTurma) {
        this.subTurma = subTurma;
    }

    public List<UnidadeCurricular> listarUnidades() {
        return unidades;
    }
}
