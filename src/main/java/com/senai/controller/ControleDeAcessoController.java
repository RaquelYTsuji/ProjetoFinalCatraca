package com.senai.controller;

import com.senai.model.dao.json.AlunoDAO;
import com.senai.model.dao.json.HorarioDAO;
import com.senai.model.dao.json.ProfessorDAO;
import com.senai.model.Aluno;
import com.senai.model.Horario;
import com.senai.model.Professor;

import java.util.Optional;

public class ControleDeAcessoController {
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final HorarioDAO horarioDAO = new HorarioDAO();
    private final ProfessorDAO professorDAO = new ProfessorDAO();

    public String processarEntrada(String idAcesso) {
        Optional<Aluno> alunoOpt = alunoDAO.buscarPorIdAcesso(idAcesso);
        if (alunoOpt.isEmpty()) {
            return "[ACESSO NEGADO] Aluno não encontrado para RFID: " + idAcesso;
        }

        Aluno aluno = alunoOpt.get();
        Optional<Horario> horarioOpt = horarioDAO.buscarHorarioDoAluno(aluno.getId());

        if (horarioOpt.isEmpty()) {
            return "[ACESSO] Aluno: " + aluno.getNome() + " - Nenhum horário atribuído.";
        }

        Horario horario = horarioOpt.get();
        boolean atrasado = aluno.estaAtrasado(aluno.getId());

        if (atrasado) {
            Optional<Professor> professorOpt = professorDAO.buscarPorId(horario.getIdProfessor());
            professorOpt.ifPresent(professor -> {
//                String msg = "[ATRASO] Aluno " + aluno.getNome() + " chegou atrasado.";
//                WebSocketSender.enviarMensagem(msg);
            });
            return "[ATRASO DETECTADO] Aluno: " + aluno.getNome();
        }

        return "[ENTRADA AUTORIZADA] Aluno: " + aluno.getNome();
    }
}

