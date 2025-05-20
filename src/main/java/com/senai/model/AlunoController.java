package com.senai.model;

import java.util.List;

public class AlunoController {
    private AlunoDAO alunoDAO = new AlunoDAO();


    public List<Aluno> listarAlunos() {
        return alunoDAO.listar;

    }
    public boolean cadastrarAluno(Aluno aluno){
        if(aluno!= null) {
            alunoDAO.salvar(aluno);
            return true;//
        }
        return false;
    }
    public boolean atualizarOperador(Aluno){
        if(operador!= null){
            operadorDAO.atualizar(operador);
            return true;
        }
        return false;
    }
    public boolean deletarOperador(int id){
        if(id >= 0){
            operadorDAO.deletar(id);
            return true;
        }
        return false;
    }
}


