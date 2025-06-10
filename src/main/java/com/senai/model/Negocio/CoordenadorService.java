package com.senai.model.Negocio;

import com.senai.model.Coordenador;
import com.senai.model.Ocorrencia;
import com.senai.model.dao.json.CoordenadorDAO;
import com.senai.websocket.WebSocketClienteConsole;
import com.senai.websocket.WebSocketSender;

public class CoordenadorService {

    private CoordenadorDAO coordenadorDAO = new CoordenadorDAO();

    // Método para notificar o coordenador de um atraso
    public void notificarAtraso(Ocorrencia ocorrencia) {
        WebSocketClienteConsole.conectar();
        WebSocketSender.enviarMensagem(ocorrencia);
    }

    // Método para aceitar uma justificativa de ocorrência
    public void aceitarJustificativa(int idCoordenador, int idOcorrencia) {
        Coordenador coordenador = coordenadorDAO.buscarPorId(idCoordenador);

        if (coordenador == null) {
            throw new IllegalArgumentException("Coordenador não encontrado.");
        }

        System.out.println("Justificativa da ocorrência " + idOcorrencia + " foi aceita por " + coordenador.getNome());
    }

}
