package com.senai.controller;

import com.senai.model.Justificativa;
import com.senai.model.dao.json.JustificativaDao;

import java.time.LocalDateTime;
import java.util.List;

public class JustificativaController{

    private final JustificativaDao justificativaDao = new JustificativaDao();

    public String cadastrarJustificativa(int id, int idAluno, String tipo, String descricao, int quantidadeDias,
                                         int prazoDeAceite, String anexo, String status, boolean cancelar) {
        Justificativa justificativa = new Justificativa(
                id, idAluno, tipo, descricao, LocalDateTime.now(),
                quantidadeDias, prazoDeAceite, anexo, status, cancelar
        );


        justificativaDao.salvar(justificativa);
        return "Justificativa cadastrada com sucesso.";
    }

    public String atualizarJustificativa(int id, int idAluno, String tipo, String descricao, LocalDateTime dataHora,
                                         int quantidadeDias, int prazoDeAceite, String anexo,
                                         String status, boolean cancelar) {
        Justificativa justificativa = new Justificativa(
                id, idAluno, tipo, descricao, dataHora,
                quantidadeDias, prazoDeAceite, anexo, status, cancelar
        );


        justificativaDao.atualizar(justificativa);
        return "Justificativa atualizada com sucesso.";
    }

    public String removerJustificativa(int id) {
        boolean sucesso = justificativaDao.deletar(id);
        return sucesso ? "Justificativa removida com sucesso." : "Justificativa não encontrada.";
    }

    public String atualizarJustificativaDoAluno(int id, int idAluno, String tipo, String descricao, LocalDateTime dataHora,
                                         int quantidadeDias, int prazoDeAceite, String anexo,
                                         String status, boolean cancelar) {
        Justificativa justificativa = new Justificativa(
                id, idAluno, tipo, descricao, dataHora,
                quantidadeDias, prazoDeAceite, anexo, status, cancelar
        );


        justificativaDao.atualizarDoAluno(justificativa);
        return "Justificativa atualizada com sucesso.";
    }

    public String removerJustificativaDoAluno(int id, int idAluno) {
        boolean sucesso = justificativaDao.deletarDoAluno(id, idAluno);
        return sucesso ? "Justificativa removida com sucesso." : "Justificativa não encontrada.";
    }

    public String aceitarJustificativa(int id, String status) {
        justificativaDao.aceitar(id, status);
        return "Justificativa aceitada com sucesso.";
    }

    public List<Justificativa> listarJustificativas() {
        return justificativaDao.listar();
    }

    public List<Justificativa> listarJustificativasDoAluno(int idAluno) {
        return justificativaDao.listarDoAluno(idAluno);
    }
}

