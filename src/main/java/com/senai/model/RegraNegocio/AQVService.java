package com.senai.model.RegraNegocio;

import com.senai.model.AQV;
import com.senai.model.Ocorrencia;
import com.senai.model.dao.json.AQVDAO;
import com.senai.model.dao.json.OcorrenciaDAO;
import com.senai.websocket.WebSocketClienteConsole;
import com.senai.websocket.WebSocketSender;

public class AQVService {

    private final AQVDAO aqvDAO = new AQVDAO();
    private final OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();


    public void notificarAtraso(Ocorrencia ocorrencia) {
        WebSocketClienteConsole.conectar();
        WebSocketSender.enviarMensagem(ocorrencia);
    }


    public void aceitarJustificativa(int idAQV, int idOcorrencia) {
        AQV aqv = aqvDAO.buscarPorId(idAQV);

        if (aqv == null) {
            throw new IllegalArgumentException("AQV não encontrado.");
        }


        ocorrenciaDAO.aceitar(idOcorrencia);
        ocorrenciaDAO.buscarPorStatusAguardando("JUSTIFICADO");

        System.out.println("Justificativa da ocorrência " + idOcorrencia + " foi aceita por " + aqv.getNome());
    }
}


