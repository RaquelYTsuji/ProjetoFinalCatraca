package com.senai.controller;

import com.senai.model.SubTurma;
import com.senai.model.SubTurmaDAO;

import java.util.List;

public class SubTurmaController {
    private SubTurmaDAO subTurmaDAO = new SubTurmaDAO();

    public boolean cadastrarSubTurma(SubTurma subTurma){
        if(subTurma != null){
            subTurmaDAO.salvar(subTurma);
            return true;
        }
        return false;
    }

    public List<SubTurma> listarSubTurmas(){
        return subTurmaDAO.listar();
    }

    public boolean atualizarSubTurmas(SubTurma subTurma){
        if(subTurma != null){
            subTurmaDAO.atualizar(subTurma);
            return true;
        }
        return false;
    }

    public boolean deletarSubTurma(int id){
        if(id >= 0){
            subTurmaDAO.deletar(id);
            return true;
        }
        return false;
    }
}
