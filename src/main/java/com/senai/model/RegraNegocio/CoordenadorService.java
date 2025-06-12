package com.senai.model.RegraNegocio;

import com.senai.model.*;
import com.senai.model.dao.json.CoordenadorDAO;
import com.senai.model.dao.json.JustificativaDao;
import com.senai.model.dao.json.OcorrenciaDAO;
import com.senai.websocket.WebSocketClienteConsole;
import com.senai.websocket.WebSocketSender;

import java.time.LocalDate;
import java.util.*;

public class CoordenadorService {
    private final Coordenador coordenador;
    private final CoordenadorDAO coordenadorDAO = new CoordenadorDAO();
    private final OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
    private final JustificativaDao justificativaDAO = new JustificativaDao();
    private final Aluno aluno = new Aluno("", "", "", 0, "", LocalDate.now());
    private final UnidadeCurricular uc = new UnidadeCurricular(0, "", "", "", "", "");


    public CoordenadorService(Coordenador coordenador) {
        this.coordenador = coordenador;
    }

    public void receberNotificacao(Ocorrencia ocorrencia) {
        WebSocketClienteConsole.conectar();
        WebSocketSender.enviarMensagem(ocorrencia);
        ocorrenciaDAO.salvar(ocorrencia);
    }

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

    public void gerarRelatorioAtrasosPorAluno() {
        List<Ocorrencia> ocorrencias = ocorrenciaDAO.listar();
        Map<String, List<Ocorrencia>> atrasosPorAluno = new HashMap<>();
        if (ocorrencias.isEmpty()){
            System.out.println("Não há ocorrencias");
        }else {
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

    }
}

