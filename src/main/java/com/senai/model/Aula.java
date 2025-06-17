package com.senai.model;

import java.time.LocalTime;
import java.util.List;
import com.senai.controller.ProfessorController;

public class Aula {
    private int id;
    private String aulaCurricular;
    private List<Professor> professores;
    private List<UnidadeCurricular> listaUC;
    private LocalTime unidadeHorario;

    public Aula(int id, String aulaCurricular, List<Professor> professores, List<UnidadeCurricular> listaUC, LocalTime unidadeHorario) {
        this.id = id;
        this.aulaCurricular = aulaCurricular;
        this.professores = professores;
        this.listaUC = listaUC;
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

    public List<UnidadeCurricular> getListaUC() {
        return listaUC;
    }

    public void setListaUC(List<UnidadeCurricular> listaUC) {
        this.listaUC = listaUC;
    }

    public LocalTime getUnidadeHorario() {
        return unidadeHorario;
    }

    public void setUnidadeHorario(LocalTime unidadeHorario) {
        this.unidadeHorario = unidadeHorario;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Aula ID: ").append(id).append("\n");
        sb.append("Aula Curricular: ").append(aulaCurricular).append("\n");
        sb.append("Horário: ").append(unidadeHorario).append("\n");

        sb.append("Professores: [");
        for (int i = 0; i < professores.size(); i++) {
            Professor prof = professores.get(i);
            sb.append("ID: ").append(prof.getId()).append(", Nome: ").append(prof.getNome());
            if (i < professores.size() - 1) sb.append("; ");
        }
        sb.append("]\n");

        sb.append("Unidades Curriculares: [");
        for (int i = 0; i < listaUC.size(); i++) {
            UnidadeCurricular uc = listaUC.get(i);
            sb.append("ID: ").append(uc.getId()).append(", Nome: ").append(uc.getNome());
            if (i < listaUC.size() - 1) sb.append("; ");
        }
        sb.append("]");

        return sb.toString();
    }
}

