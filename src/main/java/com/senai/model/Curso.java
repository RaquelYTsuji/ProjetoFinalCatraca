package com.senai.model;

import java.util.List;

public class Curso {
    private int id;
    private String titulo;
    private List<UnidadeCurricular> unidadeCurriculares;
    private int cargaHoraria;
    private boolean tipo;
    private int tolerancia;

    public Curso() {
    }

    public Curso(int id, String titulo, List<UnidadeCurricular> unidadeCurriculares, int cargaHoraria, boolean tipo, int tolerancia) {
        this.id = id;
        this.titulo = titulo;
        this.unidadeCurriculares = unidadeCurriculares;
        this.cargaHoraria = cargaHoraria;
        this.tipo = tipo;
        this.tolerancia = tolerancia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<UnidadeCurricular> getUnidadeCurriculares() {
        return unidadeCurriculares;
    }

    public void setUnidadeCurriculares(List<UnidadeCurricular> unidadeCurriculares) {
        this.unidadeCurriculares = unidadeCurriculares;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public int getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(int tolerancia) {
        this.tolerancia = tolerancia;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id='" + id + '\'' +
                ", titulo=" + titulo +
                ", unidadeCurriculars=" + unidadeCurriculares +
                ", cargaHoraria=" + cargaHoraria +
                ", tipo=" + tipo +
                '}';
    }
}
