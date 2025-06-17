package com.senai.controller;

import com.senai.model.Professor;
import com.senai.model.UnidadeCurricular;
import com.senai.model.dao.json.UnidadeCurricularDAO;

import java.util.ArrayList;
import java.util.List;

public class UnidadeCurricularController {
    private List<UnidadeCurricular> listaUC;
    private final UnidadeCurricularDAO UCdao;

    public UnidadeCurricularController() {
        UCdao = new UnidadeCurricularDAO();
        listaUC = UCdao.carregarUC();
    }


    public void cadastrarUC(int id, String nome, String disciplina, List<Professor> professor, String cargaHoraria) {
        UnidadeCurricular novaUC = new UnidadeCurricular(id, nome, disciplina, professor, cargaHoraria);
        listaUC.add(novaUC);
        UCdao.salvarUC(listaUC);
        System.out.println("Unidade Curricular cadastrada com sucesso!");
    }


    public List<UnidadeCurricular> listarUC() {
        // Carrega novamente as unidades do arquivo, garantindo que a lista está atualizada.
        return new ArrayList<>(UCdao.carregarUC());
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


    public boolean atualizarProfessor(String nomeUC, List<Professor> novoProfessor) {
        for (UnidadeCurricular uc : listaUC) {
            if (uc.getNome().equalsIgnoreCase(nomeUC)) {
                uc.setIdProfessor(novoProfessor);
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



    public UnidadeCurricular procurarUCPorId(int id) {
        for (UnidadeCurricular uc : listaUC) {
            if (uc.getId() == id) {
                return uc; // Retorna a Unidade Curricular se encontrar pelo ID
            }
        }
        return null; // Retorna null se não encontrar
    }
}
