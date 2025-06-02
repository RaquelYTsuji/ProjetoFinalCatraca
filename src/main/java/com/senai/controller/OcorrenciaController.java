package com.senai.controller;

import com.senai.model.Ocorrencia;
import com.senai.model.dao.json.OcorrenciaDAO;

import java.util.List;


public class OcorrenciaController {
    OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();

    public List<Ocorrencia> listarOcorrencias(){
        return (List<Ocorrencia>) ocorrenciaDAO.listar();
    }

    public boolean cadastrarOcorrencias(Ocorrencia ocorrencia){
        if (ocorrencia != null){
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

    public boolean aceitarOcorrencias(int id) {
        if (id >= 0) {
            ocorrenciaDAO.deletar(id);
            return true;
        }
        return false;
    }
}

