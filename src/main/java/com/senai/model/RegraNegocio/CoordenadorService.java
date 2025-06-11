package com.senai.model.RegraNegocio;

import com.senai.controller.OcorrenciaController;
import com.senai.model.*;
import com.senai.model.dao.json.CoordenadorDAO;
import com.senai.model.dao.json.JustificativaDao;
import com.senai.model.dao.json.OcorrenciaDAO;
import com.senai.websocket.WebSocketClienteConsole;
import com.senai.websocket.WebSocketSender;

import java.time.LocalDate;
import java.util.List;

import static java.util.Locale.filter;

public class CoordenadorService {

    private final CoordenadorDAO coordenadorDAO = new CoordenadorDAO();
    Coordenador coordenador = new Coordenador();
    private final OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
    private final OcorrenciaController ocorrenciaController = new OcorrenciaController();
    private final JustificativaDao justificativaDAO = new JustificativaDao();
    private LocalDate localdate;
    private final Aluno aluno = new Aluno("", "", "", 0, "", localdate.now());

    // Metodo para notificar o coordenador de um atraso
    public void receberNotificacao(Ocorrencia ocorrencia) {
        WebSocketClienteConsole.conectar();
        WebSocketSender.enviarMensagem(ocorrencia);
        ocorrenciaDAO.salvar(ocorrencia);
    }

    public void listarJustificativas(JustificativaDao justificativa) {
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


    //Parte para que as justificativas
    public void receberJustificativa(JustificativaDao justificativa) {
        justificativa.listar();
    }

    // Metodo para aceitar um atraso com uma ocorrência
    public void aceitarOcorrencias(OcorrenciaDAO ocorrencia, Ocorrencia o) {
        String status = "msg";

        if (ocorrencia.buscarPorStatusAguardando(status).isEmpty()){
            System.out.println("Não há ocorrencias para serem aceitas");

        }else {
            ocorrencia.buscarPorStatusAguardando(status);
            ocorrencia.aceitar(o.getId());
            System.out.println("A Ocorrencia foi aceita por: " + coordenador.getNome());
        }
    }

    public void gerarRelatorioAtrasosPorAluno() {

    }
}

