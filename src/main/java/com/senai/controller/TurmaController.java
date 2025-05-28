package com.senai.controller;

import com.senai.model.Turma;
import com.senai.model.dao.json.TurmaDAO;

import java.util.List;

public class TurmaController {
    private TurmaDAO turmaDAO = new TurmaDAO();

    public boolean cadastrarTurma(Turma turma){
        if(turma != null){
            turmaDAO.salvar(turma);
            return true;
        }
        return false;
    }

    public List<Turma> listarTurmas(){
        return turmaDAO.listar();
    }

    public boolean atualizarTurmas(Turma turma){
        if(turma != null){
            turmaDAO.atualizar(turma);
            return true;
        }
        return false;
    }

    public boolean deletarTurma(int id){
        if(id >= 0){
            turmaDAO.deletar(id);
            return true;
        }
        return false;
    }
}
