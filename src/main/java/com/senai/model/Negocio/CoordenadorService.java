package com.senai.model.Negocio;

import com.senai.model.Coordenador;
import com.senai.model.Ocorrencia;
import com.senai.model.dao.json.CoordenadorDAO;
import com.senai.model.dao.json.OcorrenciaDAO;
import com.senai.websocket.WebSocketClienteConsole;
import com.senai.websocket.WebSocketSender;

public class CoordenadorService {

    private final CoordenadorDAO coordenadorDAO = new CoordenadorDAO();
    private final OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();

    // Metodo para notificar o coordenador de um atraso
    public void notificarAtraso(Ocorrencia ocorrencia) {
        WebSocketClienteConsole.conectar();
        WebSocketSender.enviarMensagem(ocorrencia);
    }

    // Metodo para aceitar um atraso com uma ocorrência
    public void aceitarJustificativa(int idCoordenador, int idOcorrencia) {
        Coordenador coordenador = coordenadorDAO.buscarPorId(idCoordenador);

        if (coordenador == null) {
            throw new IllegalArgumentException("Coordenador não encontrado.");
        }

        //Parte da aceitação do coordernador pela ocorrencia
        ocorrenciaDAO.aceitar(idOcorrencia);
        ocorrenciaDAO.buscarPorStatusAguardando("JUSTIFICADO");

        System.out.println("Justificativa da ocorrência " + idOcorrencia + " foi aceita por " + coordenador.getNome());
    }
}
