package com.senai.controller;

import com.senai.model.Justificativa;
import com.senai.model.dao.json.JustificativaDao;

import java.time.LocalDateTime;
import java.util.List;

public class JustificativaController{

    private final JustificativaDao justificativaDao = new JustificativaDao();

    public String cadastrarJustificativa(int id, String tipo, String descricao, int quantidadeDias,
                                         int prazoDeAceite, String anexo, String status, boolean cancelar) {
        Justificativa justificativa = new Justificativa(
                id, tipo, descricao, LocalDateTime.now(),
                quantidadeDias, prazoDeAceite, anexo, status, cancelar
        );


        justificativaDao.salvar(justificativa);
        return "Justificativa cadastrada com sucesso.";
    }

    public String atualizarJustificativa(int id, String tipo, String descricao, LocalDateTime dataHora,
                                         int quantidadeDias, int prazoDeAceite, String anexo,
                                         String status, boolean cancelar) {
        Justificativa justificativa = new Justificativa(
                id, tipo, descricao, dataHora,
                quantidadeDias, prazoDeAceite, anexo, status, cancelar
        );


        justificativaDao.atualizar(justificativa);
        return "Justificativa atualizada com sucesso.";
    }

    public String removerJustificativa(int id) {
        boolean sucesso = justificativaDao.deletar(id);
        return sucesso ? "Justificativa removida com sucesso." : "Justificativa não encontrada.";
    }

    public List<Justificativa> listarJustificativas() {
        return justificativaDao.listar();
    }
}

