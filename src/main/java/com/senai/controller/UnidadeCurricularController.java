package com.senai.controller;

import com.senai.model.UnidadeCurricular;
import com.senai.model.UnidadeCurricularDAO;

import java.util.ArrayList;
import java.util.List;

public class UnidadeCurricularController {
    //Final, já que não haverá alterações sobre essas variaveis.
    private List<UnidadeCurricular> listaUC = new ArrayList<>();
    private final UnidadeCurricularDAO UCdao = new UnidadeCurricularDAO(listaUC);

    //O carregar, para que os dados sejam carregados
    public UnidadeCurricularController() {
        listaUC = UCdao.carregarUC();
    }

    public void cadastrarUC(String nome, String disciplina, String professor, String cargaHoraria, String metodoAvaliacao) {
        UnidadeCurricular novaUC = new UnidadeCurricular(nome, disciplina, professor, cargaHoraria, metodoAvaliacao);
        listaUC.add(novaUC); //Aqui a UC que for cadastrada, entrará na lista de UCs
        System.out.println("Unidade Curricular cadastrada com sucesso!");
        UCdao.salvarUC(listaUC);
    }

    //Aqui ele retorna a lista.
    public List<UnidadeCurricular> listarUC() {
        return listaUC;
    }

    //A partir daqui inicia a parte de atualizações, onde faço o uso de um boolean para obter retorno sobre a ação
    public boolean atualizarNomeUC(String nomeAntigo, String novoNome) {
        for (UnidadeCurricular uc : listaUC) {
            if (uc.getNome().equalsIgnoreCase(nomeAntigo)) { //ignoreCase para que a forma em que forem escritas pelo usuario não interfira no rodar do código
                uc.setNome(novoNome);
                UCdao.salvar(listaUC);
                return true;
            }
        }
        return false;
    }

    public boolean atualizarDisciplina(String nomeUC, String novaDisciplina) {
        for (UnidadeCurricular uc : listaUC) {
            if (uc.getNome().equalsIgnoreCase(nomeUC)) {
                uc.setNome(novaDisciplina);
                UCdao.salvar(listaUC);
                return true;
            }
        }
        return false;
    }


    public boolean atualizarProfessor(String nomeUC, String novoProfessor) {
        for (UnidadeCurricular uc : listaUC) {
            if (uc.getNome().equalsIgnoreCase(nomeUC)) {
                uc.setNome(novoProfessor);
                UCdao.salvarlistaUC);
                return true;
            }
        }
        return false;
    }

    public boolean atualizarMetodo(String nomeUC, String novoMetodo) {
        for (UnidadeCurricular uc : listaUC) {
            if (uc.getNome().equalsIgnoreCase(nomeUC)) {
                uc.setNome(novoMetodo);
                UCdao.salvar(listaUC);
                return true;
            }
        }
        return false;
    }

    public boolean removerUC(String nome, String confirmacao) {
        if (!confirmacao) return false;
        boolean removido = listaUC.removeIf(uc -> uc.getNome().equalsIgnoreCase(nome));
        if (removido) {
            UCdao.salvar(listaUC);
        }
        return removido;
    }
}
