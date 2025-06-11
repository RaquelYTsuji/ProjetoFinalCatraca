package com.senai.model.RegraNegocio;

import com.senai.model.*;
import com.senai.model.dao.json.CoordenadorDAO;
import com.senai.model.dao.json.JustificativaDao;
import com.senai.model.dao.json.OcorrenciaDAO;
import com.senai.websocket.WebSocketClienteConsole;
import com.senai.websocket.WebSocketSender;

import java.util.*;

public class CoordenadorService {
    private final Coordenador coordenador;
    private final CoordenadorDAO coordenadorDAO;
    private final OcorrenciaDAO ocorrenciaDAO;
    private final JustificativaDao justificativaDAO;
    private final Aluno aluno;
    private final UnidadeCurricular uc;


    public CoordenadorService(Coordenador coordenador, CoordenadorDAO coordenadorDAO, OcorrenciaDAO ocorrenciaDAO, JustificativaDao justificativaDAO, com.senai.model.Aluno aluno, UnidadeCurricular uc) {
        this.coordenador = coordenador;
        this.coordenadorDAO = coordenadorDAO;
        this.ocorrenciaDAO = ocorrenciaDAO;
        this.justificativaDAO = justificativaDAO;
        this.aluno = aluno;
        this.uc = uc;
    }

    // Notifica o coordenador e salva a ocorrência
    public void receberNotificacao(Ocorrencia ocorrencia) {
        WebSocketClienteConsole.conectar();
        WebSocketSender.enviarMensagem(ocorrencia);
        ocorrenciaDAO.salvar(ocorrencia);
    }

    // Lista todas as justificativas disponíveis
    public void listarJustificativas() {
        List<Justificativa> justificativas = justificativaDAO.listar();

        if (justificativas.isEmpty()) {
            System.out.println("Não há justificativas.");
        } else {
            for (Justificativa j : justificativas) {
                System.out.println("ID: " + j.getId() +
                        ", aluno: " + aluno.getNome() +
                        ", anexo: " + j.getAnexo() +
                        ", Descrição: " + j.getDescricao() +
                        ", Status: " + j.getStatus());
            }
        }
    }

    public void aceitarOcorrencia(Ocorrencia ocorrencia) {
        Optional<Ocorrencia> aguardando = ocorrenciaDAO.buscarPorStatusAguardando("aguardando"); // ou sem parâmetro

        if (aguardando.isEmpty()) {
            System.out.println("Não há ocorrências para serem aceitas.");
        } else if (aguardando.stream().noneMatch(o -> o.getId() == ocorrencia.getId())) {
            System.out.println("A ocorrência informada não está com status aguardando.");
        } else {
            ocorrenciaDAO.aceitar(ocorrencia.getId());
            System.out.println("A Ocorrência foi aceita por: " + coordenador.getNome());
        }
    }


    // Gera relatório de atrasos por aluno
    public void gerarRelatorioAtrasosPorAluno() {
        List<Ocorrencia> ocorrencias = ocorrenciaDAO.listar();
        Map<String, List<Ocorrencia>> atrasosPorAluno = new HashMap<>();

        for (Ocorrencia o : ocorrencias) {
            if (o.getStatus().equalsIgnoreCase("atrasado") || o.getStatus().equalsIgnoreCase("justificado")) {
                String nomeAluno = aluno.getNome();
                atrasosPorAluno.putIfAbsent(nomeAluno, new ArrayList<>());
                atrasosPorAluno.get(nomeAluno).add(o);
            }
        }

        for (Map.Entry<String, List<Ocorrencia>> entry : atrasosPorAluno.entrySet()) {
            System.out.println("Aluno: " + entry.getKey());
            for (Ocorrencia o : entry.getValue()) {
                System.out.println(" - Data: " + o.getDataHora() +
                        ", UC: " + uc.getNome() +
                        ", Status: " + o.getStatus());
            }
        }
    }

    // Gera relatório geral por curso
//    public void gerarRelatorioPorCurso(String curso) {
//        List<Ocorrencia> ocorrencias = ocorrenciaDAO.listar();
//
//        for (Ocorrencia o : ocorrencias) {
//            if (uc.getNome().equalsIgnoreCase(curso)) {
//                System.out.println("Aluno: " + aluno.getNome() +
//                        " - Data: " + o.getDataHora() +
//                        " - UC: " + uc.getNome() +
//                        " - Status: " + o.getStatus());
//            }
//        }
//    }
}

