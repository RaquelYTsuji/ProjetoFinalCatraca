package com.senai.controller;

import com.senai.model.*;
import com.senai.model.dao.json.*;
import com.senai.model.dao.json.HorarioDAO;
import com.senai.websocket.WebSocketSender;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;


public class OcorrenciaController {
    OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final HorarioDAO horarioDAO = new HorarioDAO();
    private final ProfessorDAO professorDAO = new ProfessorDAO();
    private final TurmaDAO turmaDAO = new TurmaDAO();

    public List<Ocorrencia> listarOcorrencias() {
        return (List<Ocorrencia>) ocorrenciaDAO.listar();
    }

    public List<Ocorrencia> listarOcorrenciasDoAluno(int idAluno) {
        return ocorrenciaDAO.listarDoAluno(idAluno);
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

    public boolean atualizarOcorrenciasDoAluno(Ocorrencia ocorrencia) {
        if (ocorrencia != null) {
            ocorrenciaDAO.atualizar(ocorrencia);
            return true;
        }
        return false;
    }

    public boolean deletarOcorrenciasDoAluno(int id, int idAluno) {
        if (id >= 0) {
            ocorrenciaDAO.deletarDoAluno(id, idAluno);
            return true;
        }
        return false;
    }

    public boolean aceitarOcorrencias ( int id){
        if (id >= 0) {
            ocorrenciaDAO.deletar(id);
            return true;
        }
        return false;
    }

    public String CriarOcorrenciaAtraso(String idAcesso) {
        Optional<Aluno> alunoOpt = alunoDAO.buscarPorIdAcesso(idAcesso);
        if (alunoOpt.isEmpty()) {
            return "[ACESSO NEGADO] Aluno não encontrado para ID: " + idAcesso;
        }
        Aluno aluno = alunoOpt.get();
        Optional<Turma> turmaOpt = turmaDAO.buscarTurmaDoAluno(aluno.getId());
        if (turmaOpt.isEmpty()) {
            return "[ACESSO] Aluno: " + aluno.getNome() + " - Nenhuma turma atribuída.";
        }

        Turma turma = turmaOpt.get();
        Optional<Horario> horarioOpt = horarioDAO.buscarHorarioDoAluno(aluno.getId());

        if (horarioOpt.isEmpty()) {
            return "[ACESSO] Aluno: " + aluno.getNome() + " - Nenhum horário atribuído.";
        }

        Horario horario = horarioOpt.get();
        boolean atrasado = aluno.estaAtrasado(aluno.getId());

        if (atrasado) {
            Optional<Professor> professorOpt = professorDAO.buscarPorId(horario.getIdProfessor());

            professorOpt.ifPresent(professor -> {
                Random random = new Random();
                int id = random.nextInt();
                Ocorrencia ocorrencia = new Ocorrencia(id, aluno.getId(), "Entrada", "Entrada atrasada", LocalDateTime.now());
                ocorrenciaDAO.salvar(ocorrencia);
                WebSocketSender.enviarMensagem(ocorrencia);
            });
            return "[ATRASO DETECTADO] Aluno: " + aluno.getNome();
        }
        return "[ENTRADA AUTORIZADA] Aluno: " + aluno.getNome();
    }
}

