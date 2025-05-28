package com.senai.controller;

import com.senai.model.Ambiente;
import com.senai.model.dao.json.AmbienteDAO;

import java.util.List;

public class AmbienteController {
    private AmbienteDAO ambienteDAO = new AmbienteDAO();

    public boolean cadastrarAmbiente(Ambiente ambiente){
        if(ambiente != null){
            ambienteDAO.salvar(ambiente);
            return true;
        }
        return false;
    }

    public List<Ambiente> listarAmbientes(){
        return ambienteDAO.listar();
    }

    public boolean atualizarAmbiente(Ambiente ambiente){
        if(ambiente != null){
            ambienteDAO.atualizar(ambiente);
            return true;
        }
        return false;
    }

    public boolean deletarAmbiente(int id){
        if(id >= 0){
            ambienteDAO.deletar(id);
            return true;
        }
        return false;
    }
}
