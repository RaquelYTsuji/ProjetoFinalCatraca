package com.senai.controller;

import com.senai.model.Aula;
import com.senai.model.dao.json.AulaDAO;

import java.util.List;

public class AulaController {
    private AulaDAO aulaDAO= new AulaDAO();


    public boolean cadastarAula (Aula aula) {
        if (aula != null) {
            aulaDAO.salvar(aula);
            return true;
        }

        return false;
    }

    public  List<Aula> listarAulas() {
        return aulaDAO.listar();
    }
    public boolean atualizarAulas (Aula aula){
        if (aula !=null){
            aulaDAO.atualizar(aula);
            return true;
        }
        return false;
    }

    public boolean deletarAula (int id){
        if (id >=0){
            aulaDAO.deletar(id);
            return true;
        }
        return false;
    }

}
