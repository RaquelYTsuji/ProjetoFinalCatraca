package com.senai.model;

import java.util.List;

public class AlunoController {
    private AlunoDAO alunoDAO = new AlunoDAO();


    public List<Aluno> listarAlunos() {
        return alunoDAO.listar();
    }

    public boolean cadastrarAluno(Aluno aluno){
        if(aluno!= null) {
            alunoDAO.salvar(aluno);
            return true;//
        }
        return false;
    }
    public boolean atualizarAluno(Aluno aluno){
        if(aluno!= null){
            alunoDAO.atualizar(aluno);
            return true;
        }
        return false;
    }
    public boolean deletarAlunoPorID(int id){
        if(id >= 0){
            alunoDAO.deletar(id);
            return true;
        }
        return false;

    }
}


