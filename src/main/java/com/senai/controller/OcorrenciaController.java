package com.senai.controller;

import com.senai.model.Aluno;
import com.senai.model.Ocorrencia;
import com.senai.model.Turma;
import com.senai.model.dao.json.AlunoDAO;
import com.senai.model.dao.json.OcorrenciaDAO;
import com.senai.model.dao.json.TurmaDAO;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Filter;


public class OcorrenciaController {
    OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();

    public List<Ocorrencia> listarOcorrencias() {
        return (List<Ocorrencia>) ocorrenciaDAO.listar();
    }

    public boolean cadastrarOcorrencias(Ocorrencia ocorrencia) {
        if (ocorrencia != null) {
            ocorrenciaDAO.salvar(ocorrencia);
            return true;
        }
        return false;
    }

    public boolean atualizarOcorrencias(Ocorrencia ocorrencia) {
        if (ocorrencia != null) {
            ocorrenciaDAO.atualizar(ocorrencia);
            return true;
        }
        return false;
    }

    public boolean deletarOcorrencias(int id) {
        if (id >= 0) {
            ocorrenciaDAO.deletar(id);
            return true;
        }
        return false;
    }

    public boolean ocorrenciaAtraso(String idAcesso) {
        AlunoDAO alunoDAO = new AlunoDAO();
        Turma turma = new Turma();

        Optional<Aluno> alunoOpt = alunoDAO.buscarPorIdAcesso(idAcesso);

        if (alunoOpt.isPresent()) {
            LocalTime horarioDeEntrada = turma.getHorarioEntrada();
            int tolerancia = turma.getCurso().getTolerancia();

            if (LocalTime.now().isAfter(horarioDeEntrada.plusMinutes(tolerancia))){
                Ocorrencia ocorrencia = new Ocorrencia();

                ocorrencia.setId(1);
                ocorrencia.setTipo("Entrada");
                ocorrencia.setStatus("");
                ocorrencia.setDescricao("");
                ocorrencia.setDataHora(LocalDateTime.now());
            }
            //WebSocketSender.enviarMensagem(Ocorrencia);
            return true;
        }
        return false;

    }

    public boolean aceitarOcorrencias(int id) {
        if (id >= 0) {
            ocorrenciaDAO.deletar(id);
            return true;
        }
        return false;
    }
}

