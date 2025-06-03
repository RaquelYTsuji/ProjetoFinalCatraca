package com.senai.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Turma {
    private int id;
    private String nome;
    private Curso curso;
    private List<SubTurma> subTurmas;
    private LocalDate dataInicio;
    private int quantidadeSemestre;
    private LocalTime horarioEntrada;

    public Turma(int id, String nome, Curso curso, List<SubTurma> subTurmas, LocalDate dataInicio, int quantidadeSemestre, LocalTime horarioEntrada) {
        this.id = id;
        this.nome = nome;
        this.curso = curso;
        this.subTurmas = subTurmas;
        this.dataInicio = dataInicio;
        this.quantidadeSemestre = quantidadeSemestre;
        this.horarioEntrada = horarioEntrada;
    }

    public Turma() {
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

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<SubTurma> getSubTurmas() {
        return subTurmas;
    }

    public void setSubTurmas(List<SubTurma> subTurmas) {
        this.subTurmas = subTurmas;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public int getQuantidadeSemestre() {
        return quantidadeSemestre;
    }

    public void setQuantidadeSemestre(int quantidadeSemestre) {
        this.quantidadeSemestre = quantidadeSemestre;
    }

    public LocalTime getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(LocalTime horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }

    @Override
    public String toString() {
        return "Turma{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", curso=" + curso +
                ", subTurmas=" + subTurmas +
                ", dataInicio=" + dataInicio +
                ", quantidadeSemestre=" + quantidadeSemestre +
                ", horarioEntrada=" + horarioEntrada +
                '}';
    }
}
