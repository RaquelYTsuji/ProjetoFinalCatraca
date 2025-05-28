package com.senai.controller;

import com.senai.model.UnidadeCurricular;
import com.senai.model.UnidadeCurricularDAO;

import java.util.ArrayList;
import java.util.List;

public class UnidadeCurricularController {
    //Final, já que não haverá alterações sobre essas variaveis.
    private List<UnidadeCurricular> listaUC;
    private final UnidadeCurricularDAO UCdao;

    public UnidadeCurricularController() {
        UCdao = new UnidadeCurricularDAO();
        listaUC = UCdao.carregarUC();
    }

    public void cadastrarUC(int id, String nome, String disciplina, String professor, String cargaHoraria, String metodoAvaliacao) {
        UnidadeCurricular novaUC = new UnidadeCurricular(id, nome, disciplina, professor, cargaHoraria, metodoAvaliacao);
        listaUC.add(novaUC);
        UCdao.salvarUC(listaUC);
        System.out.println("Unidade Curricular cadastrada com sucesso!");
    }

    public List<UnidadeCurricular> listarUC() {
        return new ArrayList<>(listaUC);
    }

    public boolean atualizarNomeUC(String nomeAntigo, String novoNome) {
        for (UnidadeCurricular uc : listaUC) {
            if (uc.getNome().equalsIgnoreCase(nomeAntigo)) {
                uc.setNome(novoNome);
                UCdao.salvarUC(listaUC);
                return true;
            }
        }
        return false;
    }

    public boolean atualizarDisciplina(String nomeUC, String novaDisciplina) {
        for (UnidadeCurricular uc : listaUC) {
            if (uc.getNome().equalsIgnoreCase(nomeUC)) {
                uc.setDisciplina(novaDisciplina);
                UCdao.salvarUC(listaUC);
                return true;
            }
        }
        return false;
    }

    public boolean atualizarProfessor(String nomeUC, String novoProfessor) {
        for (UnidadeCurricular uc : listaUC) {
            if (uc.getNome().equalsIgnoreCase(nomeUC)) {
                uc.setProfessorResponsavel(novoProfessor);
                UCdao.salvarUC(listaUC);
                return true;
            }
        }
        return false;
    }

    public boolean atualizarMetodo(String nomeUC, String novoMetodo) {
        for (UnidadeCurricular uc : listaUC) {
            if (uc.getNome().equalsIgnoreCase(nomeUC)) {
                uc.setMetodoAvaliacao(novoMetodo);
                UCdao.salvarUC(listaUC);
                return true;
            }
        }
        return false;
    }

    public boolean removerUC(String nome, String confirmacao) {
        if (!confirmacao.equalsIgnoreCase("s")) return false;
        boolean removido = listaUC.removeIf(uc -> uc.getNome().equalsIgnoreCase(nome));
        if (removido) UCdao.salvarUC(listaUC);
        return removido;
    }
}
